package ukr.net.jaroshov.calendarview;

public class CalendarDayModel {
    long timeinmilli;
    int status=0; //0->no color, 1->green, 2-> yellow
    boolean start = false;
    String text;

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
