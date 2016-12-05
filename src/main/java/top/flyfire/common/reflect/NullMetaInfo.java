package top.flyfire.common.reflect;

/**
 * Created by devll on 2016/12/5.
 */
public class NullMetaInfo extends MetaInfo {

    protected NullMetaInfo(){}

    @Override
    protected String buildTypeName() {
        return "null";
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

}
