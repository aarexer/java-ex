package good.overriding;

public class OverridingExamples {

    public static void main(String[] args) {
        personEquals();
    }


    //// FIXME: move to tests
    static void personEquals() {
        Person person1 = new Person(25, 8987, "Person_One", 4.2, new CarKey(1, "RED"));
        Person person2 = new Person(23, 8987, "Person_TWo", 4.1, new CarKey(1, "GREEN"));
        Person person3 = new Person(25, 8987, "Person_One", 4.2, new CarKey(1, "RED"));
        Person person4 = null;

        //different objects with different fields
        System.out.println(person1.equals(person2));
        //same objects
        System.out.println(person1.equals(person1));
        //different objects with same fields
        System.out.println(person1.equals(person3));
        //different objects with different fields
        System.out.println(person2.equals(person3));
        //null
        System.out.println(person2.equals(person4));
        //another object(not Person)
        System.out.println(person2.equals("asg"));

        //same objects with different fields
        person3.setAge(26);
        System.out.println(person1.equals(person3));
    }
}