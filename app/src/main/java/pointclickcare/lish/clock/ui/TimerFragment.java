package pointclickcare.lish.clock.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;
import android.widget.AdapterView;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentTimerBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends MainActivity.PlaceholderFragment {
    FragmentTimerBinding binding;
    private String[] timer = {"0", "0", "0", "0", "0", "0"};

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
        //return inflater.inflate(R.layout.fragment_timer, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false);
        View view = binding.getRoot();

        binding.btn1.setOnClickListener(
                viewBtn ->{
                    update("1");
                }
        );

        binding.btn2.setOnClickListener(
                viewBtn ->{
                    update("2");
                }
        );

        binding.btn3.setOnClickListener(
                viewBtn ->{
                    update("3");
                }
        );

        binding.btn4.setOnClickListener(
                viewBtn ->{
                    update("4");
                }
        );

        binding.btn5.setOnClickListener(
                viewBtn ->{
                    update("5");
                }
        );

        binding.btn6.setOnClickListener(
                viewBtn ->{
                    update("6");
                }
        );

        binding.btn7.setOnClickListener(
                viewBtn ->{
                    update("7");
                }
        );

        binding.btn8.setOnClickListener(
                viewBtn ->{
                    update("8");
                }
        );

        binding.btn9.setOnClickListener(
                viewBtn ->{
                    update("9");
                }
        );

        binding.btn0.setOnClickListener(
                viewBtn ->{
                    update("0");
                }
        );

        binding.btnDelete.setOnClickListener(
                viewBtn ->{
                    delete();
                }
        );
        return view;
    }

    private void update(String btn)
    {
        if (timer[5].equals("0"))
        {
            for (int i = timer.length - 1; i > 0; i--) {
                timer[i] = timer[i - 1];
            }
            timer[0] = btn;
            binding.s.setText(timer[1] + timer[0]);
            binding.m.setText(timer[3] + timer[2]);
            binding.h.setText(timer[5] + timer[4]);
        }
    }

    private void delete()
    {
        for (int i = 1; i < timer.length; i++) {
            timer[i-1] = timer[i];
        }
        timer[5] = "0";
        binding.s.setText(timer[1] + timer[0]);
        binding.m.setText(timer[3] + timer[2]);
        binding.h.setText(timer[5] + timer[4]);
    }

}
