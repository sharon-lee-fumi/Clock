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
import pointclickcare.lish.clock.model.Zone;

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder>{
    private List<Zone> listTime = new ArrayList<>();

    public void setSource(List<Zone> list) {
        listTime = list;
    }

    @NonNull
    @Override
    public TimeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListTimeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_time, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeListAdapter.ViewHolder holder, int position) {
        Zone time = listTime.get(position);
        //holder.binding.setZoneTime(time);
        holder.time = time;
    }

    @Override
    public int getItemCount() {
        return listTime.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ListTimeBinding binding;
        Zone time;

        public ViewHolder(View itemView, ListTimeBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
