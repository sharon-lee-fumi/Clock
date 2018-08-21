package pointclickcare.lish.clock.ui;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
    FragmentStopwatchBinding binding;
    Long millisecondTime = 0L;
    Long startTime = 0L;
    Long timeBuff = 0L;
    Long updateTime = 0L;
    int seconds, minutes, milliSeconds;

    boolean imgBtnRunStatus = false;

    Handler handler;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stopwatch, container, false);
        View view = binding.getRoot();

        binding.resetBtn.setVisibility(View.INVISIBLE);

        handler = new Handler();
        binding.imgBtnRun.setOnClickListener(
                viewBtn ->{
                    runStopWatch();
                }
        );

        binding.resetBtn.setOnClickListener(
                viewBtn ->{
                    millisecondTime = 0L ;
                    startTime = 0L ;
                    timeBuff = 0L ;
                    updateTime = 0L ;
                    seconds = 0 ;
                    minutes = 0 ;
                    milliSeconds = 0 ;

                    binding.stopwatchM.setText("");
                    binding.stopwatchS.setText("0");
                    binding.stopwatchMs.setText("00");

                    handler.removeCallbacks(runnable);
                    imgBtnRunStatus = false;
                    binding.resetBtn.setVisibility(View.INVISIBLE);
                }
        );

        return view;
    }

    public void runStopWatch() {
        if (!imgBtnRunStatus) {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            imgBtnRunStatus = true;
        }
        else
        {
            timeBuff += millisecondTime;
            handler.removeCallbacks(runnable);
            imgBtnRunStatus = false;
        }
        binding.resetBtn.setVisibility(View.VISIBLE);
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            milliSeconds = (int) (updateTime % 100);
            binding.stopwatchM.setText(String.format("%02d", minutes));
            binding.stopwatchS.setText(":" + String.format("%02d", seconds));
            binding.stopwatchMs.setText(String.format("%02d", milliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}
