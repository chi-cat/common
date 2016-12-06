package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.reflect.metainfo.FieldMetaInfo;

/**
 * Created by devll on 2016/12/5.
 */
public interface BuildInWrapper extends InstanceWrapper<Object> {

    FieldMetaInfo getField(String m);

}
