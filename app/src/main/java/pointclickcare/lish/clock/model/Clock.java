package pointclickcare.lish.clock.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;

public class Clock {
    private String time;
    private String period;
    private String date;

    public Clock() {
        this.time = this.getFormattedTime();
        this.period = this.getFormattedPeriod();
        this.date = this.getFormattedDate();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFormattedTime() {
        Date currentDateTime = Calendar.getInstance().getTime();
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(currentDateTime);
    }

    public String getFormattedPeriod() {
        Date currentDateTime = Calendar.getInstance().getTime();
        return new SimpleDateFormat("a", Locale.getDefault()).format(currentDateTime);
    }

    public String getFormattedDate() {
        Date currentDateTime = Calendar.getInstance().getTime();
        return new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault()).format(currentDateTime);
    }
}
