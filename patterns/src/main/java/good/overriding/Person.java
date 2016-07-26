package good.overriding;

public class Person {
    private int age;
    private int number;
    private double salary;
    private String name;
    private CarKey carKey;

    public Person(int age, int number, String name, double salary, CarKey carKey) {
        this.age = age;
        this.number = number;
        this.name = name;
        this.salary = salary;
        this.carKey = carKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (!(obj instanceof Person))
            return false;

        Person tPerson = (Person) obj;

        if (tPerson.age != this.age ||
                !tPerson.name.equals(this.name) ||
                tPerson.number != this.number ||
                (Double.compare(tPerson.salary, this.salary) != 0) ||
                tPerson.carKey.equals(carKey)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = result * 17 + age;
        result = result * 17 + number;
        long lnum = Double.doubleToLongBits(number);
        result = result * 17 + (int)(lnum ^ (lnum >>> 32));
        result = result * 17 + name.hashCode();
        result = result * 17 + carKey.hashCode();

        return result;
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
