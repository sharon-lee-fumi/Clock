package pointclickcare.lish.clock.ui.Timer;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentTimerBinding;
import pointclickcare.lish.clock.ui.MainActivity;
import pointclickcare.lish.clock.util.UiUtil;

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

        Fragment fragmentSetTimer = SetTimerFragment.getInstance();
        UiUtil.switchFragment(getActivity().getSupportFragmentManager(), R.id.timerPlaceholder, fragmentSetTimer);

        return view;
    }

}
