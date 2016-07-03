package top.flyfire.common;

import java.util.IllegalFormatFlagsException;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/6/4.
 */
public interface ExceptionMsgPool {

    interface ReflectUtils{
        IllegalArgumentException unWrap$Type$ = new IllegalArgumentException("ReflectUtils.unWrap only accept Type[Class,ParameterizedType,WildcardType,GenericArrayType,TypeVariable]");
    }

    interface StringUtils{
        IllegalFormatFlagsException unWrapper$String$ = new IllegalFormatFlagsException("length>=2");
    }

    interface Common{
        NullPointerException nullPointerException = new NullPointerException();
    }


}
