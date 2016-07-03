package top.flyfire.common;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/30.
 */
public class DateUtils {

    public final static boolean isNull(Date date){
        return ObjectUtils.isNull(date);
    }

    public final static boolean isNotNull(Date date){
        return ObjectUtils.isNotNull(date);
    }

}
