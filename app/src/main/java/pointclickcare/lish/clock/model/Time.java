package pointclickcare.lish.clock.model;

public class Time {
    private String countryName;
    private Long gmtOffset;

    public Time() {

    }

    public Time(String countryName, Long gmtOffset) {
        this.countryName = countryName;
        this.gmtOffset = gmtOffset;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(Long gmtOffset) {
        this.gmtOffset = gmtOffset;
    }
}
