package samples.serialization.plain.average;

import java.io.Serializable;

public class ChildClass extends ParentClass implements Serializable {
    protected int i;

    public ChildClass(int i) {
        this.i = i;
        System.out.println("Child constructor");
    }

    public int getI() {
        return i;
    }

    @Override
    public String toString() {
        return "I: " + i + ", field: " + field + ", string: " + st;
    }
}
