package pointclickcare.lish.clock.model;

import android.databinding.ObservableField;

public class AlarmData {
    public final ObservableField<String> time = new ObservableField<>();
    public final ObservableField<String> period = new ObservableField<>();
    public final ObservableField<boolean[]> days = new ObservableField<>();
    public final ObservableField<Boolean> status = new ObservableField<>();


}
