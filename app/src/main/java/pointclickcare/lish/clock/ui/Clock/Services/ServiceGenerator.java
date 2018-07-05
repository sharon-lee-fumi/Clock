package pointclickcare.lish.clock.ui.Clock.Services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder().
            baseUrl(TimeZoneDBService.baseUrl).
            addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return builder.client(httpClient.build()).build().create(serviceClass);
    }
}
