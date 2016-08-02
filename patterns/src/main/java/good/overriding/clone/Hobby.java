package good.overriding.clone;

public class Hobby {
    private String name;

    public Hobby(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
