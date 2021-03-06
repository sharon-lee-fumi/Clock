package pointclickcare.lish.clock.ui.Clock.Time;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.ListZoneBinding;
import pointclickcare.lish.clock.model.Time;
import pointclickcare.lish.clock.model.Zone;
import pointclickcare.lish.clock.ui.Clock.Services.TimeZoneDBClient;

public class ZoneListAdapter extends RecyclerView.Adapter<ZoneListAdapter.ViewHolder> {
    public List<Time> savedZone;
    ListZoneBinding binding;
    private List<Zone> listZone = new ArrayList<>();

    public ZoneListAdapter(List<Time> getZone) {
        savedZone = getZone;
    }

    public void setSource(List<Zone> list) {
        listZone = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_zone, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Zone zone = listZone.get(position);
        holder.binding.setZoneInfo(zone);
        holder.zone = zone;
    }

    @Override
    public int getItemCount() {
        return listZone.size();
    }

    public List<Time> getSavedZone() {
        savedZone.clear();
        for (int i = 0; i < listZone.size(); i++) {
            if (listZone.get(i).selected.get()) {
                Time time = new Time(listZone.get(i).getZoneName(), listZone.get(i).getGmtOffset());
                savedZone.add(time);
            }
        }
        return savedZone;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ListZoneBinding binding;
        Zone zone;

        ViewHolder(View itemView, ListZoneBinding binding) {
            super(itemView);
            this.binding = binding;

            for (int i = 0; i < savedZone.size(); i++) {
                for (int j = 0; j < listZone.size(); j++) {
                    if (savedZone.get(i).getZoneName().equals(listZone.get(j).getZoneName())) {
                        listZone.get(j).selected.set(true);
                    }
                }
            }

            itemView.setOnClickListener((view) ->
                    new TimeZoneDBClient().getZoneTime(zone.getZoneName())
            );

        }
    }
}
