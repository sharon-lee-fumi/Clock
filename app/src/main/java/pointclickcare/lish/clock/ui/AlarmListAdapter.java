package pointclickcare.lish.clock.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.ListAlarmBinding;
import pointclickcare.lish.clock.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    List<Alarm> mAlarmList = new ArrayList<>();

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
        }
    }
}
