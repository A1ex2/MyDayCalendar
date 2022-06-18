package ukr.net.jaroshov.calendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarDayView extends LinearLayout {
    Context context;
    AttributeSet attributeSet;
    RecyclerView recyclerView;

    public interface OnCalendarListener {
        void onDateSelected(DayItem dayItem);
    }

    public CalendarDayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attributeSet = attrs;
        init();
    }

    public CalendarDayView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CalendarDayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attributeSet = attrs;
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.list_day, null);

        TextView textView = view.findViewById(R.id.text);
        recyclerView = view.findViewById(R.id.re);

        view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (attributeSet != null) {
            TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.CalendarDayView);
            textView.setText(attrs.getString(R.styleable.CalendarDayView_text));
            attrs.recycle();
        } else {
            textView.setText("No Text Provided");
        }
        textView.setVisibility(GONE);

        addView(view);
    }

    public void setUpCalendar(Date date, ArrayList<DayItem> dayItems, int gradation, OnCalendarListener onCalendarListener) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(getStartOfDay(date));
        long start = startTime.getTimeInMillis();

        Calendar endTime = Calendar.getInstance();
        endTime.setTime(getEndOfDay(date));
        long end = endTime.getTimeInMillis();

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(start);

        ArrayList<CalendarDayModel> list = new ArrayList<>();

        long today = new Date().getTime();

        long current = start;
        int i = 0;
        int pos = 0;
        int id = 0;
        int firstRecording = 0;
        while (current <= end) {

            Calendar c1 = Calendar.getInstance();
            c1.setTimeInMillis(start);
            c1.add(Calendar.MINUTE, i);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            long t1 = c1.getTimeInMillis();

            Calendar c2 = Calendar.getInstance();
            c2.setTimeInMillis(t1);
            c2.add(Calendar.MINUTE, gradation);
            long t2 = c2.getTimeInMillis();

            CalendarDayModel model = new CalendarDayModel(c1.getTimeInMillis());
            if (t1 <= today && t2 > today) {
                pos = id;
                model.setStatus(1);
                model.setCurrentDate(true);
            }

            model.setText("");
            model.setStart(false);
            for (int j = 0; j < dayItems.size(); j++) {

                DayItem dayItem = dayItems.get(j);
                if (dayItem.date.getTime() <= t1 && dayItem.dateEnd.getTime() > t1
                        || t1 <= dayItem.date.getTime() && t2 > dayItem.date.getTime()) {
                    if (dayItem.start) {
                        model.setText(dayItem.text);
                        model.setStart(true);
                        dayItem.start = false;
                    } else {
                        model.setText("");
                    }
                    model.setStatus(2);
                    model.setDayItem(dayItem);
                    if (firstRecording == 0) {
                        firstRecording = id;
                    }
                    break;
                }
            }

            list.add(model);

            current = c1.getTimeInMillis();
            i += gradation;
            id++;
            Log.d("Setting data", sdf.format(c1.getTimeInMillis()));
        }

        CalendarDayAdapter adapter = new CalendarDayAdapter(list, context);
        adapter.setOnCalendarListener(onCalendarListener);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (pos == 0) {
            recyclerView.scrollToPosition(firstRecording);
        } else {
            recyclerView.scrollToPosition(pos);
        }
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
