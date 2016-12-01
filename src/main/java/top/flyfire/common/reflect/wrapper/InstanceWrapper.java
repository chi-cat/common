package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.reflect.MetaInfo;

/**
 * Created by shyy_work on 2016/12/1.
 */
public interface InstanceWrapper<M> extends Wrapper {


    Object instance();

    void set(M s,Object instance, Object val);

    MetaInfo getMetaInfo(M m);

}
