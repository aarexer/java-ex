package good.overriding;

public class Person {
    private int age;
    private int number;
    private String name;

    public Person(int age, int number, String name) {
        this.age = age;
        this.number = number;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Person))
            return false;

        Person tPerson = (Person) obj;

        if (tPerson.age != this.age || !tPerson.name.equals(this.name ) || tPerson.number != this.number)
            return false;

        return true;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
