package top.flyfire.common.reflect.value;

import top.flyfire.common.CharPool;
import top.flyfire.common.StringUtils;

/**
 * Created by devll on 2016/11/8.
 */
public class CharParser implements Parser<Character,String> {

    @Override
    public Character parse(String value) {
        if(StringUtils.isEmpty(value)){
            return CharPool.EMPTY;
        }else{
            return value.charAt(0);
        }
    }
}
