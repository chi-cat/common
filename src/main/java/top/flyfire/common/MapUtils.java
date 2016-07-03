package top.flyfire.common;


import java.util.Map;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/6/6.
 */
public class MapUtils {

    public static boolean isNull(Map map){
        return ObjectUtils.isNull(map);
    }

    public static boolean isNotNull(Map map){
        return ObjectUtils.isNotNull(map);
    }

    public static boolean isEmpty(Map map){
        return ObjectUtils.isNull(map)||map.size()==0;
    }

    public static boolean isNotEmpty(Map map){
        return ObjectUtils.isNotNull(map)&&map.size()>0;
    }
}
