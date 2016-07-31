package top.flyfire.common.reflect.inst;

import top.flyfire.common.reflect.MetaInfo;

/**
 * Created by shyy_work on 2016/7/31.
 */
public abstract class IndexedInstWrapper<M extends MetaInfo> extends InstWrapper<M,Integer,Object> {

    public IndexedInstWrapper(Object proxyVal, M metaInfo) {
        super(proxyVal, metaInfo);
    }
}
