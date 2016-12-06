package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.reflect.MetaInfo;

/**
 * Created by devll on 2016/12/5.
 */
public interface BuildOutWrapper<O> extends InstanceWrapper<O>{

    MetaInfo getMetaInfo();

}
