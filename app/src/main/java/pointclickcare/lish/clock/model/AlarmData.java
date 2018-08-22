package pointclickcare.lish.clock.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

public class AlarmData extends BaseObservable {
    //public final ObservableField<String> time = new ObservableField<>();
    //public final ObservableField<String> period = new ObservableField<>();
    public final ObservableBoolean repeat = new ObservableBoolean();


    public final List<ObservableField<Boolean>> days = new ObservableArrayList<>();
    public final ObservableField<Boolean> status = new ObservableField<>();
    public boolean[] alarmDayBtns = new boolean[7];

    public boolean[] getAlarmDayBtns(boolean[] alarmDaysSetting) {
        return alarmDayBtns;
    }

    public void setAlarmDayBtns(boolean[] alarmDayBtns) {
        this.alarmDayBtns = alarmDayBtns;
    }


}
