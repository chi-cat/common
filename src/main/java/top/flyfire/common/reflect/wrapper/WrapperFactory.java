package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.StringUtils;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.ReflectiveException;
import top.flyfire.common.reflect.metainfo.*;
import top.flyfire.common.reflect.value.Parser;
import top.flyfire.common.reflect.value.ValueParserHolder;

import java.util.*;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class WrapperFactory {

    public final static Wrapper<?> wrap(MetaInfo metaInfo){
        if(metaInfo instanceof ClassMetaInfo){
            return wrap((ClassMetaInfo) metaInfo);
        }else if(metaInfo instanceof ParameterizedMetaInfo){
            return wrap((ParameterizedMetaInfo)metaInfo);
        }else if(metaInfo instanceof WildcardMetaInfo){
            return wrap((WildcardMetaInfo) metaInfo);
        }else if(metaInfo instanceof VariableMetaInfo){
            return wrap((VariableMetaInfo) metaInfo);
        }else if(metaInfo instanceof ArrayMetaInfo){
            return wrap((ArrayMetaInfo) metaInfo);
        }
        throw new ReflectiveException();
    }

    private final static Wrapper wrap(final ParameterizedMetaInfo parameterizedMetaInfo){
        ClassMetaInfo classMetaInfo = (ClassMetaInfo) parameterizedMetaInfo.getRawType();
        Class<?> clzz = classMetaInfo.getRawType();
        if(List.class.isAssignableFrom(clzz)){
            return new Wrapper<Integer>() {

                @Override
                public Object instance() {
                    return new ArrayList<>();
                }

                @Override
                public MetaInfo getMetaInfo(Integer integer) {
                    return parameterizedMetaInfo.getActualTypeArguments()[0];
                }

                @Override
                public void set(Integer s, Object instance, Object val) {
                    ((List)instance).add(s,val);
                }

                @Override
                public Object rawValue(Object instance) {
                    return instance;
                }
            };
        }else if(Map.class.isAssignableFrom(clzz)){
            return new Wrapper<String>() {
                @Override
                public Object instance() {
                    return new HashMap<>();
                }

                @Override
                public MetaInfo getMetaInfo(String s) {
                    return parameterizedMetaInfo.getActualTypeArguments()[1];
                }

                @Override
                public void set(String s, Object instance, Object val) {
                    ((Map)instance).put(s,val);
                }

                @Override
                public Object rawValue(Object instance) {
                    return instance;
                }
            };
        }else{
            return wrap(parameterizedMetaInfo.asClassMetaInfo());
        }
    }

    private final static Wrapper wrap(WildcardMetaInfo wildcardMetaInfo){
        MetaInfo bound;
        if(!MetaInfo.NULL.equals(bound = wildcardMetaInfo.getLowerBound())){
            return wrap(bound);
        }else if(MetaInfo.NULL.equals(bound = wildcardMetaInfo.getUpperBound())){
            return wrap(ReflectUtils.unWrap(Object.class));
        }else{
            return wrap(bound);
        }
    }

    private final static Wrapper wrap(VariableMetaInfo variableMetaInfo){
        MetaInfo bound;
        if(MetaInfo.NULL.equals(bound = variableMetaInfo.getBound())){
            return wrap(ReflectUtils.unWrap(Object.class));
        }else{
            return wrap(bound);
        }
    }

    private final static Wrapper wrap(final ArrayMetaInfo arrayMetaInfo){
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

    private final static Wrapper wrap(final ClassMetaInfo classMetaInfo){
        final Class<?> clzz =  classMetaInfo.getRawType();
        if(List.class.isAssignableFrom(clzz)){
            return new Wrapper<Integer>() {

                @Override
                public Object instance() {
                    return new ArrayList<>();
                }

                @Override
                public MetaInfo getMetaInfo(Integer integer) {
                    return ReflectUtils.unWrap(Object.class);
                }

                @Override
                public void set(Integer s, Object instance, Object val) {
                    ((List)instance).add(s,val);
                }

                @Override
                public Object rawValue(Object instance) {
                    return instance;
                }
            };
        }else if(Map.class.isAssignableFrom(clzz)){
            return new Wrapper<String>() {
                @Override
                public Object instance() {
                    return new HashMap<>();
                }

                @Override
                public MetaInfo getMetaInfo(String s) {
                    return ReflectUtils.unWrap(Object.class);
                }

                @Override
                public void set(String s, Object instance, Object val) {
                    ((Map)instance).put(s,val);
                }

                @Override
                public Object rawValue(Object instance) {
                    return instance;
                }
            };
        }else if(ReflectUtils.isJdkPrimitiveType(clzz)){
            return new Wrapper() {

                Parser valueParser = ValueParserHolder.apply(clzz);

                @Override
                public Object instance() {
                    return null;
                }

                @Override
                public MetaInfo getMetaInfo(Object o) {
                    return MetaInfo.NULL;
                }

                @Override
                public void set(Object s, Object instance, Object val) {

                }

                @Override
                public Object rawValue(Object instance) {
                    return valueParser.parse(instance);
                }
            };
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
                        throw new ReflectiveException(StringUtils.merge("Property[", s, "] isn't exists..."));
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
    }

}
