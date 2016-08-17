package top.flyfire.common.reflect.inst;

import top.flyfire.common.ObjectUtils;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.metainfo.*;

import java.lang.reflect.GenericArrayType;
import java.util.Collection;
import java.util.Map;

/**
 * Created by shyy_work on 2016/7/31.
 */
public abstract class InstWrapper<M extends MetaInfo,K,V> {

    public final static InstWrapper getInstance(MetaInfo metaInfo){
        if(metaInfo instanceof ClassMetaInfo){
            Class clzz = ((ClassMetaInfo) metaInfo).getRawType();
            if(ReflectUtils.isInterface(clzz)){
                //接口从接口注册器获取实现类型；否则抛出异常
            }else if(ReflectUtils.isJdkDataType(clzz)){
                //JDK数据类型
                if(Map.class.isAssignableFrom(clzz)){
                    //1.Map子类
                    return new StructedInstWrapper(ReflectUtils.newInstance(clzz),metaInfo) {
                        MetaInfo valueType;
                        @Override
                        public void put(Object key, Object value) {
                            ((Map)proxyVal).put(key,value);
                        }

                        @Override
                        public MetaInfo getType(Object key) {
                            return MetaInfo.NULL;
                        }

                        @Override
                        public Object getValue() {
                            return proxyVal;
                        }
                    };
                }else if(Collection.class.isAssignableFrom(clzz)){
                    //2.Collection子类
                    return new IndexedInstWrapper(ReflectUtils.newInstance(clzz),metaInfo ){
                        @Override
                        public void put(Object key, Object value) {
                            ((Collection)proxyVal).add(value);
                        }

                        @Override
                        public MetaInfo getType(Object key) {
                            return MetaInfo.NULL;
                        }

                        @Override
                        public Object getValue() {
                            return proxyVal;
                        }
                    };
                }else{
                    //3.Primitive
                }
            }else{
                //用户自定义Class
                return new StructedInstWrapper<ClassMetaInfo>(ReflectUtils.newInstance(clzz), (ClassMetaInfo) metaInfo) {

                    @Override
                    public MetaInfo getType(String key) {
                        FieldMetaInfo filed = metaInfo.getField(key);
                        if(ObjectUtils.isNotNull(filed)) {
                            return filed.getType();
                        }
                        return MetaInfo.NULL;
                    }

                    @Override
                    public void put(String key, Object value) {
                        FieldMetaInfo filed = metaInfo.getField(key);
                        if(ObjectUtils.isNotNull(filed)) {
                            filed.invokeSetter(proxyVal, value);
                        }
                    }

                    @Override
                    public Object getValue() {
                        return proxyVal;
                    }
                };
            }
        }else if(metaInfo instanceof ParameterizedMetaInfo){

        }else if(metaInfo instanceof VariableMetaInfo){

        }else if(metaInfo instanceof WildcardMetaInfo){

        }else if (metaInfo instanceof GenericArrayType){

        }else {

        }
        return null;
    }


    public InstWrapper(Object proxyVal,M metaInfo) {
        this.proxyVal = proxyVal;
        this.metaInfo = metaInfo;
    }

    protected Object proxyVal;

    protected M metaInfo;

    public abstract void put(K key,V value);

    public abstract MetaInfo getType(K key);

    public abstract Object getValue();

}
