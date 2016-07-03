package patterns.builder;

/**
 * Created by rexer on 03.07.16.
 */
public class TelephoneJavaBeansPattern {
    private int serialnumber;
    private String name;
    private String screenName;
    private String mark;

    public void setSerialnumber(int serialnumber) {
        this.serialnumber = serialnumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
