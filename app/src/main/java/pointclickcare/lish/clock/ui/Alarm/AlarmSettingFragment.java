package pointclickcare.lish.clock.ui.Alarm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.FragmentAlarmSettingBinding;

import pointclickcare.lish.clock.util.Extra;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmSettingFragment extends Fragment{

    FragmentAlarmSettingBinding binding;
    private boolean[] days = new boolean[7];
    private String message;

    public AlarmSettingFragment() {
        // Required empty public constructor
    }

    public static AlarmSettingFragment newInstance(String message) {
        AlarmSettingFragment fragment = new AlarmSettingFragment();
        Bundle args = new Bundle();
        args.putString(Extra.ALARM, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.message = getArguments().getString(Extra.ALARM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm_setting, container, false);

        binding = FragmentAlarmSettingBinding.inflate(getLayoutInflater());


        Toast.makeText(getActivity(), "ok", Toast.LENGTH_LONG);
        //binding.daysSetting.setOnClickListener(this.binding);


        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> binding,
                                            View itemView,
                                            int position,
                                            long id) {
                        Toast.makeText(getActivity(), "ok", Toast.LENGTH_LONG);

                    }};

        return view;
    }
}
