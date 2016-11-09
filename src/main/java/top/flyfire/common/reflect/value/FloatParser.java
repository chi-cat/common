package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/8.
 */
public class FloatParser implements Parser<Float,String> {
    @Override
    public Float parse(String value) {
        return new Float(value);
    }
}
