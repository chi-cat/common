package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/8.
 */
public class IntegerParser implements Parser<Integer,String> {

    @Override
    public Integer parse(String value) {
        return new Integer(value);
    }
}
