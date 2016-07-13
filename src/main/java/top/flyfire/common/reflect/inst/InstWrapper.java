package top.flyfire.common.reflect.inst;

import top.flyfire.common.reflect.MetaInfo;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/22.
 * instance wrapper
 */
public abstract class InstWrapper<M extends MetaInfo> {

    protected M metaInfo;

    public InstWrapper(M metaInfo){
        this.metaInfo = metaInfo;
    }

    public abstract Object newInstance();

    public abstract Object newContainerInstance(int size);

}
