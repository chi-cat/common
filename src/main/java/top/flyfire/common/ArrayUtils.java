package top.flyfire.common;

import java.lang.reflect.Array;
import java.util.Collection;

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

}
