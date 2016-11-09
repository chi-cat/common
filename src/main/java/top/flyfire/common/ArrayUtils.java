package top.flyfire.common;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/25.
 */
public class ArrayUtils {

    public static <T> boolean isNull(T[] arr){
        return ObjectUtils.isNull(arr);
    }

    public static <T> boolean isNotNull(T[] arr){
        return ObjectUtils.isNotNull(arr);
    }

    public static <T> boolean isEmpty(T[] arr){
        return ObjectUtils.isNull(arr)||arr.length==0;
    }

    public static <T> boolean isNotEmpty(T[] arr){
        return ObjectUtils.isNotNull(arr)&&arr.length>0;
    }

    public static <T> T[] newArray(Class<T> type,int length){
        return (T[])Array.newInstance(type,length);
    }


    public static <T> void arrayCopy(T[] srcArr,int srcStart,T[] destArr,int destStart,int length){
        System.arraycopy(srcArr,srcStart,destArr,destStart,length);
    }

    public static <K,V> Map<K,V> arrayToMap(V[] srcArr,MiddleWare<V,K,V> middleWare){
        Map<K,V> map = new HashMap<>();
        for(V v : srcArr){
            map.put(middleWare.getKey(v),middleWare.getValue(v));
        }
        return map;
    }

    public interface MiddleWare<O,K,V>{
        K getKey(O o);
        V getValue(O o);
    }

    public static <T> T first(T[] arr){
        if(isEmpty(arr)){
            return null;
        }else{
            return arr[0];
        }
    }

    public static <T> T last(T[] arr){
        if(isEmpty(arr)){
            return null;
        }else{
            return arr[arr.length-1];
        }
    }

}
