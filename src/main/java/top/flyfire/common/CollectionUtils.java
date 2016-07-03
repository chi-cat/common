package top.flyfire.common;

import java.util.Collection;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/25.
 */
public class CollectionUtils {

    public static boolean isNull(Collection collection){
        return ObjectUtils.isNull(collection);
    }

    public static boolean isNotNull(Collection collection){
        return ObjectUtils.isNotNull(collection);
    }

    public static boolean isEmpty(Collection collection){
        return ObjectUtils.isNull(collection)||collection.size()==0;
    }

    public static boolean isNotEmpty(Collection collection){
        return ObjectUtils.isNotNull(collection)&&collection.size()>0;
    }


}
