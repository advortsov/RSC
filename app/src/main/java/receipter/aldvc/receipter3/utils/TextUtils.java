package receipter.aldvc.receipter3.utils;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence text) {
        return text == null || text.length() == 0;
    }

    public static String formatDate(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return dateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String stringDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);
    }

    public static String formatPrice(int price) {
        return price / 100 + " руб. " + price % 100 + " коп.";
    }

}
