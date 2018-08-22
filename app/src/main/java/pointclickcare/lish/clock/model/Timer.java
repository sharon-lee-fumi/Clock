package pointclickcare.lish.clock.model;

public class Timer {
    private int seconds;
    private int minutes;

    public Timer(int seconds, int minutes) {
        this.seconds = seconds;
        this.minutes = minutes;
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

    public String getFormattedSeconds() {
        return String.format("%02d", this.seconds);
    }

    public String getFormattedMinutes() {
        return String.format("%02d", this.minutes);
    }
}
