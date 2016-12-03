package top.flyfire.common.reflect.metainfo;

import top.flyfire.common.ObjectUtils;
import top.flyfire.common.StringUtils;
import top.flyfire.common.reflect.MetaInfo;

import java.lang.reflect.Type;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/31.
 */
public class WildcardMetaInfo extends MetaInfo {

    private final MetaInfo upperBound;

    private final MetaInfo lowerBound;

    public WildcardMetaInfo(MetaInfo upperBound, MetaInfo lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.init();
    }

    @Override
    protected void init() {
        if(!MetaInfo.NULL.equals(this.lowerBound)){
            typeName = StringUtils.merge("? super " ,this.lowerBound.getTypeName());
        }else if(MetaInfo.NULL.equals(this.upperBound)){
            typeName = "?";
        }else{
            typeName = StringUtils.merge("? extends " ,this.upperBound.getTypeName());
        }
        hash = typeName.hashCode();
    }

    @Override
    public boolean compatible(Type type) {
        return false;
    }

    public MetaInfo getUpperBound() {
        return upperBound;
    }

    public MetaInfo getLowerBound() {
        return lowerBound;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }else if(obj instanceof WildcardMetaInfo){
            WildcardMetaInfo other = ((WildcardMetaInfo) obj);
            return upperBound.equals(other.upperBound)&&lowerBound.equals(other.lowerBound);
        }else{
            return false;
        }
    }
}
