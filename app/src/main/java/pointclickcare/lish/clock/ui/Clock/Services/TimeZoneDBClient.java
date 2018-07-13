package pointclickcare.lish.clock.ui.Clock.Services;

import pointclickcare.lish.clock.model.ZoneList;
import pointclickcare.lish.clock.model.ZoneTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeZoneDBClient {
    private TimeZoneDBService service = ServiceGenerator.createService(TimeZoneDBService.class);

    public void getZoneList(Callback<ZoneList> callback) {
        service.getTimeZoneList(TimeZoneDBService.apiKey, "json").enqueue(callback);
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
