package samples.serialization.jaxb;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Example {

    public static void main(String[] args) throws JAXBException {
        JaxbParser parser = new JaxbParser();
        parser.saveObject(new File("jaxbExample.xml"), new DataObject());
    }

}
