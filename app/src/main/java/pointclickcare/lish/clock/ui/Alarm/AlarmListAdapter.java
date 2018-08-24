package pointclickcare.lish.clock.ui.Alarm;

import android.databinding.DataBindingUtil;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.AlarmDayBinding;
import pointclickcare.lish.clock.databinding.ListAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    List<Alarm> mAlarmList = new ArrayList<>();
    private List<ClickEventListener> observers = new ArrayList<>();

    public void setSource(List<Alarm> list) {
        if (list == null) return;
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

    private void notifyClickEvent(View view) {
        for (ClickEventListener listener : observers) {
            listener.onClicked(view);
        }
    }

    interface ClickEventListener {
        void onClicked(View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListAlarmBinding binding;

        public ViewHolder(View itemView, ListAlarmBinding binding) {
            super(itemView);
            this.binding = binding;

            AlarmDayBinding[] bindingsForAlarmDayButton = {binding.alarmSetting.alarmDayBtnSun,
                    binding.alarmSetting.alarmDayBtnMon, binding.alarmSetting.alarmDayBtnTue,
                    binding.alarmSetting.alarmDayBtnWed, binding.alarmSetting.alarmDayBtnThu,
                    binding.alarmSetting.alarmDayBtnFri, binding.alarmSetting.alarmDayBtnSat};

            for (AlarmDayBinding alarmDayBinding : bindingsForAlarmDayButton) {
                alarmDayBinding.alarmDaysContainer.setOnClickListener(view -> {
                    Alarm alarm = mAlarmList.get(getAdapterPosition());
                    alarmDayBinding.cbSelect.setChecked(!alarmDayBinding.cbSelect.isChecked());
                    alarmDayBinding.setAlarmData(alarm.alarmData);
                });
            }

            observers.add(view -> toggleSettingView(view != itemView));
            itemView.setOnClickListener(view -> notifyClickEvent(view));
            binding.alarmSetting.btnCollapse.setOnClickListener(view -> toggleSettingView(false));
            binding.btnExpand.setOnClickListener(view -> toggleSettingView(false));

            binding.alarmTime.setOnClickListener(view -> {
                showTimePickerDialog(view);
                    }
            );
        }

        private void toggleSettingView(boolean forceCollapsing) {
            Alarm alarm = mAlarmList.get(getAdapterPosition());
            if (forceCollapsing) alarm.selected = false;
            else alarm.selected ^= true;
            binding.btnExpand.setVisibility(alarm.selected ? View.INVISIBLE : View.VISIBLE);
            binding.setAlarm(alarm);
        }

        public void showTimePickerDialog(View v) {
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
        }



    }
}
