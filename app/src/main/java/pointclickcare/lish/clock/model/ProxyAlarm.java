package pointclickcare.lish.clock.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyAlarm extends BaseObservable implements AlarmInterface{

    private Alarm alarm;
    public final ObservableBoolean status = new ObservableBoolean();
    public final ObservableBoolean repeat = new ObservableBoolean();
    public final List<ObservableBoolean> days = new ArrayList<>();
    public boolean selected;
    public String strSeparator = " ";
    private Date time;

    public ProxyAlarm() {
        this.time = new Date();
        this.status.set(true);
        this.selected = false;
        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
    }

    public ProxyAlarm(Date time, String daysString, int status) {
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
            this.status.set(true);
        } else {
            this.status.set(false);
        }

        if (repeatFlag) {
            this.repeat.set(true);
        }

    }

    public String[] getDaysArray(String daysString) {
        if(alarm == null){
            alarm = new Alarm();
        }
        return  alarm.getDaysArray(daysString);
    }


    @Override
    public String getTimeStr() {
        if(alarm == null){
            alarm = new Alarm();
        }
        return alarm.getTimeStr();
    }

    @Override
    public String getAmPmStr() {
        if(alarm == null){
            alarm = new Alarm();
        }
        return alarm.getAmPmStr();
    }

    @Override
    public String getDaysStr() {
        if(alarm == null){
            alarm = new Alarm();
        }
        return alarm.getDaysStr();
    }

    @Override
    public String getDaysBool(List<ObservableBoolean> days) {
        if(alarm == null){
            alarm = new Alarm();
        }
        return alarm.getDaysBool(days);
    }

    @Override
    public int getHours() {
        if(alarm == null){
            alarm = new Alarm();
        }
        return alarm.getHours();
    }

    @Override
    public int getMinutes() {
        if(alarm == null){
            alarm = new Alarm();
        }
        return alarm.getMinutes();
    }
}
