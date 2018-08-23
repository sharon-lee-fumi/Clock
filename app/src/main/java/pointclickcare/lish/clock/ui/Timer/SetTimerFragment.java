package pointclickcare.lish.clock.ui.Timer;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentSetTimerBinding;
import pointclickcare.lish.clock.model.Timer;
import pointclickcare.lish.clock.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetTimerFragment extends MainActivity.PlaceholderFragment {
    public Timer timer;
    FragmentSetTimerBinding binding;
    private int[] displayTimer = {0, 0, 0, 0, 0, 0};
    private int hours;
    private int seconds;
    private int minutes;

    public SetTimerFragment() {
        // Required empty public constructor
    }

    public static SetTimerFragment getInstance() {
        return new SetTimerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_timer, container, false);
        View view = binding.getRoot();

        Button[] timerButtons = {binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5,
                binding.btn6, binding.btn7, binding.btn8, binding.btn9};
        for (int i = 0; i < timerButtons.length; i++) {
            int finalI = i;
            timerButtons[i].setOnClickListener(
                    viewBtn -> {
                        update(finalI);
                    }
            );
        }

        binding.btnDelete.setOnClickListener(
                viewBtn -> {
                    delete();
                }
        );
        return view;
    }

    private void update(int btn) {
        if (displayTimer[5] == 0) {
            for (int i = displayTimer.length - 1; i > 0; i--) {
                displayTimer[i] = displayTimer[i - 1];
            }
            displayTimer[0] = btn;
            binding.s.setText(String.format("%01d", displayTimer[1]) + String.format("%01d", displayTimer[0]));
            binding.m.setText(String.format("%01d", displayTimer[3]) + String.format("%01d", displayTimer[2]));
            binding.h.setText(String.format("%01d", displayTimer[5]) + String.format("%01d", displayTimer[4]));
        }
    }

    private void delete() {
        for (int i = 1; i < displayTimer.length; i++) {
            displayTimer[i - 1] = displayTimer[i];
        }
        displayTimer[5] = 0;
        binding.s.setText(String.format("%01d", displayTimer[1]) + String.format("%01d", displayTimer[0]));
        binding.m.setText(String.format("%01d", displayTimer[3]) + String.format("%01d", displayTimer[2]));
        binding.h.setText(String.format("%01d", displayTimer[5]) + String.format("%01d", displayTimer[4]));
    }

    public Timer getTimer() {
        hours = (displayTimer[5] * 10 + displayTimer[4]);
        minutes = (displayTimer[3] * 10 + displayTimer[2]);
        seconds = (displayTimer[1] * 10 + displayTimer[0]);
        timer = new Timer(seconds, minutes, hours);
        return timer;
    }

}
