package pointclickcare.lish.clock;

import android.app.Application;

public class ClockApplication extends Application {
    public static Application sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
