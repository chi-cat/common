package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/8.
 */
public class LongParser implements Parser<Long,String> {
    @Override
    public Long parse(String value) {
        return new Long(value);
    }
}
