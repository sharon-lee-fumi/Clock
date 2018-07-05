package pointclickcare.lish.clock.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Zone {
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
        return new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).format(new Date(timestamp));
    }
}
