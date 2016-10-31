package samples.serialization.plain.simple;

import java.io.*;

public class Example {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Employee dto = new Employee();
        dto.setAge(14);
        dto.setName("Hello");

        File file = new File("serializationDemo.bin");

        //file writing
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fo);
        outputStream.writeObject(dto);
        outputStream.close();

        //file reading
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(fi);
        Employee dtoFromFile = (Employee)inputStream.readObject();
        inputStream.close();

        System.out.println("Before: " + dto.getName() + " " + dto.getAge());
        System.out.println("After: " + dtoFromFile.getName() + " " + dtoFromFile.getAge());

    }
}
