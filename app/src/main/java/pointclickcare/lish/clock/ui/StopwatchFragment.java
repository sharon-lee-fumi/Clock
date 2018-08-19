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
    Long MillisecondTime = 0L;
    Long StartTime = 0L;
    Long TimeBuff = 0L;
    Long UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds ;

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

        handler = new Handler();
        binding.imgBtnRun.setOnClickListener(
                viewBtn ->{
                    if (!imgBtnRunStatus) {
                        StartTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);
                        imgBtnRunStatus = true;
                    }
                    else
                    {
                        TimeBuff += MillisecondTime;
                        handler.removeCallbacks(runnable);
                        imgBtnRunStatus = false;
                    }
                }
        );
        return view;
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 100);
            binding.stopwatchM.setText(String.format("%02d",Minutes));
            binding.stopwatchS.setText(":" + String.format("%02d",Seconds));
            binding.stopwatchMs.setText(String.format("%02d",MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}
