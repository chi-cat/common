package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/8.
 */
public class DoubleParser implements Parser<Double,String> {
    @Override
    public Double parse(String value) {
        return new Double(value);
    }
}
