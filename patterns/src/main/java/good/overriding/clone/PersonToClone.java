package good.overriding.clone;

public class PersonToClone implements Cloneable {
    private String name;
    private int age;
    private Hobby hobby;

    public PersonToClone(String name, int age, Hobby hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "PersonToClone{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby=" + hobby +
                '}';
    }

    @Override
    protected PersonToClone clone() throws CloneNotSupportedException {
        return (PersonToClone) super.clone();
    }

    public static PersonToClone newInstance(PersonToClone personToClone) {
        return new PersonToClone(personToClone.getName(), personToClone.getAge(), new Hobby(personToClone.hobby.getName()));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Hobby getHobby() {
        return hobby;
    }
}
