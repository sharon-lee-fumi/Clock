package pointclickcare.lish.clock.model;

import android.databinding.ObservableBoolean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface AlarmInterface {
    ObservableBoolean status = new ObservableBoolean();
    ObservableBoolean repeat = new ObservableBoolean();
    List<ObservableBoolean> days = new ArrayList<>();
    boolean selected = false;

    String[] getDaysArray(String daysString);
    String getTimeStr();
    String getAmPmStr();
    String getDaysStr();
    String getDaysBool(List<ObservableBoolean> days);
    int getHours();
    int getMinutes();
}
