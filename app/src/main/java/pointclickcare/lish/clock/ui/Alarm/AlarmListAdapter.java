package pointclickcare.lish.clock.ui.Alarm;

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
