package top.flyfire.common.reflect.inst;

import org.junit.Test;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.RawType;
import top.flyfire.common.reflect.ReflectUtils;

import java.util.Map;

/**
 * Created by shyy_work on 2016/7/29.
 */
public class ValuePutTest {

    @Test
    public void test(){
        MetaInfo metaInfo = ReflectUtils.unWrap(new RawType<Map<String,Integer>>(){}.getType());
        //metaInfo = ReflectUtils.unWrap(new RawType<Animal>(){}.getType());
        System.out.println(metaInfo);
    }

}
