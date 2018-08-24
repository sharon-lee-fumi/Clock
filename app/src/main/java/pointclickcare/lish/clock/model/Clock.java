package pointclickcare.lish.clock.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Clock {
    private Date dateTime;

    public Clock() {
        this.dateTime = new Date();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeStr() {
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(dateTime);
    }

    public String getAmPmStr() {
        return new SimpleDateFormat("a", Locale.getDefault()).format(dateTime);
    }

    public String getDateStr() {
        return new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault()).format(dateTime);
    }
}
