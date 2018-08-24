package pointclickcare.lish.clock.ui.Alarm;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;
import pointclickcare.lish.clock.model.AlarmData;
import pointclickcare.lish.clock.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends MainActivity.PlaceholderFragment {

    List<Alarm> alarmList = new ArrayList<>();
    AlarmListAdapter adapter;
    FragmentAlarmBinding binding;

    public AlarmFragment() {
        // Required empty public constructor
    }

    public static AlarmFragment getInstance() {
        return new AlarmFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        alarmList = generateAlarmData(6);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false);
        View view = binding.getRoot();

        adapter = new AlarmListAdapter(getContext());
        adapter.setSource(alarmList);

        binding.listAlarm.setAdapter(adapter);
        binding.listAlarm.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listAlarm.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        String message = "Tue";

        Fragment fragment = AlarmSettingFragment.newInstance(message);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.commit();
        return view;
    }

    private List<Alarm> generateAlarmData(int count) {
        List<Alarm> alarmList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Alarm alarm = new Alarm();
            AlarmData alarmData = new AlarmData();
            alarm.setAlarmData(alarmData);

            alarm.status.set(true);
            alarm.setTime(new Date(new Date().getTime() + (long) (Math.random() * 1000 * 23 * 3600)));
            alarmList.add(alarm);
        }
        return alarmList;
    }
}
