package core.serialization.example.json;

import com.google.gson.Gson;
import core.serialization.example.jaxb.DataObject;

public class Example {

    public static void main(String[] args) {
        Gson parser = new Gson();
        //Simple DataObject
        DataObject dataObject = new DataObject();
        dataObject.setName("aarexer");
        dataObject.setCount(14);
        dataObject.addHobby("Football");
        dataObject.addHobby("Ping-Pong");
        dataObject.addHobby("Bicycle");

        //our json string
        String jsonString = parser.toJson(dataObject);
        System.out.println(jsonString);

        DataObject dataObjectFromJson = parser.fromJson(jsonString, DataObject.class);
        //we override toString!
        System.out.println(dataObjectFromJson);
    }
}
