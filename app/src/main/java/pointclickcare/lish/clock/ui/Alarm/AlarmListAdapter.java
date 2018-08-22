package pointclickcare.lish.clock.ui.Alarm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.AlarmDayBinding;
import pointclickcare.lish.clock.databinding.ListAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;
import pointclickcare.lish.clock.model.AlarmData;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    List<Alarm> mAlarmList = new ArrayList<>();
    private List<ClickEventListener> observers = new ArrayList<>();

    public void setSource(List<Alarm> list) {
        mAlarmList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListAlarmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_alarm, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setAlarm(mAlarmList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAlarmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListAlarmBinding binding;

        public ViewHolder(View itemView, ListAlarmBinding binding) {
            super(itemView);
            this.binding = binding;

            itemView.setOnClickListener((view) -> {

                int position = getAdapterPosition();
                notifyClickEvent(itemView);
                if(mAlarmList.get(position).selected == true)
                {
                    mAlarmList.get(position).selected = false;
                    binding.setAlarmSettingView(mAlarmList.get(position).selected);
                }
                else
                {
                    for(int i = 0; i < getItemCount(); i++)
                    {
                        mAlarmList.get(i).selected = false;
                        observers.add(viewList -> binding.setAlarmSettingView(false));
                    }
                    mAlarmList.get(position).selected = true;
                    binding.setAlarmSettingView(mAlarmList.get(position).selected);

                    binding.alarmSetting.setAlarmDaysSetting(mAlarmList.get(position).alarmData);

                    binding.alarmSetting.alarmSettingRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                binding.alarmSetting.daysSetting.setVisibility(View.VISIBLE);
                            } else {
                                binding.alarmSetting.daysSetting.setVisibility(View.GONE);
                            }
                        }
                    });

                    AlarmDayBinding[] bindingsForAlarmDayButton = {binding.alarmSetting.alarmDayBtnSun,
                            binding.alarmSetting.alarmDayBtnMon, binding.alarmSetting.alarmDayBtnTue,
                            binding.alarmSetting.alarmDayBtnWed, binding.alarmSetting.alarmDayBtnThu,
                            binding.alarmSetting.alarmDayBtnFri, binding.alarmSetting.alarmDayBtnSat};


                    AlarmData ad =  mAlarmList.get(position).alarmData;
                    for (int i =0; i < ad.alarmDayBtns.length; i++)
                    {
                        int finalI = i;
                        bindingsForAlarmDayButton[i].alarmDaysContainer.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                bindingsForAlarmDayButton[finalI].setSelectedDay(
                                        !bindingsForAlarmDayButton[finalI].getSelectedDay());
                            }
                        });
                    }

                    binding.alarmSetting.btnHide.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            mAlarmList.get(position).selected = false;
                            binding.setAlarmSettingView(mAlarmList.get(position).selected);
                        }
                    });
                }
            });
        }
    }

    private void notifyClickEvent(View view) {
        for (ClickEventListener listener : observers) {
            listener.onClicked(view);
        }
    }

    interface ClickEventListener {
        void onClicked(View view);
    }
}
