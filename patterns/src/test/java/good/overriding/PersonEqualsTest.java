package good.overriding;

import org.junit.Assert;
import org.junit.Test;

public class PersonEqualsTest {
    private Person person1 = new Person(25, 8987, "Person_One", 4.2, new CarKey(1, "RED"));
    private Person person2 = new Person(23, 8987, "Person_TWo", 4.1, new CarKey(1, "GREEN"));
    private Person person3 = new Person(25, 8987, "Person_One", 4.2, new CarKey(1, "RED"));

    @Test
    public void equalDifferentPersonsWithDifferentFields() {
        Assert.assertFalse(person1.equals(person2));
    }

    @Test
    public void equalSamePersons() {
        Assert.assertTrue(person1.equals(person1));
    }

    @Test
    public void equalPersonAndNull() {
        Assert.assertFalse(person1.equals(null));
    }

    @Test
    public void equalDifferentPersonsWithSameFields() {
        Assert.assertTrue(person1.equals(person3));
    }

    @Test
    public void equalPersonAndNotPersonObject() {
        Assert.assertFalse(person2.equals("String object"));
    }
}
