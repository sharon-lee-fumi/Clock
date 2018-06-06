package pointclickcare.lish.clock.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentStopwatchBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends MainActivity.PlaceholderFragment {


    public StopwatchFragment() {
        // Required empty public constructor
    }

    public static StopwatchFragment getInstance() {
        return new StopwatchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_stopwatch, container, false);
        FragmentStopwatchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stopwatch, container, false);
        View view = binding.getRoot();
        return view;
    }

}
