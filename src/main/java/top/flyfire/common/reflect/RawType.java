package top.flyfire.common.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/7/29.
 */
public abstract class RawType<T> {

    private static final int INS = 0;

    public final Type getType(){
        Type type = this.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType){
            return ((ParameterizedType)type).getActualTypeArguments()[INS];
        }
        throw new IllegalArgumentException("type arguments is missing.");
    }

}
