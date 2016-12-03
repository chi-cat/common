package top.flyfire.common.reflect.metainfo;

import top.flyfire.common.StringUtils;
import top.flyfire.common.reflect.GenericTypeAdapted;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;

import java.lang.reflect.Type;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/31.
 */
public class ArrayMetaInfo extends MetaInfo implements GenericTypeAdapted {

    private final MetaInfo genericComponentType;

    public ArrayMetaInfo(MetaInfo genericComponentType) {
        this.genericComponentType = genericComponentType;
        this.init();
    }

    @Override
    protected void init() {
        typeName = StringUtils.merge(genericComponentType.toString(),"[]");
        hash = typeName.hashCode();
    }

    @Override
    public boolean compatible(Type type) {
        return false;
    }

    public MetaInfo getGenericComponentType() {
        return genericComponentType;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }else if(obj instanceof ArrayMetaInfo){
            ArrayMetaInfo other = ((ArrayMetaInfo) obj);
            return genericComponentType.equals(other.genericComponentType);
        }else{
            return false;
        }
    }

    public MetaInfo adapt(MetaInfo[] variableMetaInfos, MetaInfo[] typeStore) {
        if(genericComponentType instanceof GenericTypeAdapted){
            return new ArrayMetaInfo(((GenericTypeAdapted) genericComponentType).adapt(variableMetaInfos,typeStore));
        }
        return this;
    }
}
