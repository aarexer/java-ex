package good.overriding.clone;

public class PersonToCloneBetter implements Cloneable {
    private String name;
    private int age;
    private Hobby hobby;

    public PersonToCloneBetter(String name, int age, Hobby hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    @Override
    protected PersonToCloneBetter clone() throws CloneNotSupportedException {
        PersonToCloneBetter pClone = (PersonToCloneBetter) super.clone();
        pClone.setHobby(new Hobby(hobby.getName()));

        return pClone;
    }

    @Override
    public String toString() {
        return "PersonToCloneBetter{" +
                "hobby=" + hobby +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
}
