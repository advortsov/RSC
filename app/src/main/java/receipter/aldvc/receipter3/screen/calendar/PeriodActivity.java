package receipter.aldvc.receipter3.screen.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import receipter.aldvc.receipter3.R;
import receipter.aldvc.receipter3.utils.TextUtils;

/**
 * Created by advortsov.
 */
public class PeriodActivity extends AppCompatActivity {

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, PeriodActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.range_picker_activity);

        CalendarView calendarView = findViewById(R.id.calendarView);
        Button setPeriodBtn = findViewById(R.id.getDateButton);
        try {
            calendarView.setDate(new Date());
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        setPeriodBtn.setOnClickListener(v -> {
            List<Calendar> selectedDates = calendarView.getSelectedDates();
            Date from;
            Date to;
            if (selectedDates.size() == 1) {
                from = TextUtils.getStartOfTheDay(selectedDates.get(0).getTime());
                to = TextUtils.getEndOfTheDay(selectedDates.get(0).getTime());
            } else if (selectedDates.size() > 1) {
                from = TextUtils.getStartOfTheDay(selectedDates.get(0).getTime());
                to = TextUtils.getEndOfTheDay(selectedDates.get(selectedDates.size() - 1).getTime());
            } else { // хз почему так получается
                from = TextUtils.getStartOfTheDay(new Date());
                to = TextUtils.getEndOfTheDay(new Date());
            }

            Toast.makeText(getApplicationContext(),
                    from.getTime() + " - " + to.getTime(),
                    Toast.LENGTH_LONG).show();
            putResult(from, to);
            finish();
        });
    }

    public void putResult(Date from, Date to) {
        Intent intent = new Intent();
        intent.putExtra("from", from.getTime());
        intent.putExtra("to", to.getTime());
        setResult(RESULT_OK, intent);
    }

}