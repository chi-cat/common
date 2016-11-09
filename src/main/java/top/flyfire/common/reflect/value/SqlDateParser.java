package top.flyfire.common.reflect.value;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by devll on 2016/11/8.
 */
public class SqlDateParser implements Parser<Date,String> {

    @Override
    public Date parse(String value) {
        final int YEAR_LENGTH = 4;
        final int MONTH_LENGTH = 2;
        final int DAY_LENGTH = 2;
        final int MAX_MONTH = 12;
        final int MAX_DAY = 31;
        int firstDash;
        int secondDash;
        Date d = null;
        if (value == null) {
            throw new java.lang.IllegalArgumentException();
        }

        firstDash = value.indexOf('-');
        secondDash = value.indexOf('-', firstDash + 1);

        if ((firstDash > 0) && (secondDash > 0) && (secondDash < value.length() - 1)) {
            String yyyy = value.substring(0, firstDash);
            String mm = value.substring(firstDash + 1, secondDash);
            String dd = value.substring(secondDash + 1);
            if (yyyy.length() == YEAR_LENGTH &&
                    (mm.length() >= 1 && mm.length() <= MONTH_LENGTH) &&
                    (dd.length() >= 1 && dd.length() <= DAY_LENGTH)) {
                int year = Integer.parseInt(yyyy);
                int month = Integer.parseInt(mm);
                int day = Integer.parseInt(dd);

                if ((month >= 1 && month <= MAX_MONTH) && (day >= 1 && day <= MAX_DAY)) {
                    Calendar c = Calendar.getInstance();
                    c.clear();
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH,month-1);//从0开始，0表是1月，1表示2月依次类推
                    c.set(Calendar.DAY_OF_MONTH,day);
                    d = new Date(c.getTimeInMillis());
                }
            }
        }
        if (d == null) {
            throw new java.lang.IllegalArgumentException();
        }

        return d;
    }
}
