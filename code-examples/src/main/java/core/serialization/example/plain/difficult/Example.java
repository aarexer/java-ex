package core.serialization.example.plain.difficult;

import java.io.*;

public class Example {

    //we don't handle any errors - it's just a simple example
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Creating...");
        DataTransferObject dto = new DataTransferObject();
        //set field of parent class
        dto.setMyData("MyData in NonSerializable!");
        //set fields of current class
        dto.setData("Data current");
        dto.setI(14);
        dto.setCob(new CustomObject());

        File file = new File("serializationDemo.bin");

        System.out.println("Serializing...");
        //file writing
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fo);
        outputStream.writeObject(dto);
        outputStream.close();

        System.out.println("Deserializing...");
        //file reading
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(fi);
        DataTransferObject dtoFromFile = (DataTransferObject)inputStream.readObject();
        inputStream.close();

        System.out.println("Before: ");
        System.out.println(dto);
        System.out.println("After:");
        System.out.println(dtoFromFile);
    }
}
