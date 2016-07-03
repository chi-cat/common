package top.flyfire.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/21.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotSupported {
    String causeBy() default "";
}
