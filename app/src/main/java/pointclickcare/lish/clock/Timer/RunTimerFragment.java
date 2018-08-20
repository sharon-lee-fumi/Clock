package pointclickcare.lish.clock.Timer;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentRunTimerBinding;
import pointclickcare.lish.clock.model.Timer;
import pointclickcare.lish.clock.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RunTimerFragment extends MainActivity.PlaceholderFragment {
    FragmentRunTimerBinding binding;
    Long millisecondTime;
    Long startTime;
    Long timeBuff;
    Long updateTime;
    int seconds, minutes;

    boolean imgBtnTimerRunStatus = false;

    Handler handler;

    public RunTimerFragment() {
        // Required empty public constructor
    }

    public static RunTimerFragment newInstance() {
        RunTimerFragment runTimerFragment = new RunTimerFragment();
        Bundle args = new Bundle();
        return runTimerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_run_timer, container, false);
        View view = binding.getRoot();

        Timer timer = new Timer(19, 3);
        minutes = timer.getMinutes() * 60;
        seconds = timer.getSeconds();
        timeBuff = (minutes + seconds) * 1000L;

        binding.timerM.setText(String.format("%02d", minutes));
        binding.timerS.setText(String.format("%02d", seconds));



        binding.timerResetBtn.setVisibility(View.INVISIBLE);

        handler = new Handler();
        binding.imgBtnTimerRun.setOnClickListener(
                viewBtn ->{
                    if (!imgBtnTimerRunStatus) {
                        startTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);
                        imgBtnTimerRunStatus = true;
                        binding.timerResetBtn.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        timeBuff -= millisecondTime;
                        handler.removeCallbacks(runnable);
                        imgBtnTimerRunStatus = false;
                        binding.timerResetBtn.setVisibility(View.VISIBLE);
                    }
                }
        );

        binding.timerResetBtn.setOnClickListener(
                viewBtn ->{
                    binding.timerM.setText(String.format("%02d", timer.getMinutes()));
                    binding.timerS.setText(String.format("%02d", timer.getSeconds()));

                    minutes = timer.getMinutes() * 60;
                    seconds = timer.getSeconds();
                    timeBuff = (minutes + seconds) * 1000L;

                    handler.removeCallbacks(runnable);
                    imgBtnTimerRunStatus = false;
                }
        );

        return view;
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff - millisecondTime;
            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            binding.timerM.setText(String.format("%02d", minutes));
            binding.timerS.setText(String.format("%02d", seconds));

            handler.postDelayed(this, 0);
        }

    };
}
