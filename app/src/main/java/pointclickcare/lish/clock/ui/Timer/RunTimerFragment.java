package pointclickcare.lish.clock.ui.Timer;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentRunTimerBinding;
import pointclickcare.lish.clock.model.Timer;
import pointclickcare.lish.clock.ui.MainActivity;
import pointclickcare.lish.clock.util.Extras;

/**
 * A simple {@link Fragment} subclass.
 */
public class RunTimerFragment extends MainActivity.PlaceholderFragment {
    FragmentRunTimerBinding binding;
    Timer newTimer;
    Long millisecondTime;
    Long startTime;
    Long timeBuff;
    Long updateTime;
    int seconds, minutes, hours;

    boolean imgBtnTimerRunStatus = false;

    Handler handler;
    public Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff - millisecondTime;
            seconds = (int) (updateTime / 1000);
            hours = seconds / 3600;
            seconds = seconds % 3600;
            minutes = seconds / 60;
            seconds = seconds % 60;

            binding.timerH.setText(String.format("%02d", hours));
            binding.timerM.setText(String.format("%02d", minutes));
            binding.timerS.setText(String.format("%02d", seconds));

            handler.postDelayed(this, 0);
        }

    };

    public RunTimerFragment() {
        // Required empty public constructor
    }

    public static RunTimerFragment newInstance(Timer timer) {
        RunTimerFragment runTimerFragment = new RunTimerFragment();
        Bundle args = new Bundle();
        args.putSerializable(Extras.TIMER, timer);
        runTimerFragment.setArguments(args);
        return runTimerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_run_timer, container, false);
        View view = binding.getRoot();

        newTimer = (Timer) getArguments().getSerializable(Extras.TIMER);
        binding.setTimer(newTimer);
        hours = newTimer.getHours() * 3600;
        minutes = newTimer.getMinutes() * 60;
        seconds = newTimer.getSeconds();
        timeBuff = (hours + minutes + seconds) * 1000L;

        binding.timerResetBtn.setVisibility(View.INVISIBLE);

        handler = new Handler();
        binding.imgBtnTimerRun.setOnClickListener(
                viewBtn -> {
                    runTimer();
                }
        );

        binding.timerResetBtn.setOnClickListener(
                viewBtn -> {
                    binding.setTimer(newTimer);

                    hours = newTimer.getHours() * 3600;
                    minutes = newTimer.getMinutes() * 60;
                    seconds = newTimer.getSeconds();
                    timeBuff = (hours + minutes + seconds) * 1000L;

                    handler.removeCallbacks(runnable);
                    imgBtnTimerRunStatus = false;
                }
        );

        binding.timerDeleteBtn.setOnClickListener(
                viewBtn -> {
                    Fragment newTimer = TimerFragment.getInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.timerPlaceholder, newTimer, "newTimer");
                    transaction.commit();
                }
        );

        return view;
    }

    public void runTimer() {
        if (!imgBtnTimerRunStatus) {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            imgBtnTimerRunStatus = true;
            binding.timerResetBtn.setVisibility(View.INVISIBLE);
        } else {
            timeBuff -= millisecondTime;
            handler.removeCallbacks(runnable);
            imgBtnTimerRunStatus = false;
            binding.timerResetBtn.setVisibility(View.VISIBLE);
        }
    }

}
