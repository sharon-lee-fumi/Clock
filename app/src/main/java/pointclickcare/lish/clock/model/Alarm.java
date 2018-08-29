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
    public final ObservableBoolean onOff = new ObservableBoolean();
    public final ObservableBoolean repeat = new ObservableBoolean();
    public final List<ObservableBoolean> days = new ArrayList<>();
    public boolean selected;
    public String strSeparator = " ";
    private transient Integer alarmId;
    private Date time;

    public Alarm() {
        this.alarmId = -1;
        this.time = new Date();
        this.onOff.set(true);
        this.selected = false;
        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
    }

    public Alarm(Integer alarmId, Date time, String daysString, int status) {
        this.alarmId = alarmId;
        boolean repeatFlag = false;
        this.time = time;

        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
        String[] selectedDays = getDaysArray(daysString);

        for (int i = 0; i < selectedDays.length; i++) {
            if (selectedDays[i].equals("1")) {
                days.get(i).set(true);
                repeatFlag = true;
            } else {
                days.get(i).set(false);
            }
        }

        if (status == 1) {
            this.onOff.set(true);
        } else {
            this.onOff.set(false);
        }

        if (repeatFlag) {
            this.repeat.set(true);
        }
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
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
                sb.append(daysString[i] + strSeparator);
            }
        }
        return sb.toString();
    }

    public String getDaysBool(List<ObservableBoolean> days) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            if (days.get(i).get()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            if (i < days.size() - 1) {
                sb.append(strSeparator);
            }
        }
        return sb.toString();
    }

    public String[] getDaysArray(String daysString) {
        String[] daysArray = daysString.split(strSeparator);
        return daysArray;
    }

    public List<ObservableBoolean> getDays() {
        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
        return days;
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
