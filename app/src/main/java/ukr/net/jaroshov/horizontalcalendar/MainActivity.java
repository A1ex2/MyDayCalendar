package ukr.net.jaroshov.horizontalcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ukr.net.jaroshov.calendarview.CalendarDayView;
import ukr.net.jaroshov.calendarview.Tools;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarDayView calendarView = findViewById(R.id.calendar);

        Calendar starttime = Calendar.getInstance();
        starttime.setTime(getStartOfDay(starttime.getTime()));

        Calendar endtime = Calendar.getInstance();
        endtime.setTime(getEndOfDay(endtime.getTime()));

        ArrayList datesToBeColored = new ArrayList();
        datesToBeColored.add(Tools.getFormattedDateToday());

        calendarView.setUpCalendar(starttime.getTimeInMillis(), endtime.getTimeInMillis(), datesToBeColored, new CalendarDayView.OnCalendarListener() {
            @Override
            public void onDateSelected(String date) {
                Toast.makeText(MainActivity.this, date + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static Date getStartOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);

        return cal.getTime();
    }
}