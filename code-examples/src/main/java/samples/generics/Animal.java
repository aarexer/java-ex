package samples.generics;

public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void feed() {
        System.out.println("Animal" + name + " feed");
    }

    public void call() {
        System.out.println("Animal" + name + " called");
    }
}
