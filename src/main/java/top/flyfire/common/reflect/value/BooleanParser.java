package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/8.
 */
public class BooleanParser implements Parser<Boolean,String> {

    @Override
    public Boolean parse(String value) {
        return new Boolean(value);
    }
}
