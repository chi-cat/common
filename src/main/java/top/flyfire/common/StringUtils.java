package top.flyfire.common;

import java.util.regex.Pattern;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/19.
 */
public class StringUtils implements CharPool {

    public static boolean isNull(String string) {
        return ObjectUtils.isNull(string);
    }

    public static boolean isNotNull(String string) {
        return ObjectUtils.isNotNull(string);
    }

    public static boolean isEmpty(String string) {
        return ObjectUtils.isNull(string) || string.length() == 0;
    }

    public static boolean isNotEmpty(String string) {
        return ObjectUtils.isNotNull(string) && string.length() > 0;
    }

    public static final String NULL = "null";

    public static String merge(Object...args){
        return megre(args,args.length);
    }

    public static String megre(Object[] args,int length){
        int charCount = 0;
        String[] items = new String[args.length];
        for(int i = 0,len = length;i<len;i++){
            charCount += (items[i]=(args[i]==null?NULL:args[i].toString())).length();
        }
        char[] value = new char[charCount];
        for(int i = 0,charBeign=0,charEnd,len = length;i<len;i++,charBeign+=charEnd){
            items[i].getChars(0,charEnd = items[i].length(),value,charBeign);
        }
        return new String(value);
    }

    public static String toLower(String string){
        char[] value = new char[string.length()];
        for(int i = 0,len = value.length;i<len;i++){
            value[i] = CharUtils.toLower(string.charAt(i));
        }
        return new String(value);
    }

    public static String toUpper(String string){
        char[] value = new char[string.length()];
        for(int i = 0,len = value.length;i<len;i++){
            value[i] = CharUtils.toUpper(string.charAt(i));
        }
        return new String(value);
    }

    public final static boolean isNullDescription(String string){
        return NULL.equals(string);
    }

    private final static Validator<String> DigitValidator = new Validator<String>() {
        @Override
        final boolean validate(String s) {
            for (int i = 0, c = s.charAt(i), len = s.length(); i < len; c = s.charAt(i++)) {
                if (CharUtils.isNotDigit(c)) return false;
            }
            return true;
        }
    };

    public static boolean isDigit(String string) {
        return StringUtils.isNotEmpty(string) && ObjectUtils.validate(string, DigitValidator);
    }

    private final static Validator<String> NumriceValidator = new Validator<String>() {
        @Override
        final boolean validate(String s) {
            int len = s.length();
            if (len == 1) {
                char c = s.charAt(0);
                return CharUtils.isDigit(c);
            } else {
                int cursor = 0;
                char c;
                boolean signed = false;
                if ((c = s.charAt(cursor)) == PLUS || c == MINUS) {
                    cursor++;
                    signed = true;
                }
                if ((!signed && c == POINT) || (signed && (c = s.charAt(cursor)) == POINT)) {
                    return false;
                } else if (CharUtils.isDigit(c)) {
                    len = len - 1;
                    while (++cursor < len) {
                        if (CharUtils.isNotDigit(c = s.charAt(cursor)) && c != POINT) {
                            return false;
                        }
                    }
                    return (CharUtils.isDigit(c = s.charAt(len)));
                } else {
                    return false;
                }

            }
        }
    };

    public static boolean isNumric(String string) {
        return isNotEmpty(string) && ObjectUtils.validate(string, NumriceValidator);
    }

    private static final Validator<String> UrlValidator = new Validator<String>() {

        Pattern validator;

        @Override
        final boolean validate(String s) {
            return validator.matcher(s).matches();
        }

        @Override
        public final void complie() {
            super.complie();
            validator = Pattern.compile("^(http|https|ftp|file|ed2k|thunder){0,1}://\\S{1,}$");
        }
    };

    public static boolean isUrl(String string) {
        return isNotEmpty(string) && ObjectUtils.validate(string, UrlValidator);
    }

    private static final Validator<String> EmailValidator = new Validator<String>() {

        Pattern validator;

        @Override
        final boolean validate(String s) {
            return validator.matcher(s).matches();
        }

        @Override
        public final void complie() {
            super.complie();
            validator = Pattern.compile("\\w+\\.?\\w+@\\w+(\\.(com|cn)){1,2}");
        }
    };

    public static boolean isEmail(String string) {
        return isNotEmpty(string) && ObjectUtils.validate(string, EmailValidator);
    }

    private static final Validator<String> DateValidator = new Validator<String>() {
        @Override
        final boolean validate(String s) {
            int cursor = 0, len = s.length(), _4 = 4, _7 = 7, _10 = 10, _13 = 13, _16 = 16;
            char c;
            if (len == 19) {
                if (((c = s.charAt(_4)) == '/' || c == '-') && c == s.charAt(_7)
                        && ((c = s.charAt(_10)) == ' ' || c == 'T')
                        && ((c = s.charAt(_13)) == ':' && c == s.charAt(_16))) {
                    while (++cursor < len) {
                        if (cursor == _4
                                || cursor == _7
                                || cursor == _10
                                || cursor == _13
                                || cursor == _16) continue;
                        if (CharUtils.isNotDigit(c = s.charAt(cursor))) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
    };

    public static boolean isDate(String string) {
        return isNotEmpty(string) && ObjectUtils.validate(string, DateValidator);
    }

    private static final Validator<String> CodeValidator = new Validator<String>() {
        @Override
        final boolean validate(String s) {
            for (int i = 0, c = s.charAt(i), len = s.length(); i < len; c = s.charAt(i++)) {
                if (CharUtils.isDigit(c) || CharUtils.isLetter(c) || c == UDLINE) continue;
                return false;
            }
            return true;
        }
    };

    public static boolean isCode(String string) {
        return isNotEmpty(string) && ObjectUtils.validate(string, CodeValidator);
    }

    public static char[] unWrapper(String string){
        int len = string.length(),ch;
        if(StringUtils.isNotNull(string)&&len>2&&(ch = string.charAt(0))=='"'&&ch==string.charAt(len-1)){
            char[] dest = new char[len-2];
            string.getChars(1,len-1,dest,0);
            return dest;
        }
        throw ExceptionMsgPool.StringUtils.unWrapper$String$;
    }

}
