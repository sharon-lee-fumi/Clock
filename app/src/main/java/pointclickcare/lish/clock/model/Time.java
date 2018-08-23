package pointclickcare.lish.clock.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Time {
    long calculatedTimeMillis;
    private String zoneName;
    private long gmtOffset;

    public Time(String zoneName, long gmtOffset) {
        this.zoneName = zoneName;
        this.gmtOffset = gmtOffset;
    }

    public String getCountryName() {
        return zoneName;
    }

    public String getCalculatedTime() {
        long unixTime = System.currentTimeMillis();
        long localOffset = TimeZone.getDefault().getRawOffset();
        calculatedTimeMillis = unixTime - localOffset + (this.gmtOffset * 1000L);

        Date calculatedTime = new java.util.Date(calculatedTimeMillis);

        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(calculatedTime);
    }

    public String getFormattedPeriod() {
        Date calculatedTime = new java.util.Date(calculatedTimeMillis);
        return new SimpleDateFormat("a", Locale.getDefault()).format(calculatedTime);
    }
}
