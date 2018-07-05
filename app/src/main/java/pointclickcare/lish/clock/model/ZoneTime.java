package pointclickcare.lish.clock.model;

import com.google.gson.annotations.SerializedName;

public class ZoneTime extends Response{
    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("countryName")
    private String countryName;

    @SerializedName("zoneName")
    private String zoneName;

    @SerializedName("abbreviation")
    private String abbreviation;

    @SerializedName("gmtOffset")
    private Long gmtOffset;

    @SerializedName("dst")
    private String dst;

    @SerializedName("dstStart")
    private Long dstStart;

    @SerializedName("dstEnd")
    private Long dstEnd;

    @SerializedName("nextAbbreviation")
    private String nextAbbreviation;

    @SerializedName("timestamp")
    private Long timestamp;

    @SerializedName("formatted")
    private String formatted;

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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Long getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(Long gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public Long getDstStart() {
        return dstStart;
    }

    public void setDstStart(Long dstStart) {
        this.dstStart = dstStart;
    }

    public Long getDstEnd() {
        return dstEnd;
    }

    public void setDstEnd(Long dstEnd) {
        this.dstEnd = dstEnd;
    }

    public String getNextAbbreviation() {
        return nextAbbreviation;
    }

    public void setNextAbbreviation(String nextAbbreviation) {
        this.nextAbbreviation = nextAbbreviation;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }
}
