package pointclickcare.lish.clock.ui.Clock.Time;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentClockBinding;
import pointclickcare.lish.clock.model.Clock;
import pointclickcare.lish.clock.model.Zone;
import pointclickcare.lish.clock.ui.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFragment extends MainActivity.PlaceholderFragment {

    List<Zone> timeList = new ArrayList<>();
    TimeListAdapter adapter;
    FragmentClockBinding binding;

    private SQLiteDatabase db;
    private Cursor cursor;

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
        FragmentClockBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clock, container, false);
        View view = binding.getRoot();
        Clock clock = new Clock();
        binding.setClock(clock);

        SQLiteOpenHelper clockDatabaseHelper = new ClockDatabaseHelper(getActivity());


        try{
            db = clockDatabaseHelper.getReadableDatabase();

            cursor = db.query("zones",
                    new String[]{"_id", "ZONE_NAME"},  null, null, null, null, null);
/*            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(getActivity(),
                    binding.listTime.???,
                    cursor,
                    new String[]{"ZONE_NAME"},
                    new int[]{binding.???},
                    0);

                    setAdapter?????

                    */

        }
        catch(SQLiteException e)
        {
            Toast.makeText(getActivity(), R.string.db_error_message, Toast.LENGTH_SHORT);
        }



        adapter = new TimeListAdapter();
        adapter.setSource(timeList);
        binding.listTime.setAdapter(adapter);
        binding.listTime.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
