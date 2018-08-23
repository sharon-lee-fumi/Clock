package pointclickcare.lish.clock.model;

public class Alarm {

    //private boolean status;
    //public boolean[] alarmDayBtns = new boolean[7];
    public boolean selected;
    public AlarmData alarmData;
    private String time;
    private String period;
    private String days;

    public Alarm() {
        this.alarmData = this.getAlarmData();
        this.time = this.getTime();
        this.period = this.getPeriod();
        this.days = this.getDays();
        //this.status = this.getStatus();
        // this.alarmDayBtns = this.getAlarmDayBtns();
        this.selected = false;
    }

    /*
        public Alarm(String time, String period, String days, boolean status) {
            this.time = time;
            this.period = period;
            this.days = days;
            this.status = status;
        }


        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

    */
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

/*    public boolean[] getAlarmDayBtns() {
        return alarmDayBtns;
    }

    public void setAlarmDayBtns(boolean[] alarmDayBtns) {
        this.alarmDayBtns = alarmDayBtns;
    }*/

    public AlarmData getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(AlarmData alarmData) {
        this.alarmData = alarmData;
    }
}
