package top.flyfire.common.reflect.feature;

import org.junit.Test;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.RawType;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.metainfo.ClassMetaInfo;
import top.flyfire.common.reflect.wrapper.Wrapper;
import top.flyfire.common.reflect.wrapper.WrapperFactory;

import java.math.BigDecimal;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class FeatureTest {

    public void common(MetaInfo metaInfo){
        ClassMetaInfo temp = null;
        Wrapper wrapper = WrapperFactory.getInstance().wrap(metaInfo);
        Object instance = wrapper.instance();
//        wrapper.set("gen",instance,new BigDecimal(123));
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
        common(ReflectUtils.unWrap(new RawType<BaseWithGen<Base>>(){}.getType()));
    }

    @Test
    public void wtTest(){
        MetaInfo metaInfo = ReflectUtils.unWrap(WType.class);
        Wrapper wrapper = WrapperFactory.getInstance().wrap(metaInfo);
        Object instance = wrapper.instance();

        MetaInfo fieldMetaInfo = wrapper.getMetaInfo("wtype2");
        Wrapper fieldWrapper = WrapperFactory.getInstance().wrap(fieldMetaInfo);
        MetaInfo subFieldMetaInfo = fieldWrapper.getMetaInfo("gen");
        Wrapper subFieldWrapper = WrapperFactory.getInstance().wrap(subFieldMetaInfo);

        MetaInfo fieldMetaInfo2 = wrapper.getMetaInfo("wtype");
        Wrapper fieldWrapper2 = WrapperFactory.getInstance().wrap(fieldMetaInfo2);

        metaInfo.toString();
    }

}
