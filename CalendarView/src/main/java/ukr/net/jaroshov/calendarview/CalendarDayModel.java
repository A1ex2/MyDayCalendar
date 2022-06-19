package ukr.net.jaroshov.calendarview;

public class CalendarDayModel {
    public long timeinmilli;
    public int status = 0; //0->no color, 1->green, 2-> yellow
    public boolean start = false;
    public boolean currentDate = false;
    public String text;
    public DayItem dayItem = new DayItem();

    public boolean isCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(boolean currentDate) {
        this.currentDate = currentDate;
    }

    public DayItem getDayItem() {
        return dayItem;
    }

    public void setDayItem(DayItem dayItem) {
        this.dayItem = dayItem;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public CalendarDayModel(long timeinmilli) {
        this.timeinmilli = timeinmilli;
    }

    public long getTimeinmilli() {
        return timeinmilli;
    }

    public void setTimeinmilli(long timeinmilli) {
        this.timeinmilli = timeinmilli;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
