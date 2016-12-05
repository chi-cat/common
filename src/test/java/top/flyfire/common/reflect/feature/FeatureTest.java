package top.flyfire.common.reflect.feature;

import org.junit.Test;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.RawType;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.metainfo.ClassMetaInfo;
import top.flyfire.common.reflect.metainfo.FieldMetaInfo;
import top.flyfire.common.reflect.wrapper.BuildInWrapper;
import top.flyfire.common.reflect.wrapper.InstanceWrapper;
import top.flyfire.common.reflect.wrapper.Wrapper;
import top.flyfire.common.reflect.wrapper.WrapperFactory;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class FeatureTest {

    public void common(MetaInfo metaInfo){
        ClassMetaInfo temp = null;
        Wrapper wrapper = WrapperFactory.wrap(metaInfo);
        Object instance = ((InstanceWrapper<String>) wrapper).instance();
//        wrapper.set("gen",instance,new BigDecimal(123));
        metaInfo.toString();
    }

    @Test
    public void baseTest(){
       common(ReflectUtils.getMetaInfo(Base.class));
    }

    @Test
    public void baseWithGenTest(){
        common(ReflectUtils.getMetaInfo(BaseWithGen.class));
    }

    @Test
    public void baseWidthGeanAsPdTest(){
        common(ReflectUtils.getMetaInfo(new RawType<BaseWithGen<Base>>(){}.getType()));
    }

    @Test
    public void wtTest(){
        MetaInfo metaInfo = ReflectUtils.getMetaInfo(WType.class);
        Wrapper wrapper = WrapperFactory.wrap(metaInfo);
        Object instance = ((InstanceWrapper) wrapper).instance();

        FieldMetaInfo fieldMetaInfo = ((BuildInWrapper) wrapper).getField("wtype2");
        Wrapper fieldWrapper = WrapperFactory.wrap(fieldMetaInfo.getType());
        FieldMetaInfo subFieldMetaInfo = ((BuildInWrapper) fieldWrapper).getField("gen");
        Wrapper subFieldWrapper = WrapperFactory.wrap(subFieldMetaInfo.getType());

        FieldMetaInfo fieldMetaInfo2 = ((BuildInWrapper) wrapper).getField("wtype");
        Wrapper fieldWrapper2 = WrapperFactory.wrap(fieldMetaInfo2.getType());

        metaInfo.toString();
    }

}
