import samples.serialization.jaxb.DataObject;
import samples.serialization.jaxb.JaxbParser;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JaxbTest {
    private JaxbParser parser;
    private File file;

    @Before
    public void setUp() throws Exception {
        parser = new JaxbParser();
        file = new File("dataObject.xml");
    }

    @Test
    public void testSaveObject() throws Exception {
        DataObject dataObject = new DataObject();
        dataObject.setName("aarexer");
        dataObject.setCount(14);
        dataObject.addHobby("Football");
        dataObject.addHobby("Ping-Pong");
        dataObject.addHobby("Bicycle");

        parser.saveObject(file, dataObject);

        DataObject dataObjectFromFile = (DataObject) parser.getObject(file, DataObject.class);
        assertEquals(dataObject.getCount(), dataObjectFromFile.getCount());
        assertEquals(dataObject.getName(), dataObjectFromFile.getName());
        assertThat(dataObject.getHobbies(), is(dataObjectFromFile.getHobbies()));
    }
}