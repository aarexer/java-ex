package patterns.builder;

/**
 * Builder pattern
 */
public class TelephoneBuilderPattern {
    private int serialnumber;
    private String name;
    private String screenName;
    private String mark;


    private TelephoneBuilderPattern() {

    }

    public class Builder {

        private Builder() {
        }

        public Builder setName(String name) {
            TelephoneBuilderPattern.this.name = name;

            return this;
        }

        public Builder setScreenName(String screenName) {
            TelephoneBuilderPattern.this.screenName = screenName;

            return this;
        }

        public Builder setMark(String mark) {
            TelephoneBuilderPattern.this.mark = mark;
            return this;

        }

        public Builder setSerialnumber(int serialnumber) {
            TelephoneBuilderPattern.this.serialnumber = serialnumber;

            return this;
        }

        public TelephoneBuilderPattern build() {
            return TelephoneBuilderPattern.this;
        }
    }

    public static Builder getBuilder() {
        return new TelephoneBuilderPattern().new Builder();
    }
}
