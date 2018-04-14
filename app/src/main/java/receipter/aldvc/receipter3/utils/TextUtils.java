package receipter.aldvc.receipter3.utils;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public final class TextUtils {

    public static final String DATE_WITH_TIME_FULL = "dd.MM.yyyy HH:mm";
    public static final String DATE_WITHOUT_TIME_FULL = "dd.MM.yyyy";
    public static final String SHORT_MONTH = "dd MMM";

    private TextUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence text) {
        return text == null || text.length() == 0;
    }

    public static String formatDate(Date date, String pattern) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatToolbarPeriod(Date from, Date to) {
        SimpleDateFormat dateFormat;
        if (DateUtils.isSameDay(from, to) && isToday(from)) {
            return "Сегодня";
        } else if (DateUtils.isSameDay(from, getStartOfYesterday())) {
            return "Вчера";
        } else if (DateUtils.isSameDay(from, to)) {
            dateFormat = new SimpleDateFormat(SHORT_MONTH);
            return dateFormat.format(from);
        } else if (isOneOrTwoMonthsDates(from, to)) {
            dateFormat = new SimpleDateFormat(SHORT_MONTH); // 5 мар. - 4 апр.
        } else {
            dateFormat = new SimpleDateFormat(DATE_WITHOUT_TIME_FULL);
        }
        return dateFormat.format(from) + " - " + dateFormat.format(to);

    }

    private static boolean isOneOrTwoMonthsDates(Date from, Date to) {
        return from.getMonth() == to.getMonth() || from.getMonth() + 1 == to.getMonth();
    }

    public static Date getStartOfYesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    public static boolean isToday(Date date) {
        return DateUtils.isSameDay(date, new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String stringDate, String pattern) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatPrice(int price) { // ₽
        return price / 100 + "," + price % 100 + "\u20BD";
    }

    public static Date getStartOfTheDay(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getEndOfTheDay(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }
}
