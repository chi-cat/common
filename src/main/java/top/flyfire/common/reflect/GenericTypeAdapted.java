package top.flyfire.common.reflect;

import top.flyfire.common.reflect.metainfo.VariableMetaInfo;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/7/3.
 */
public interface GenericTypeAdapted {

    MetaInfo adapt(MetaInfo[] variableMetaInfos,MetaInfo[] typeStore);

}
