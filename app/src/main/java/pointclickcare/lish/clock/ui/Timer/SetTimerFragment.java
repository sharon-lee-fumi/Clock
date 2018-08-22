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
import pointclickcare.lish.clock.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetTimerFragment extends MainActivity.PlaceholderFragment {
    FragmentSetTimerBinding binding;
    private int[] timer = {0, 0, 0, 0, 0, 0};

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
        if (timer[5] == 0) {
            for (int i = timer.length - 1; i > 0; i--) {
                timer[i] = timer[i - 1];
            }
            timer[0] = btn;
            binding.s.setText(String.format("%01d", timer[1]) + String.format("%01d", timer[0]));
            binding.m.setText(String.format("%01d", timer[3]) + String.format("%01d", timer[2]));
            binding.h.setText(String.format("%01d", timer[5]) + String.format("%01d", timer[4]));
        }
    }

    private void delete() {
        for (int i = 1; i < timer.length; i++) {
            timer[i - 1] = timer[i];
        }
        timer[5] = 0;
        binding.s.setText(String.format("%01d", timer[1]) + String.format("%01d", timer[0]));
        binding.m.setText(String.format("%01d", timer[3]) + String.format("%01d", timer[2]));
        binding.h.setText(String.format("%01d", timer[5]) + String.format("%01d", timer[4]));
    }

}
