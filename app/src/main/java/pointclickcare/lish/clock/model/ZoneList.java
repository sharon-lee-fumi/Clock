package pointclickcare.lish.clock.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZoneList extends Response{
    @SerializedName("zones")
    List<Zone> zones;

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
