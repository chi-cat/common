package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.StringUtils;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.ReflectiveException;
import top.flyfire.common.reflect.metainfo.*;

import java.util.*;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class WrapperFactory {

    public final static Wrapper<?> wrap(MetaInfo metaInfo){
        if(metaInfo instanceof ClassMetaInfo){
            final ClassMetaInfo classMetaInfo = (ClassMetaInfo) metaInfo;
            Class<?> clzz =  classMetaInfo.getRawType();
            if(List.class.isAssignableFrom(clzz)){

            }else if(Map.class.isAssignableFrom(clzz)){

            }else {
                return new Wrapper<String>() {

                    @Override
                    public Object instance() {
                        return classMetaInfo.newInstance();
                    }

                    @Override
                    public MetaInfo getMetaInfo(String s) {
                        FieldMetaInfo field = classMetaInfo.getField(s);
                        if (field == null) {
                            return MetaInfo.NULL;
                        } else {
                            return field.getType();
                        }
                    }

                    @Override
                    public void set(String s, Object instance, Object val) {
                        FieldMetaInfo field = classMetaInfo.getField(s);
                        if (field == null) {
                            throw new ReflectiveException(StringUtils.merge("Field[", s, "] isn't exists..."));
                        } else {
                            field.invokeSetter(instance, val);
                        }
                    }

                    @Override
                    public Object rawValue(Object instance) {
                        return instance;
                    }
                };
            }
        }else if(metaInfo instanceof ParameterizedMetaInfo){
            return wrap(((ParameterizedMetaInfo) metaInfo).asClassMetaInfo());
        }else if(metaInfo instanceof WildcardMetaInfo){
            WildcardMetaInfo wildcardMetaInfo = (WildcardMetaInfo) metaInfo;
            MetaInfo bound;
            if(!MetaInfo.NULL.equals(bound = wildcardMetaInfo.getLowerBound())){
                return wrap(bound);
            }else if(MetaInfo.NULL.equals(bound = wildcardMetaInfo.getUpperBound())){
                return wrap(ReflectUtils.unWrap(Object.class));
            }else{
                return wrap(bound);
            }
        }else if(metaInfo instanceof VariableMetaInfo){
            VariableMetaInfo variableMetaInfo = (VariableMetaInfo) metaInfo;
            MetaInfo bound;
            if(MetaInfo.NULL.equals(bound = variableMetaInfo.getBound())){
                return wrap(ReflectUtils.unWrap(Object.class));
            }else{
                return wrap(bound);
            }
        }else if(metaInfo instanceof ArrayMetaInfo){
            final ArrayMetaInfo arrayMetaInfo = (ArrayMetaInfo) metaInfo;
            return new Wrapper<Integer>() {
                @Override
                public Object instance() {
                    return new ArrayList<>();
                }

                @Override
                public MetaInfo getMetaInfo(Integer integer) {
                    return arrayMetaInfo.getGenericComponentType();
                }

                @Override
                public void set(Integer s, Object instance, Object val) {
                    ((List)instance).add(s,val);
                }

                @Override
                public Object rawValue(Object instance) {
                    return  ((List)instance).toArray();
                }
            };
        }

        throw new ReflectiveException();
    }

}
