package top.flyfire.common.reflect;

import org.junit.Test;
import top.flyfire.common.reflect.metainfo.ClassMetaInfo;

/**
 * Created by shyy_work on 2016/7/13.
 */
public class InstanceTest {

    @Test
    public void test(){
        MetaInfo metaInfo = ReflectUtils.getMetaInfo(People.class);
        Object object = ((ClassMetaInfo)metaInfo).newInstance();
        System.out.println(metaInfo);
    }

}
