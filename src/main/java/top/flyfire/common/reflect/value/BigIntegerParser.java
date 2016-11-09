package top.flyfire.common.reflect.value;

import java.math.BigInteger;

/**
 * Created by devll on 2016/11/8.
 */
public class BigIntegerParser implements Parser<BigInteger,String> {
    @Override
    public BigInteger parse(String value) {
        return new BigInteger(value);
    }
}
