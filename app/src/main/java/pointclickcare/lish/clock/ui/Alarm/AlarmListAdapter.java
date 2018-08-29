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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.AlarmDayBinding;
import pointclickcare.lish.clock.databinding.ListAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;
import pointclickcare.lish.clock.util.ClockContentProvider;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    private Context mContext;
    private List<Alarm> mAlarmList = new ArrayList<>();
    private Set<View.OnClickListener> mObservers = new HashSet<>();

    public AlarmListAdapter(Context context) {
        mContext = context;
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
        Alarm alarm = mAlarmList.get(position);
        holder.alarm = alarm;
        holder.binding.setAlarm(holder.alarm);
        mObservers.add(holder);
    }

    @Override
    public int getItemCount() {
        return mAlarmList.size();
    }

    private void notifyClickEvent(View view) {
        for (View.OnClickListener listener : mObservers) {
            listener.onClick(view);
        }
    }

    public void insertAlarm() {
        Uri alarms = ClockContentProvider.PROVIDER_ALARM_URI;
        ContentResolver cr = mContext.getContentResolver();
        ContentValues cv = new ContentValues();

        Alarm alarm = new Alarm();
        cv.put("ALARM_TIME", alarm.getTimeStr());
        cv.put("ALARM_DAYS", alarm.getDaysStr());
        cv.put("ALARM_STATUS", alarm.onOff.get() ? 1 : 0);
        Uri uri = cr.insert(alarms, cv);
        if (uri != null) {
            int id = Integer.parseInt(uri.getLastPathSegment());
            alarm.setAlarmId(id);
            mAlarmList.add(alarm);
            Toast.makeText(mContext, "Alarm " + alarm.getTimeStr() + " is set", Toast.LENGTH_SHORT).show();
            notifyItemInserted(mAlarmList.size());
        }
    }

    private void updateAlarm(Alarm alarm) {
        int id = alarm.getAlarmId();

        Uri alarms = Uri.parse(ClockContentProvider.PROVIDER_ALARM_URI + "/" + id);
        ContentResolver cr = mContext.getContentResolver();

        ContentValues cv = new ContentValues();
        cv.put("ALARM_TIME", alarm.getHours() + ":" + alarm.getMinutes());
        cv.put("ALARM_DAYS", alarm.getDaysBool(alarm.days));
        cv.put("ALARM_STATUS", alarm.onOff.get() ? 1 : 0);
        int count = cr.update(alarms, cv, null, null);
        if (count == 1) {
            Toast.makeText(mContext, "Alarm " + alarm.getHours() + ":" + alarm.getMinutes() + " is set", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteAlarm(Alarm alarm) {
        int id = alarm.getAlarmId();
        Uri alarms = Uri.parse(ClockContentProvider.PROVIDER_ALARM_URI + "/" + id);
        ContentResolver cr = mContext.getContentResolver();
        int count = cr.delete(alarms, null, null);
        if (count == 1) {
            int position = mAlarmList.indexOf(alarm);
            mAlarmList.remove(alarm);
            mObservers.remove(alarm.getAlarmId());
            notifyItemRemoved(position);
            Toast.makeText(mContext, "Alarm " + alarm.getTimeStr() + " is deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements TimePickerDialog.OnTimeSetListener, View.OnClickListener {
        ListAlarmBinding binding;
        Alarm alarm;

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
                    updateAlarm(alarm);
                });
            }

            itemView.setOnClickListener(view -> notifyClickEvent(view));
            binding.alarmSetting.btnCollapse.setOnClickListener(view -> toggleSettingView(false));
            binding.btnExpand.setOnClickListener(view -> toggleSettingView(false));
            binding.alarmTime.setOnClickListener(view -> showTimePickerDialog(alarm));
            binding.onOff.setOnClickListener(view -> updateAlarm(alarm));
            binding.alarmSetting.btnDelete.setOnClickListener(view -> {
                deleteAlarm(alarm);
                mObservers.remove(this);
            });
            mObservers.add(this);
        }

        private void toggleSettingView(boolean forceCollapsing) {
            if (forceCollapsing) alarm.selected = false;
            else alarm.selected ^= true;
            binding.setAlarm(alarm);
        }

        public void showTimePickerDialog(Alarm alarm) {
            // Create a new instance of TimePickerDialog and return it
            new TimePickerDialog(mContext, this, alarm.getHours(), alarm.getMinutes(), false).show();
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, minute);
            alarm.setTime(calendar.getTime());
            updateAlarm(alarm);
        }

        @Override
        public void onClick(View view) {
            toggleSettingView(view != itemView);
        }
    }
}