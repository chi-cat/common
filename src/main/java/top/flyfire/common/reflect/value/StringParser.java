package top.flyfire.common.reflect.value;

/**
 * Created by shyy_work on 2017/2/24.
 */
public class StringParser implements Parser<String,String> {

    @Override
    public String parse(String value) {
        return value;
    }
}
