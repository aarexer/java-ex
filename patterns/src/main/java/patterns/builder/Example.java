package patterns.builder;

/**
 * Examples for Builder pattern
 */
public class Example {
    public static void main(String[] args) {
        //example of telescoping pattern
        TelephoneTelescopingPattern telephone1 = new TelephoneTelescopingPattern(1, "Sony", "TFT", "X");

        //example of java beans pattern
        TelephoneJavaBeansPattern telephone2 = new TelephoneJavaBeansPattern();
        telephone2.setSerialnumber(2);
        telephone2.setName("Sony");
        telephone2.setScreenName("TFT");
        telephone2.setMark("X");

        //example of builder
        TelephoneBuilderPattern telephone3 = TelephoneBuilderPattern.getBuilder().setName("Sony")
                                                                                 .setSerialnumber(3)
                                                                                 .setMark("X")
                                                                                 .setScreenName("TFT")
                                                                                 .build();

        //example of builder2
        TelephoneBuilderPattern2 telephone4 = TelephoneBuilderPattern2.getBuilder().setMark("X")
                                                                                   .setName("Sony")
                                                                                   .setScreenName("TFT")
                                                                                   .setSerialnumber(4)
                                                                                   .build();
    }
}
