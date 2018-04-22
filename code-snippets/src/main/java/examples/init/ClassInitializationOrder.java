package examples.init;

import java.util.HashMap;
import java.util.Map;

public class ClassInitializationOrder {
    private static final String CONST = printAndGetStringConst();
    private String sField = printAndGetString();

    public ClassInitializationOrder(String s) {
        System.out.println(s + " in constructor initialization");
    }

    static {
        System.out.println("Static init 1");
    }

    {
        System.out.println("Block init 1");
    }

    static {
        System.out.println("Static init 2");
    }

    {
        System.out.println("Block init 2");
    }

    private static String printAndGetStringConst() {
        System.out.println("CONST initialization");
        return "CONST";
    }

    public static void main(String[] args) {
        System.out.println("FIRST Instance");
        new ClassInitializationOrder("FIRST");
        System.out.println("\nSECOND Instance");
        new ClassInitializationOrder("SECOND");

        Map<String, String> map = new HashMap<String, String>() {{
            put("паук",  "арахнид");
            put("птица", "архозавр");
            put("кит",   "зверь");
        }};

        System.out.println(map);
    }

    private String printAndGetString() {
        System.out.println("sField initialization");
        return "sField";
    }
}

