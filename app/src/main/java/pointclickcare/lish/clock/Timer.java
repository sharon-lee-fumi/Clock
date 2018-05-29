package pointclickcare.lish.clock;

public class Timer {
    private String h;
    private String m;
    private String s;

    public Timer(String h, String m, String s) {
        this.h = h;
        this.m = m;
        this.s = s;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
