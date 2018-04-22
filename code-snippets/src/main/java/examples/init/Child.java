package examples.init;

public class Child extends Parent {
    private static final String CONST = printAndGetStringConst();

    static {
        System.out.println("Child static code block");
    }

    {
        System.out.println("Child non static code block");
    }

    public Child() {
        System.out.println("Child constructor");
    }

    private static String printAndGetStringConst() {
        System.out.println("Child CONST initialization");
        return "CONST";
    }
}
