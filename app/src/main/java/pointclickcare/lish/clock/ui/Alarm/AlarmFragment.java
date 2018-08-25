package pointclickcare.lish.clock.ui.Alarm;


import android.content.ContentResolver;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;
import pointclickcare.lish.clock.model.AlarmData;
import pointclickcare.lish.clock.model.Time;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false);
        View view = binding.getRoot();

        String uri = "content://pointclickcare.lish.clock.util.ClockContentProvider/alarms";
        Uri alarms = Uri.parse(uri);
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cursor = cr.query(alarms, null, null, null, null);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                // The Cursor is now set to the right position
                Alarm alarm = null;
                try {
                    alarm = new Alarm(new SimpleDateFormat("hh:mm").parse(cursor.getString(cursor.getColumnIndex("ALARM_TIME"))),
                            cursor.getInt(cursor.getColumnIndex("ALARM_STATUS")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                AlarmData alarmData = new AlarmData();
                alarm.setAlarmData(alarmData);

                alarmList.add(alarm);
            }
        }

        adapter = new AlarmListAdapter(getContext());
        adapter.setSource(alarmList);

        binding.listAlarm.setAdapter(adapter);
        binding.listAlarm.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listAlarm.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return view;
    }
}
