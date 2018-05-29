package pointclickcare.lish.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Alarm {
    private String time;
    private String period;
    private String days;

    public Alarm() {
        this.time = this.getTime();
        this.period = this.getPeriod();
        this.days = this.getDays();
    }

    public Alarm(String time, String period, String days) {
        this.time = time;
        this.period = period;
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
