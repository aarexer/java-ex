package good;

/**
 * Illustrate how we can create class for only static methods and fields.
 * Good way.
 */
public final class ClassForStatic {

    public static String st_string = "Hello";

    public static void method() {
        System.out.println("Static method");
    }

    /**
     * If we create instance of class - we throw Error.
     */
    private ClassForStatic() {
        throw new AssertionError();
    }

    /**
     * We have error in this method! - cause we have only private constructor,
     * and if we create instance of our class - we throw error.
     */
    public static ClassForStatic errorThrow() {
        return new ClassForStatic();
    }
}
