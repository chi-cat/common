package top.flyfire.common.reflect.inst;

import top.flyfire.common.reflect.MetaInfo;

/**
 * Created by shyy_work on 2016/7/31.
 */
public abstract class StructedInstWrapper<M extends MetaInfo> extends InstWrapper<M,String,Object> {
    public StructedInstWrapper(Object proxyVal, M metaInfo) {
        super(proxyVal, metaInfo);
    }
}
