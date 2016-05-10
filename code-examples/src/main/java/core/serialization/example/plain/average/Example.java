package core.serialization.example.plain.average;

import java.io.*;

public class Example {
    public static void main(String[] args) {
        try {
            System.out.println("Creating...");
            ChildClass c = new ChildClass(25);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            //parent parameter
            c.field = 10;

            System.out.println("Serializing...");
            oos.writeObject(c);
            oos.close();
            baos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);

            System.out.println("Deserializing...");
            ChildClass c1 = (ChildClass) ois.readObject();


            System.out.println("Before: " + c);
            System.out.println("After: " + c1);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
