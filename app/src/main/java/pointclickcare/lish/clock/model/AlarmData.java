package pointclickcare.lish.clock.model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import java.util.List;

public class AlarmData {
    //public final ObservableField<String> time = new ObservableField<>();
    //public final ObservableField<String> period = new ObservableField<>();
    public final List<ObservableField<Boolean>> days = new ObservableArrayList<>();

    public boolean[] alarmDayBtns = new boolean[7];

    public boolean[] getAlarmDayBtns(boolean[] alarmDaysSetting) {
        return alarmDayBtns;
    }

    public void setAlarmDayBtns(boolean[] alarmDayBtns) {
        this.alarmDayBtns = alarmDayBtns;
    }


    public final ObservableField<Boolean> status = new ObservableField<>();


}
