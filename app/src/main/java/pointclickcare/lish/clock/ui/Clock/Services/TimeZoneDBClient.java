package pointclickcare.lish.clock.ui.Clock.Services;

import java.util.List;

import pointclickcare.lish.clock.model.Zone;
import pointclickcare.lish.clock.model.ZoneList;
import pointclickcare.lish.clock.model.ZoneTime;
import pointclickcare.lish.clock.ui.Clock.ZoneListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeZoneDBClient {
    private TimeZoneDBService service = ServiceGenerator.createService(TimeZoneDBService.class);

    List<Zone> zoneList;

    public List<Zone> setZoneList() {
        service.getTimeZoneList(TimeZoneDBService.apiKey, "json").enqueue(new Callback<ZoneList>() {
            @Override
            public void onResponse(Call<ZoneList> call, Response<ZoneList> response) {
                // 200: OK, 202: Accepted
                if (response.code() == 200 || response.code() == 202) {
                    zoneList = response.body().getZones();
                } else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<ZoneList> call, Throwable t) {
                // error
            }
        });
        return zoneList;
    }

    public void getZoneList(ZoneListAdapter adapter) {

                    adapter.setSource(setZoneList());
                    adapter.notifyDataSetChanged();
    }

    public void getZoneTime(String zone) {
        service.getZoneTime(TimeZoneDBService.apiKey, "json", "zone", zone).enqueue(new Callback<ZoneTime>() {
            @Override
            public void onResponse(Call<ZoneTime> call, Response<ZoneTime> response) {
                // 200: OK, 202: Accepted
                if (response.code() == 200 || response.code() == 202) {
                    ZoneTime zoneTime = response.body();
                    // do something
                } else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<ZoneTime> call, Throwable t) {
                // error
            }
        });
    }
}
