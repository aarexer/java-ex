package samples.serialization.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser {

    /**
     *
     * @param file which have our object
     * @param clazz which we want to load from file
     * @return Object - our object which we can ca
     * @throws JAXBException
     */
    public Object getObject(File file, Class clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(file);
    }

    /**
     *
     * @param file for save object
     * @param object which we want to save in file
     * @throws JAXBException smth wrong:)
     */
    public void saveObject(File file, Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(object, file);
    }
}
