package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.StringUtils;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectiveException;
import top.flyfire.common.reflect.metainfo.ClassMetaInfo;
import top.flyfire.common.reflect.metainfo.FieldMetaInfo;
import top.flyfire.common.reflect.metainfo.ParameterizedMetaInfo;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class WrapperFactory {

    public final static Wrapper<?> wrap(MetaInfo metaInfo){
        if(metaInfo instanceof ClassMetaInfo){
            final ClassMetaInfo classMetaInfo = (ClassMetaInfo) metaInfo;
            return new Wrapper<String>() {

                @Override
                public Object instance() {
                    return classMetaInfo.newInstance();
                }

                @Override
                public MetaInfo getMetaInfo(String s) {
                    FieldMetaInfo field = classMetaInfo.getField(s);
                    if(field==null){
                        return MetaInfo.NULL;
                    }else{
                        return field.getType();
                    }
                }

                @Override
                public void set(String s,Object instance, Object val) {
                    FieldMetaInfo field = classMetaInfo.getField(s);
                    if(field==null){
                        throw new ReflectiveException(StringUtils.merge("Field[",s,"] isn't exists..."));
                    }else{
                        field.invokeSetter(instance,val);
                    }
                }
            };
        }else if(metaInfo instanceof ParameterizedMetaInfo){
            
        }

        throw new ReflectiveException();
    }

}
