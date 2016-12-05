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
    }

    @Override
    protected String buildTypeName() {
        if (!MetaInfo.NULL.equals(this.lowerBound)) {
            return StringUtils.merge("? super ", this.lowerBound.getTypeName());
        } else if (MetaInfo.NULL.equals(this.upperBound)) {
            return "?";
        } else {
            return StringUtils.merge("? extends ", this.upperBound.getTypeName());
        }
    }

    public MetaInfo getUpperBound() {
        return upperBound;
    }

    public MetaInfo getLowerBound() {
        return lowerBound;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof WildcardMetaInfo) {
            WildcardMetaInfo other = ((WildcardMetaInfo) obj);
            return upperBound.equals(other.upperBound) && lowerBound.equals(other.lowerBound);
        } else {
            return false;
        }
    }
}
