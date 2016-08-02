package good.overriding.clone;

public class CloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        exampleOfNativeCloneWithCopy();
        System.out.println("Native clone example: ");
        exampleOfNativeClone();
    }

    static void exampleOfNativeCloneWithCopy() throws CloneNotSupportedException {
        //Clone method as super class
        Hobby pythonHobby = new Hobby("Python");
        PersonToCloneBetter personOne = new PersonToCloneBetter("Aleksandr", 25, pythonHobby);

        PersonToCloneBetter personAfterClone = personOne.clone();
        System.out.println(personOne);
        System.out.println(personAfterClone);

        //change
        personAfterClone.setAge(26);
        personAfterClone.getHobby().setName("Python 3.5");

        System.out.println("After changing: ");
        System.out.println("Original Person: " + personOne);
        System.out.println("Clone: " + personAfterClone);
    }

    static void exampleOfNativeClone() throws CloneNotSupportedException {
        //Clone method as super class
        Hobby pythonHobby = new Hobby("Python");
        PersonToClone personOne = new PersonToClone("Aleksandr", 25, pythonHobby);

        PersonToClone personAfterClone = personOne.clone();
        System.out.println(personOne);
        System.out.println(personAfterClone);

        //change
        personAfterClone.setAge(26);
        personAfterClone.getHobby().setName("Python 3.5");

        System.out.println("After changing: ");
        System.out.println("Original Person: " + personOne);
        System.out.println("Clone: " + personAfterClone);
    }
}

