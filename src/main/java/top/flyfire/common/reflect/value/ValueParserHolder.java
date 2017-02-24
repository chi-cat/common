package top.flyfire.common.reflect.value;

import top.flyfire.common.MapUtils;
import top.flyfire.common.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public final class ValueParserHolder {

    private Map<Class, Parser> classValueParsers;

    private ValueParserHolder() {
        this.classValueParsers = MapUtils.megre(new HashMap(),DEFAULT_CLASS_VALUE_PARSERS);
    }

    private ValueParserHolder(Map<Class, Parser> classValueParsers) {
        this.classValueParsers = MapUtils.megre(new HashMap(),DEFAULT_CLASS_VALUE_PARSERS,classValueParsers);
    }

    public <T> void register(Class<T> clzz, Parser<T, ?> valueParser) {
        classValueParsers.put(clzz, valueParser);
    }

    public Parser apply(Class<?> clzz) {
        Parser valueParser = classValueParsers.get(clzz);
        if (valueParser == null) {
            throw new RuntimeException(StringUtils.merge("unsupport class : ", clzz.getName()));
        }
        return valueParser;
    }

    public static ValueParserHolder getInstance(){
        return new ValueParserHolder();
    }

    public static ValueParserHolder getInstance(Map classValueParsers){
        return new ValueParserHolder(classValueParsers);
    }

    static Map<Class, Parser> DEFAULT_CLASS_VALUE_PARSERS = new HashMap<>();

    static {
        DEFAULT_CLASS_VALUE_PARSERS.put(java.util.Date.class, new DateParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(java.sql.Date.class, new SqlDateParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Timestamp.class, new TimestampParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(BigDecimal.class, new BigDecimalParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(BigInteger.class, new BigIntegerParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Number.class, DEFAULT_CLASS_VALUE_PARSERS.get(BigDecimal.class));

        DEFAULT_CLASS_VALUE_PARSERS.put(Integer.class, new IntegerParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Short.class, new ShortParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Long.class, new LongParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Double.class, new DoubleParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Float.class, new FloatParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Boolean.class, new BooleanParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Byte.class, new ByteParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(Character.class, new CharParser());

        DEFAULT_CLASS_VALUE_PARSERS.put(String.class, new StringParser());

    }
}