package pointclickcare.lish.clock.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pointclickcare.lish.clock.ClockApplication;
import pointclickcare.lish.clock.R;

public class Alarm extends BaseObservable {
    public final ObservableBoolean status = new ObservableBoolean();
    public final ObservableBoolean repeat = new ObservableBoolean();
    public final List<ObservableBoolean> days = new ArrayList<>();
    public boolean selected;
    public AlarmData alarmData;
    private Date time;

    public Alarm() {
        this.alarmData = this.getAlarmData();
        this.time = new Date();
        this.status.set(false);
        this.selected = false;
        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
    }

    public Alarm(Date time, int status) {
        this.time = time;
        if (status == 1)
        {
            this.status.set(true);
        }
        else
        {
            this.status.set(false);
        }

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

    public String getDaysStr() {
        String[] daysString = ClockApplication.sInstance.getResources().getStringArray(R.array.daysString);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).get()) {
                sb.append(daysString[i] + " ");
            }
        }

        return sb.toString();
    }

    public List<ObservableBoolean> getDays() {
        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
        return days;
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
