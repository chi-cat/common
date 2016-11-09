package top.flyfire.common.reflect.value;

import java.math.BigDecimal;

/**
 * Created by devll on 2016/11/8.
 */
public class BigDecimalParser implements Parser<BigDecimal,String> {
    @Override
    public BigDecimal parse(String value) {
        return new BigDecimal(value);
    }
}
