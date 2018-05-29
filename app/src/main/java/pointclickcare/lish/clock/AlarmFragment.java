package pointclickcare.lish.clock;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pointclickcare.lish.clock.databinding.FragmentAlarmBinding;

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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_alarm, container, false);

        alarmList = generateAlarmData(6);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false);
        View view = binding.getRoot();

        adapter = new AlarmListAdapter();
        adapter.setSource(alarmList);
        //adapter.setDaysString(getResources().getStringArray(R.array.daysString));

        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return view;
    }

    private List<Alarm> generateAlarmData(int count) {
        List<Alarm> alarmList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Alarm alarm = new Alarm();
            //alarm.time = new Date(new Date().getTime() + (long) (Math.random() * 10000));
            alarm.setTime("8:00");
            /*Boolean[] days = new Boolean[7];
            for (int j = 0; j < days.length; j++) {
                days[j] = (long) (Math.random() * 100) % 2 != 0;
            }*/
            //alarm.days = days;
            alarm.setPeriod("AM");
            alarm.setDays("Monday");
            alarmList.add(alarm);
        }
        return alarmList;
    }
}
