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
import pointclickcare.lish.clock.model.AlarmInterface;
import pointclickcare.lish.clock.model.ProxyAlarm;
import pointclickcare.lish.clock.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends MainActivity.PlaceholderFragment {

    //List<Alarm> alarmList = new ArrayList<>();
    List<AlarmInterface> alarmList = new ArrayList<>();
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

        adapter = new AlarmListAdapter(getContext(), AlarmFragment.this);
        updateList(generateAlarmList());

        binding.listAlarm.setAdapter(adapter);
        binding.listAlarm.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listAlarm.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return view;
    }

    public List<AlarmInterface> generateAlarmList() {
        alarmList.clear();
        String uri = "content://pointclickcare.lish.clock.util.ClockContentProvider/alarms";
        Uri alarms = Uri.parse(uri);
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cursor = cr.query(alarms, null, null, null, null);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                // The Cursor is now set to the right position
                AlarmInterface alarm = null;
                try {
                    //
                    alarm = new ProxyAlarm(new SimpleDateFormat("hh:mm").parse(cursor.getString(cursor.getColumnIndex("ALARM_TIME"))),
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

        //Alarm alarm = new Alarm();
        AlarmInterface alarm = new ProxyAlarm();
        cv.put("ALARM_TIME", alarm.getTimeStr());
        cv.put("ALARM_DAYS", alarm.getDaysStr());
        cv.put("ALARM_STATUS", alarm.status.get());
        cr.insert(alarms, cv);

        alarmList.add(alarm);
        Toast.makeText(getContext(), "Alarm " + alarm.getTimeStr() + " is set", Toast.LENGTH_SHORT).show();

        updateList(generateAlarmList());
    }

    public void updateList(List<AlarmInterface> alarmList) {
        adapter.setSource(alarmList);
        adapter.notifyDataSetChanged();
    }
}
