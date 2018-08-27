package pointclickcare.lish.clock.ui.Alarm;

import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.AlarmDayBinding;
import pointclickcare.lish.clock.databinding.ListAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    Context mContext;
    AlarmFragment mAlarmFragment;
    List<Alarm> mAlarmList = new ArrayList<>();
    int alarmPosition;
    private List<View.OnClickListener> observers = new ArrayList<>();

    public AlarmListAdapter(Context context, AlarmFragment alarmFragment) {
        mContext = context;
        mAlarmFragment = alarmFragment;
    }

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
        for (View.OnClickListener listener : observers) {
            listener.onClick(view);
        }
    }

    private void updateAlarmStatus(Alarm alarm, boolean status) {
        int i;
        if (status) {
            i = 1;
        } else {
            i = 0;
        }
        alarmPosition = mAlarmList.indexOf(alarm);
        int id = alarmPosition + 1;
        String uri = "content://pointclickcare.lish.clock.util.ClockContentProvider/alarms";
        Uri alarms = Uri.parse(uri + "/" + id);
        ContentResolver cr = mContext.getContentResolver();

        ContentValues cv = new ContentValues();
        cv.put("ALARM_STATUS", i);
        int returns = cr.update(alarms, cv, null, null);
        if (returns == 1) {
            Toast.makeText(mContext, "Alarm " + alarm.getTimeStr() + " is updated", Toast.LENGTH_SHORT).show();
            updateAlarmList();
        } else {
            Toast.makeText(mContext, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteAlarm(Alarm alarm) {
        alarmPosition = mAlarmList.indexOf(alarm);
        int id = alarmPosition + 1;

        String uri = "content://pointclickcare.lish.clock.util.ClockContentProvider/alarms";
        Uri alarms = Uri.parse(uri + "/" + id);
        ContentResolver cr = mContext.getContentResolver();
        int returns = cr.delete(alarms, null, null);
        if (returns == 1) {
            Toast.makeText(mContext, "Alarm " + alarm.getTimeStr() + " is deleted", Toast.LENGTH_SHORT).show();
            updateAlarmList();
        } else {
            Toast.makeText(mContext, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateDaysSetting(Alarm alarm) {
        alarmPosition = mAlarmList.indexOf(alarm);
        int id = alarmPosition + 1;
        String uri = "content://pointclickcare.lish.clock.util.ClockContentProvider/alarms";
        Uri alarms = Uri.parse(uri + "/" + id);
        ContentResolver cr = mContext.getContentResolver();

        ContentValues cv = new ContentValues();
        cv.put("ALARM_DAYS", alarm.getDaysBool(alarm.days));
        int returns = cr.update(alarms, cv, null, null);
        if (returns == 1) {
            Toast.makeText(mContext, "Alarm " + alarm.getTimeStr() + " days is updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Something is wrong", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateAlarmList() {
        mAlarmFragment.updateList(mAlarmFragment.generateAlarmList());
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements TimePickerDialog.OnTimeSetListener {
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
                    alarmDayBinding.cbSelect.setChecked(!alarmDayBinding.cbSelect.isChecked());
                });
            }

            observers.add(view -> toggleSettingView(view != itemView));
            itemView.setOnClickListener(view -> notifyClickEvent(view));
            binding.alarmSetting.btnCollapse.setOnClickListener(view -> {
                toggleSettingView(false);
                Alarm alarm = mAlarmList.get(getAdapterPosition());
                updateDaysSetting(alarm);
            });
            binding.btnExpand.setOnClickListener(view -> toggleSettingView(false));
            binding.alarmTime.setOnClickListener(view -> {
                Alarm alarm = mAlarmList.get(getAdapterPosition());
                showTimePickerDialog(alarm);
            });


/*            binding.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Alarm alarm = mAlarmList.get(getAdapterPosition());
                    if (binding.status.isChecked()) {
                        updateAlarmStatus(alarm, true);
                    } else {
                        updateAlarmStatus(alarm, false);
                    }
                }
            });*/


            binding.alarmSetting.btnDelete.setOnClickListener(view -> {
                Alarm alarm = mAlarmList.get(getAdapterPosition());
                deleteAlarm(alarm);
            });
        }

        private void toggleSettingView(boolean forceCollapsing) {
            Alarm alarm = mAlarmList.get(getAdapterPosition());
            if (forceCollapsing) alarm.selected = false;
            else alarm.selected ^= true;
            binding.daysContainer.setVisibility(alarm.selected ? View.GONE : View.VISIBLE);
            binding.setAlarm(alarm);
        }

        public void showTimePickerDialog(Alarm alarm) {
            // Create a new instance of TimePickerDialog and return it
            new TimePickerDialog(mContext, this, alarm.getHours(), alarm.getMinutes(), false).show();
            alarmPosition = mAlarmList.indexOf(alarm);
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            int id = alarmPosition + 1;

            String uri = "content://pointclickcare.lish.clock.util.ClockContentProvider/alarms";
            Uri alarms = Uri.parse(uri + "/" + id);
            ContentResolver cr = mContext.getContentResolver();

            ContentValues cv = new ContentValues();
            cv.put("ALARM_TIME", hour + ":" + minute);
            int returns = cr.update(alarms, cv, null, null);

            if (returns == 1) {
                Toast.makeText(mContext, "Alarm " + hour + ":" + minute + " is set", Toast.LENGTH_SHORT).show();
                updateAlarmList();
            } else {
                Toast.makeText(mContext, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
