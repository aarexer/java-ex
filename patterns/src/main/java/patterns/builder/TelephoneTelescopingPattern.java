package patterns.builder;

public class TelephoneTelescopingPattern {
    private int serialnumber;
    private String name;
    private String screenName;
    private String mark;

    public TelephoneTelescopingPattern(int serialnumber, String name, String screenName, String mark) {
        this.serialnumber = serialnumber;
        this.name = name;
        this.screenName = screenName;
        this.mark = mark;
    }
}
