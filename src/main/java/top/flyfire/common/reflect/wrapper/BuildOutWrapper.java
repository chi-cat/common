package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.reflect.MetaInfo;

/**
 * Created by devll on 2016/12/5.
 */
public interface BuildOutWrapper<M> extends InstanceWrapper<M>{

    void set(M s,Object instance, Object val);

    MetaInfo getMetaInfo();

}
