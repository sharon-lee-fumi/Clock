package pointclickcare.lish.clock.model;

import android.databinding.ObservableBoolean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Alarm {
    public final ObservableBoolean status = new ObservableBoolean();
    public boolean selected;
    public AlarmData alarmData;
    private Date time;

    public Alarm() {
        this.alarmData = this.getAlarmData();
        this.time = new Date();
        this.status.set(false);
        this.selected = false;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimeStr() {
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(time);
    }

    public String getAmPmStr() {
        return new SimpleDateFormat("a", Locale.getDefault()).format(time);
    }

    public AlarmData getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(AlarmData alarmData) {
        this.alarmData = alarmData;
    }

    public int getHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinutes() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.MINUTE);
    }
}
