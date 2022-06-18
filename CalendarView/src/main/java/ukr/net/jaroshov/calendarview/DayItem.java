package ukr.net.jaroshov.calendarview;

import java.util.Calendar;
import java.util.Date;

public class DayItem {
    public Date date;
    public Date dateEnd;
    public int durationMin = 0;
    public String text = "";
    public boolean start = true;

    public DayItem(Date date, int durationMin, String text) {
        this.date = date;
        this.durationMin = durationMin;
        this.text = text;
        this.dateEnd = getDateEnd(date, durationMin);
        this.start = true;
    }

    private Date getDateEnd(Date date, int durationMin) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime());
        c.add(Calendar.MINUTE, durationMin);
        return c.getTime();
    }
}
