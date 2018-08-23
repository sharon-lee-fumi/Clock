package pointclickcare.lish.clock.ui.Timer;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentTimerBinding;
import pointclickcare.lish.clock.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends MainActivity.PlaceholderFragment {
    FragmentTimerBinding binding;

    public TimerFragment() {
        // Required empty public constructor
    }

    public static TimerFragment getInstance() {
        return new TimerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false);
        View view = binding.getRoot();

        Fragment setTimer = SetTimerFragment.getInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.timerPlaceholder, setTimer, "setTimer");
        transaction.commit();

        return view;
    }

}
