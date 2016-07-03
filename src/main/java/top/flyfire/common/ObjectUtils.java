package top.flyfire.common;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/19.
 */
public class ObjectUtils {

    public static boolean isNull(Object object){
        return null==object;
    }

    public static  boolean isNull(Object...objects){
        for(int i = 0;i<objects.length;i++){
            if(isNotNull(objects[i]))return false;
        }
        return true;
    }

    public static boolean isNotNull(Object object){
        return null!=object;
    }

    public static boolean isNotNull(Object...objects){
        for(int i = 0;i<objects.length;i++){
            if(isNull(objects[i]))return false;
        }
        return true;
    }

    public static boolean equals(Object o1,Object o2){
        return o1==o2?true:o1==null||o2==null?false:o1.hashCode()==o2.hashCode()?true:o1.equals(o2);
    }

    public static <T> boolean validate(T object,Validator<T> validator){
        if(isNull(object))
            return false;
        if(!validator.complie){
            synchronized (validator){
                if(!validator.complie) validator.complie();
            }
        }
        return validator.validate(object);
    }

}
