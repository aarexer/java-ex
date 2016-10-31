package samples.generics;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    public void call() {
        System.out.println("Dog " + name + "called");
    }
}
