package patterns.builder;

/**
 * Builder pattern standard
 */
public class TelephoneBuilderPattern2 {
    private final int serialnumber;
    private final String name;
    private final String screenName;
    private final String mark;

    private TelephoneBuilderPattern2(int serialnumber, String name, String screenName, String mark) {
        this.serialnumber = serialnumber;
        this.name = name;
        this.screenName = screenName;
        this.mark = mark;
    }

    public static class Builder {

        private int serialnumber;
        private String name;
        private String screenName;
        private String mark;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;

            return this;
        }

        public Builder setScreenName(String screenName) {
            this.screenName = screenName;

            return this;
        }

        public Builder setMark(String mark) {
            this.mark = mark;
            return this;

        }

        public Builder setSerialnumber(int serialnumber) {
            this.serialnumber = serialnumber;

            return this;
        }

        public TelephoneBuilderPattern2 build() {
            return new TelephoneBuilderPattern2(serialnumber, name, screenName, mark);
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }

}
