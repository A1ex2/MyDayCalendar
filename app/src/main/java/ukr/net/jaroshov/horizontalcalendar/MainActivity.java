package ukr.net.jaroshov.horizontalcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ukr.net.jaroshov.calendarview.CalendarDayModel;
import ukr.net.jaroshov.calendarview.CalendarDayView;
import ukr.net.jaroshov.calendarview.DayItem;
import ukr.net.jaroshov.calendarview.Tools;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarDayView calendarView = findViewById(R.id.calendar);

        Date date = new Date();

        ArrayList<DayItem> dateItems = new ArrayList();

        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            String strdate = "18-6-2022 08:00";
            String strdate2 = "18-6-2022 15:06";
            String strdate3 = "18-6-2022 18:30";
            String strdate4 = "18-6-2022 09:24";

            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            Date newdate3 = dateformat.parse(strdate3);
            Date newdate4 = dateformat.parse(strdate4);

            dateItems.add(new DayItem(newdate, 20, "Заметка 1", 0));
            dateItems.add(new DayItem(newdate2, 60, "Заметка 2", 0));
            dateItems.add(new DayItem(newdate3, 90, "Заметка 3", 0));
            dateItems.add(new DayItem(newdate4, 90, "Заметка 4", 0));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendarView.setUpCalendar(date, dateItems, 15, new CalendarDayView.OnCalendarListener() {
            @Override
            public void onDateSelected(DayItem dayItem) {
                Toast.makeText(MainActivity.this, dayItem.date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}