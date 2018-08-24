package pointclickcare.lish.clock.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.ClockApplication;
import pointclickcare.lish.clock.R;

public class AlarmData extends BaseObservable {
    public final ObservableBoolean repeat = new ObservableBoolean();
    public final List<ObservableBoolean> days = new ArrayList<>();

    public AlarmData() {
        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
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
}
