package core.serialization.example.jaxb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DataObject {

    @XmlElement
    private int count;
    @XmlElement
    private String name;

    //we can annotate it without any getters/setters - Reflection!
    @XmlElement
    private String secretString = "secret";

    @XmlElement(name = "hobby")
    @XmlElementWrapper(name = "hobbies")
    private List<String> hobbies = new ArrayList<>();


    @Override
    public String toString() {
        return "count:" + count + ", name: " + name + ", secretString: " + secretString + ", hobbies: " + hobbies;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }

    public void removeHobby(String hobby) {
        hobbies.remove(hobby);
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}
