package pointclickcare.lish.clock.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Zone extends BaseObservable {
    public final ObservableBoolean selected = new ObservableBoolean();
    private transient Integer zoneId;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("countryName")
    private String countryName;
    @SerializedName("zoneName")
    private String zoneName;
    @SerializedName("gmtOffset")
    private Long gmtOffset;
    @SerializedName("timestamp")
    private Long timestamp;

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Long getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(Long gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormattedTime() {
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(timestamp* 1000L);
    }

    public String getFormattedPeriod() {
        return new SimpleDateFormat("a", Locale.getDefault()).format(timestamp* 1000L);
    }
}
