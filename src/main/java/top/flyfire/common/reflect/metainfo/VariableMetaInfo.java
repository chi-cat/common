package top.flyfire.common.reflect.metainfo;

import top.flyfire.common.reflect.GenericTypeAdapted;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/31.
 */
public class VariableMetaInfo extends MetaInfo implements GenericTypeAdapted {

    private final MetaInfo[] bounds;

    private final GenericDeclaration genericDeclaration;

    public VariableMetaInfo(String name,MetaInfo[] bounds, GenericDeclaration genericDeclaration) {
        this.typeName = name;
        this.bounds = bounds;
        this.genericDeclaration = genericDeclaration;
    }

    @Override
    protected String buildTypeName() {
        return typeName;
    }

    @Override
    public boolean compatible(Type type) {
        return false;
    }

    public MetaInfo[] getBounds() {
        return bounds;
    }

    public GenericDeclaration getGenericDeclaration() {
        return genericDeclaration;
    }

    public String getName() {
        return typeName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }else if(obj instanceof VariableMetaInfo){
            VariableMetaInfo other = ((VariableMetaInfo) obj);
            return typeName.equals(other.typeName)&&genericDeclaration.equals(other.genericDeclaration)&&ReflectUtils.metaInfoArrEquals(bounds,other.bounds);
        }else{
            return false;
        }
    }

    public MetaInfo adapt(MetaInfo[] variableMetaInfos, MetaInfo[] typeStore) {
        for(int i = 0 ;i<variableMetaInfos.length;i++){
            if(this.equals(variableMetaInfos[i])){
                return typeStore[i];
            }
        }
        return this;
    }

}
