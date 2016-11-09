package top.flyfire.common;

import org.junit.Assert;
import org.junit.Test;
import top.flyfire.common.annotation.Failure;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/20.
 */
public class StringUtilsTest {

    @org.junit.Test
    public void testIsNull() throws Exception {
        System.out.println((char) ('b' - 32));
        assertTrue(StringUtils.isNull(null));
    }

    @org.junit.Test
    public void testIsNotNull() throws Exception {
        assertTrue(StringUtils.isNotNull(""));
    }

    @org.junit.Test
    public void testIsEmpty() throws Exception {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
    }

    @org.junit.Test
    public void testIsNotEmpty() throws Exception {
        assertTrue(StringUtils.isNotEmpty("1"));
    }

    @org.junit.Test
    public void testIsDigit() throws Exception {
        assertTrue(StringUtils.isDigit("123123"));
    }

    @org.junit.Test
    public void testIsNumric() throws Exception {
        assertTrue(StringUtils.isNumric("123123"));
        assertTrue(StringUtils.isNumric("123123.3"));
        assertTrue(StringUtils.isNumric("+123123.3"));
        assertTrue(StringUtils.isNumric("-123123.3"));
        assertTrue(StringUtils.isNumric("-123123.3234"));
    }

    @org.junit.Test
    public void testIsUrl() throws Exception {
        assertTrue(StringUtils.isUrl("http://123123"));
        assertTrue(StringUtils.isUrl("ftp://123123/23234/"));
        assertTrue(StringUtils.isUrl("ftp://123123/23234"));
    }

    @org.junit.Test
    public void testIsEmail() throws Exception {
        assertTrue(StringUtils.isEmail("123123@qq.com"));
        assertTrue(StringUtils.isEmail("123123@qq.com.cn"));
    }

    @org.junit.Test
    public void testIsDate() throws Exception {
        assertTrue(StringUtils.isDate("2101-12-12 12:12:12"));
    }

    @org.junit.Test
    public void testIsCode() throws Exception {
        assertTrue(StringUtils.isCode("asdfaf_123"));
    }

    @Test
    public void testTimeConvert() throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c =Calendar.getInstance();
        Date date1 ,date2;
        System.out.println(sdf.format(c.getTime()));
        c.set(Calendar.YEAR, 2014);
        c.set(Calendar.MONTH,9);//从0开始，0表是1月，1表示2月依次类推
        c.set(Calendar.DAY_OF_MONTH,21);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,0);
        System.out.println(sdf.format(date1= c.getTime()));
        c.clear();
        c.set(Calendar.YEAR, 2016);
        c.set(Calendar.MONTH,9);//从0开始，0表是1月，1表示2月依次类推
        c.set(Calendar.DAY_OF_MONTH,21);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,0);
        System.out.println(sdf.format(date2= c.getTime()));
    }

    public static Timestamp valueOf(String s) {
        final int YEAR_LENGTH = 4;
        final int MONTH_LENGTH = 2;
        final int DAY_LENGTH = 2;
        final int MAX_MONTH = 12;
        final int MAX_DAY = 31;
        String date_s;
        String time_s;
        String nanos_s;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour;
        int minute;
        int second;
        int a_nanos = 0;
        int firstDash;
        int secondDash;
        int dividingSpace;
        int firstColon = 0;
        int secondColon = 0;
        int period = 0;
        String formatError = "Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]";
        String zeros = "000000000";
        String delimiterDate = "-";
        String delimiterTime = ":";

        if (s == null) throw new java.lang.IllegalArgumentException("null string");

        // Split the string into date and time components
        s = s.trim();
        dividingSpace = s.indexOf(' ');
        if (dividingSpace > 0) {
            date_s = s.substring(0, dividingSpace);
            time_s = s.substring(dividingSpace + 1);
        } else {
            throw new java.lang.IllegalArgumentException(formatError);
        }

        // Parse the date
        firstDash = date_s.indexOf('-');
        secondDash = date_s.indexOf('-', firstDash + 1);

        // Parse the time
        if (time_s == null)
            throw new java.lang.IllegalArgumentException(formatError);
        firstColon = time_s.indexOf(':');
        secondColon = time_s.indexOf(':', firstColon + 1);
        period = time_s.indexOf('.', secondColon + 1);

        // Convert the date
        boolean parsedDate = false;
        if ((firstDash > 0) && (secondDash > 0) && (secondDash < date_s.length() - 1)) {
            String yyyy = date_s.substring(0, firstDash);
            String mm = date_s.substring(firstDash + 1, secondDash);
            String dd = date_s.substring(secondDash + 1);
            if (yyyy.length() == YEAR_LENGTH &&
                    (mm.length() >= 1 && mm.length() <= MONTH_LENGTH) &&
                    (dd.length() >= 1 && dd.length() <= DAY_LENGTH)) {
                year = Integer.parseInt(yyyy);
                month = Integer.parseInt(mm);
                day = Integer.parseInt(dd);

                if ((month >= 1 && month <= MAX_MONTH) && (day >= 1 && day <= MAX_DAY)) {
                    parsedDate = true;
                }
            }
        }
        if (!parsedDate) {
            throw new java.lang.IllegalArgumentException(formatError);
        }

        // Convert the time; default missing nanos
        if ((firstColon > 0) & (secondColon > 0) &
                (secondColon < time_s.length() - 1)) {
            hour = Integer.parseInt(time_s.substring(0, firstColon));
            minute =
                    Integer.parseInt(time_s.substring(firstColon + 1, secondColon));
            if ((period > 0) & (period < time_s.length() - 1)) {
                second =
                        Integer.parseInt(time_s.substring(secondColon + 1, period));
                nanos_s = time_s.substring(period + 1);
                if (nanos_s.length() > 9)
                    throw new java.lang.IllegalArgumentException(formatError);
                if (!Character.isDigit(nanos_s.charAt(0)))
                    throw new java.lang.IllegalArgumentException(formatError);
                nanos_s = nanos_s + zeros.substring(0, 9 - nanos_s.length());
                a_nanos = Integer.parseInt(nanos_s);
            } else if (period > 0) {
                throw new java.lang.IllegalArgumentException(formatError);
            } else {
                second = Integer.parseInt(time_s.substring(secondColon + 1));
            }
        } else {
            throw new java.lang.IllegalArgumentException(formatError);
        }

        return new Timestamp(year - 1900, month - 1, day, hour, minute, second, a_nanos);
    }

}