package top.flyfire.common.reflect;

/**
 * Created by shyy_work on 2016/4/26.
 */
public class ReflectiveException extends RuntimeException {

    public ReflectiveException() {
    }

    public ReflectiveException(String message) {
        super(message);
    }

    public ReflectiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectiveException(Throwable cause) {
        super(cause);
    }

    public ReflectiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
