package good.collections.empty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PersonNamesIterator {
    private List<String> names;

    public void addName(String name) {
        if (names == null)
            names = new ArrayList<>();

        names.add(name);
    }
    public Iterator<String> iterator() {
        return (names != null) ? names.iterator() : Collections.emptyIterator();
    }
}
