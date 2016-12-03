package top.flyfire.common.reflect.metainfo;

import top.flyfire.common.CharPool;
import top.flyfire.common.StringUtils;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.ReflectiveException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/30.
 */
public class FieldMetaInfo {

    private final String fieldName;

    private final Field field;

    private final MetaInfo type;

    private final Method setter;

    private final Method getter;


    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

    public Object invokeGetter(Object object){
        try {
            return this.getter.invoke(object);
        }catch (ReflectiveOperationException e){
            throw new ReflectiveException(e);
        }
    }

    public void invokeSetter(Object object,Object objectParameterized){
        try {
            this.setter.invoke(object,objectParameterized);
        }catch (ReflectiveOperationException e){
            throw new ReflectiveException(e);
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public Field getField(){
        return field;
    }

    public MetaInfo getType(){
        return type;
    }

    public FieldMetaInfo(String fieldName,Field field,MetaInfo type, Method getter ,Method setter) {
        this.fieldName = fieldName;
        this.field = field;
        this.type = type;
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public String toString() {
        return StringUtils.merge(this.type.getTypeName(), " ",this.field.getDeclaringClass().getName(),".",this.fieldName);
    }
}
