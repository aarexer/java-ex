package good;

public final class ClassForStatic {

    public static String st_string = "Hello";

    public static void method() {
        System.out.println("Static method");
    }

    private ClassForStatic() {
        throw new AssertionError();
    }
}
