package top.flyfire.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/30.
 */
public class DateUtils {

    public static String DATEFORMAT = "yyyy-MM-dd";

    private static ThreadLocal<SimpleDateFormat> DATEFORMATTHREADLOCAL = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMAT);
        }
    };

    public final static boolean isNull(Date date){
        return ObjectUtils.isNull(date);
    }

    public final static boolean isNotNull(Date date){
        return ObjectUtils.isNotNull(date);
    }

    public final static String format(Date date){
        return DATEFORMATTHREADLOCAL.get().format(date);
    }

    public final static Date parse(String date){
        try {
            return DATEFORMATTHREADLOCAL.get().parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
