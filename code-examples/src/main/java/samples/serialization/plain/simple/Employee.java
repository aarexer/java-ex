package samples.serialization.plain.simple;

import java.io.Serializable;

public class Employee implements Serializable {
    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
