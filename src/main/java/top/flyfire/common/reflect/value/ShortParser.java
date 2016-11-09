package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/8.
 */
public class ShortParser implements Parser<Short,String> {
    @Override
    public Short parse(String value) {
        return new Short(value);
    }
}
