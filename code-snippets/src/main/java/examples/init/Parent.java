package examples.init;

public class Parent {
    private static final String PCONST = printAndGetStringConst();

    static {
        System.out.println("Parent static code block");
    }

    {
        System.out.println("Parent non static code block");
    }

    public Parent() {
        System.out.println("Parent constructor");
    }

    private static String printAndGetStringConst() {
        System.out.println("Parent_CONST initialization");
        return "Parent_CONST";
    }
}
