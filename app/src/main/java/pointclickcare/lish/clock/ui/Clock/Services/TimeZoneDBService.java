package pointclickcare.lish.clock.ui.Clock.Services;

import pointclickcare.lish.clock.model.ZoneList;
import pointclickcare.lish.clock.model.ZoneTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TimeZoneDBService {
    String baseUrl ="http://api.timezonedb.com/";
    String apiKey = "YJE13E075YMW";

    @GET("v2/list-time-zone")
    Call<ZoneList> getTimeZoneList(
            @Query("key") String apiKey,
            @Query("format") String format
    );

    @GET("v2/get-time-zone")
    Call<ZoneTime> getZoneTime(
            @Query("key") String apiKey,
            @Query("format") String format,
            @Query("by") String by,
            @Query("zone") String zone
    );
}
