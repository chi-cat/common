package top.flyfire.common.reflect.value;

import top.flyfire.common.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public final class ValueParserHolder {

        public static <T> void apply(Class<T> clzz,Parser<T,?> valueParser){
            CLASS_VALUE_PARSER_MAP.put(clzz,valueParser);
        }

        public static Parser apply(Class<?> clzz){
            Parser valueParser = ValueParserHolder.CLASS_VALUE_PARSER_MAP.get(clzz);
            if(valueParser==null){
                throw new RuntimeException(StringUtils.merge("unsupport class : ",clzz.getName()));
            }
            return valueParser;
        }

        static Map<Class,Parser> CLASS_VALUE_PARSER_MAP = new HashMap<>();

        static {
            CLASS_VALUE_PARSER_MAP.put(java.util.Date.class,new DateParser());

            CLASS_VALUE_PARSER_MAP.put(java.sql.Date.class,new SqlDateParser());

            CLASS_VALUE_PARSER_MAP.put(Timestamp.class,new TimestampParser());

            CLASS_VALUE_PARSER_MAP.put(BigDecimal.class,new BigDecimalParser());

            CLASS_VALUE_PARSER_MAP.put(BigInteger.class,new BigIntegerParser());

            CLASS_VALUE_PARSER_MAP.put(Number.class,CLASS_VALUE_PARSER_MAP .get(BigDecimal.class));

            CLASS_VALUE_PARSER_MAP.put(Integer.class,new IntegerParser());

            CLASS_VALUE_PARSER_MAP.put(Short.class,new ShortParser());

            CLASS_VALUE_PARSER_MAP.put(Long.class,new LongParser());

            CLASS_VALUE_PARSER_MAP.put(Double.class,new DoubleParser());

            CLASS_VALUE_PARSER_MAP.put(Float.class,new FloatParser());

            CLASS_VALUE_PARSER_MAP.put(Boolean.class,new BooleanParser());

            CLASS_VALUE_PARSER_MAP.put(Byte.class,new ByteParser());

            CLASS_VALUE_PARSER_MAP.put(Character.class,new CharParser());

        }
    }