package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/8.
 */
public class ByteParser implements Parser<Byte,String> {
    @Override
    public Byte parse(String value) {
        return new Byte(value);
    }
}
