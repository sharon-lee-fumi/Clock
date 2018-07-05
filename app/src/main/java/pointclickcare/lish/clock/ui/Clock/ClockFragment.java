package pointclickcare.lish.clock.ui.Clock;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentClockBinding;
import pointclickcare.lish.clock.model.Clock;
import pointclickcare.lish.clock.ui.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFragment extends MainActivity.PlaceholderFragment {


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
        return view;
    }

}
