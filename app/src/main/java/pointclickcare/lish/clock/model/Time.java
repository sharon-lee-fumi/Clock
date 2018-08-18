package pointclickcare.lish.clock.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Time {
    private String zoneName;
    private String calculatedTime;
    private String period;
    private long gmtOffset;

    public Time() {
    }

    public Time(String zoneName, long gmtOffset) {
        this.zoneName = zoneName;
        this.gmtOffset = gmtOffset;
    }

    public String getCountryName() {
        return zoneName;
    }

    public void setCountryName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Long getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(Long gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public void setCalculatedTime(String convertedTime) {
        this.calculatedTime = calculatedTime;
    }

    public String getCalculatedTime(long gmtOffset) {
        long unixTime = System.currentTimeMillis();
        long localOffset = TimeZone.getDefault().getRawOffset();
        long calculatedTimeMillis = unixTime - localOffset + (-21600);

        Date calculatedTime = new java.util.Date(calculatedTimeMillis);

        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(calculatedTime);
    }

    public String getFormattedPeriod() {
        Date currentDateTime = Calendar.getInstance().getTime();
        return new SimpleDateFormat("a", Locale.getDefault()).format(currentDateTime);
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
