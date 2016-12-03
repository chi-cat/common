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

    private final MetaInfo bound;

    private final GenericDeclaration genericDeclaration;

    public VariableMetaInfo(String name,MetaInfo bound, GenericDeclaration genericDeclaration) {
        this.typeName = name;
        this.bound = bound;
        this.genericDeclaration = genericDeclaration;
        this.init();
    }

    @Override
    protected void init() {
        hash = typeName.hashCode();
    }

    @Override
    public boolean compatible(Type type) {
        return false;
    }

    public MetaInfo getBound() {
        return bound;
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
            return typeName.equals(other.typeName)&&genericDeclaration.equals(other.genericDeclaration)&&bound.equals(other.bound);
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
