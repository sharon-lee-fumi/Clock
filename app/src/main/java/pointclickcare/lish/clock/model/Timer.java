package pointclickcare.lish.clock.model;

import java.io.Serializable;

public class Timer implements Serializable {
    private int hours;
    private int seconds;
    private int minutes;
    private Long timeBuff;
    private int formattedHours;
    private int formattedMinutes;
    private int formattedSeconds;

    public Timer(int hours, int minutes, int seconds) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Long calculate() {
        timeBuff = (this.hours * 3600 + this.minutes * 60 + this.seconds) * 1000L;
        return timeBuff;
    }

    public String getFormattedSeconds() {
        formattedSeconds = (int) (calculate() / 1000);
        formattedHours = formattedSeconds / 3600;
        formattedSeconds = formattedSeconds % 3600;
        formattedMinutes = formattedSeconds / 60;
        formattedSeconds = formattedSeconds % 60;

        return String.format("%02d", formattedSeconds);
    }

    public String getFormattedMinutes() {
        return String.format("%02d", formattedMinutes);
    }

    public String getFormattedHours() {
        return String.format("%02d", formattedHours);
    }
}
