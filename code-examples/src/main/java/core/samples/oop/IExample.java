package core.samples.oop;

public class IExample {
    public static void main(String[] args) {
        Person aleksandr = new Person();
        aleksandr.setAge(10); //it's all good
        aleksandr.setAge(-1); //we can't set it!
    }
}

/**
 * Example of encapsulation
 */
class Person {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    /**
     * We can't set age less zero.
     */
    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age can't be less then zero");
        this.age = age;
    }
}
