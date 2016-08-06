package good.collections.empty;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PersonNames {
    private List<String> personNames;

    public PersonNames() {
        personNames = Collections.emptyList();
    }

    public PersonNames(List<String> personNames) {
        this.personNames = personNames;
    }

    public PersonNames(String... personNames) {
        this.personNames = new ArrayList<>();
        Collections.addAll(this.personNames, personNames);
    }

    @Override
    public String toString() {
        return personNames.toString();
    }

    public Iterator<String> iterator() {
        return personNames.iterator();
    }
}
