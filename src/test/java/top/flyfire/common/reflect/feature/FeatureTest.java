package top.flyfire.common.reflect.feature;

import org.junit.Test;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.RawType;
import top.flyfire.common.reflect.ReflectUtils;

import java.math.BigDecimal;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class FeatureTest {

    public void common(MetaInfo metaInfo){
        metaInfo.toString();
    }

    @Test
    public void baseTest(){
       common(ReflectUtils.unWrap(Base.class));
    }

    @Test
    public void baseWithGenTest(){
        common(ReflectUtils.unWrap(BaseWithGen.class));
    }

    @Test
    public void baseWidthGeanAsPdTest(){
        common(ReflectUtils.unWrap(new RawType<BaseWithGen<BigDecimal>>(){}.getType()));
    }

    @Test
    public void wtTest(){
        common(ReflectUtils.unWrap(WType.class));
    }

}
