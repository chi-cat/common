package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.reflect.MetaInfo;

/**
 * Created by shyy_work on 2016/9/13.
 */
public interface Wrapper<M> {

    Object instance();

    MetaInfo getMetaInfo(M m);

    void set(M s,Object instance, Object val);

    Object rawValue(Object instance);

}
