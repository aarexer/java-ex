package samples.serialization.plain.average;

/**
 * Parent class, have two fields.
 */
public class ParentClass {
    protected int field;
    protected String st;

    public ParentClass() {
        System.out.println("Parent Constructor without params");
        field = 14;
        st = "Hello";
    }

    public ParentClass(int i, String st) {
        System.out.println("Parent Constructor with params");
        this.field = i;
        this.st = st;
    }

    public int getField() {
        return field;
    }

    public String getSt() {
        return st;
    }
}
