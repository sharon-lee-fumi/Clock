package pointclickcare.lish.clock.model;

public class Stopwatch {
    private String m;
    private String s;

    public Stopwatch(String m, String s) {
        this.m = m;
        this.s = s;
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
