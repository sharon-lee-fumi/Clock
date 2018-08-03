package pointclickcare.lish.clock.ui.Clock.Time;


import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentClockBinding;
import pointclickcare.lish.clock.model.Clock;
import pointclickcare.lish.clock.model.Time;
import pointclickcare.lish.clock.model.ZoneTime;
import pointclickcare.lish.clock.ui.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFragment extends MainActivity.PlaceholderFragment {
    List<Time> timeList = new ArrayList<>();
    TimeListAdapter adapter;
    FragmentClockBinding binding;

    private SQLiteDatabase db;
    //private Cursor cursor;

    public ClockFragment() {
        // Required empty public constructor
    }

    public static ClockFragment getInstance() {
        return new ClockFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_clock, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clock, container, false);
        View view = binding.getRoot();
        Clock clock = new Clock();
        binding.setClock(clock);

        String uri = "content://pointclickcare.lish.clock.ui.Clock.Time.ClockContentProvider/zones";

        Uri zones = Uri.parse(uri);

        ContentResolver cr = getActivity().getContentResolver();

        Cursor cursor = cr.query(zones, null, null, null, "ZONE_NAME");

        if (cursor != null)
        {
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                // The Cursor is now set to the right position
                Time t = new Time(cursor.getString(cursor.getColumnIndex("ZONE_NAME")), cursor.getLong(cursor.getColumnIndex("GMT_OFFSET")));
                timeList.add(t);
            }
        }

        adapter = new TimeListAdapter();
        adapter.setSource(timeList);
        binding.listTime.setAdapter(adapter);
        binding.listTime.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}