package pointclickcare.lish.clock.ui.Alarm;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false);
        View view = binding.getRoot();

        //alarmList = generateAlarmList();
        adapter = new AlarmListAdapter(getContext());
        //adapter.setSource(alarmList);

        updateList(generateAlarmList());

        binding.listAlarm.setAdapter(adapter);
        binding.listAlarm.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listAlarm.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return view;
    }

    private List<Alarm> generateAlarmList() {
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
                            cursor.getString(cursor.getColumnIndex("ALARM_DAYS")),
                            cursor.getInt(cursor.getColumnIndex("ALARM_STATUS")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                alarmList.add(alarm);
            }
        }
        return alarmList;
    }

    public void insertAlarm() {
        String uri = "content://pointclickcare.lish.clock.util.ClockContentProvider/alarms";
        Uri alarms = Uri.parse(uri);
        ContentResolver cr = getActivity().getContentResolver();
        ContentValues cv = new ContentValues();

        Alarm alarm = new Alarm();
        cv.put("ALARM_TIME", alarm.getTimeStr());
        cv.put("ALARM_DAYS", alarm.getDaysStr());
        cv.put("ALARM_STATUS", alarm.status.get());

        cr.insert(alarms, cv);

        alarmList.add(alarm);
        Toast.makeText(getContext(), "Alarm " + alarm.getTimeStr() + " is set", Toast.LENGTH_SHORT).show();

        updateList(alarmList);
    }

    private void updateList(List<Alarm> alarmList) {
        adapter.setSource(alarmList);
        adapter.notifyDataSetChanged();
    }
}
