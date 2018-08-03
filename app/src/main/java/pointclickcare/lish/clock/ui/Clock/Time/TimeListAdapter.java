package pointclickcare.lish.clock.ui.Clock.Time;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.ListTimeBinding;
import pointclickcare.lish.clock.model.Time;
import pointclickcare.lish.clock.model.ZoneTime;

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder>{
    private List<Time> listTime = new ArrayList<>();

    public void setSource(List<Time> list) {
        listTime = list;
    }

    @Override
    public TimeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListTimeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_time, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(TimeListAdapter.ViewHolder holder, int position) {
        Time time = listTime.get(position);
        holder.binding.setTime(time);
        holder.time = time;
    }

    @Override
    public int getItemCount() {
        return listTime.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ListTimeBinding binding;
        Time time;

        public ViewHolder(View itemView, ListTimeBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
