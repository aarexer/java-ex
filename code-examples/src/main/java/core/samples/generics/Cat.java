package core.samples.generics;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void call() {
        System.out.println("Cat " + name + " called");
    }
}
