package pointclickcare.lish.clock.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import java.util.ArrayList;
import java.util.List;

public class AlarmData extends BaseObservable {
    public final ObservableBoolean status = new ObservableBoolean();
    //public final ObservableField<String> time = new ObservableField<>();
    //public final ObservableField<String> period = new ObservableField<>();
    public final ObservableBoolean repeat = new ObservableBoolean();
    public final List<ObservableBoolean> days = new ArrayList<>();

    public AlarmData() {
        for (int i = 0; i < 7; i++) {
            days.add(new ObservableBoolean());
        }
    }
}
