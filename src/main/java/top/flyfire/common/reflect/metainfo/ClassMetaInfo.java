package top.flyfire.common.reflect.metainfo;

import top.flyfire.common.LoopUtils;
import top.flyfire.common.reflect.GenericTypeAdapted;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.ReflectiveSyntaxException;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/30.
 */
public class ClassMetaInfo extends MetaInfo {

    private Class<?> rawType;

    private MetaInfo[] typeParameters;

    private Map<String,FieldMetaInfo> fieldMetaInfoMap;

    @Override
    protected String buildTypeName() {
        return rawType.getName();
    }

    @Override
    public boolean compatible(Type type) {
        return false;
    }

    public ClassMetaInfo(Class<?> rawType,MetaInfo[] typeParameters) {
        this.rawType = rawType;
        this.fieldMetaInfoMap = new HashMap<String, FieldMetaInfo>();
        this.typeParameters = typeParameters;
    }

    public void setFieldMetaInfo(String fieldName,FieldMetaInfo fieldMetaInfo){
        this.fieldMetaInfoMap.put(fieldName,fieldMetaInfo);
    }

    public void extendSuper(ClassMetaInfo classMetaInfo){
        LoopUtils.forEach(classMetaInfo.fieldMetaInfoMap, new EntryProxy$extendSuper$ClassMetaInfo());
    }

    public class EntryProxy$extendSuper$ClassMetaInfo implements LoopUtils.EntryProxy<String,FieldMetaInfo>{
        public void proxy(String key, FieldMetaInfo value, Map.Entry<String, FieldMetaInfo> entry) {
            fieldMetaInfoMap.put(key,value);
        }
    }

    public void extendSuper(ParameterizedMetaInfo parameterizedMetaInfo) {
        Type type;
        while (!((type = parameterizedMetaInfo.getRawType()) instanceof ClassMetaInfo)) {
            if (type instanceof ParameterizedMetaInfo) {
                parameterizedMetaInfo = (ParameterizedMetaInfo) type;
            } else {
                throw new ReflectiveSyntaxException("[A ClassMetaInfo or TypeParameterized is expected in the buidlSuper , but superType is of type " + parameterizedMetaInfo + " .]");
            }
        }
        final ClassMetaInfo classMetaInfo = (ClassMetaInfo) type;
        final MetaInfo[] types = parameterizedMetaInfo.getActualTypeArguments();

        LoopUtils.forEach(classMetaInfo.fieldMetaInfoMap, new LoopUtils.EntryProxy<String, FieldMetaInfo>() {
            public void proxy(String key, FieldMetaInfo value, Map.Entry<String, FieldMetaInfo> entry) {
                Field field = value.getField();
                MetaInfo fieldType = value.getType();
                if(fieldType instanceof GenericTypeAdapted)
                    fieldType = ((GenericTypeAdapted) fieldType).adapt(classMetaInfo.typeParameters,types);
                fieldMetaInfoMap.put(key, new FieldMetaInfo(key, field,fieldType , value.getGetter(), value.getSetter()));
            }
        });
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }else if(obj instanceof ClassMetaInfo){
            ClassMetaInfo other = ((ClassMetaInfo) obj);
            return rawType.equals(other.rawType)&&ReflectUtils.metaInfoArrEquals(typeParameters,other.typeParameters);
        }else{
            return false;
        }
    }
}
