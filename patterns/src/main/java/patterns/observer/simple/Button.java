package patterns.observer.simple;

import java.util.ArrayList;

public class Button{
    private ArrayList<ClickListener> listeners = new ArrayList<>();
    private int count = 0;


    public void addListner(ClickListener listener) {
        listeners.add(listener);
    }

    public void clicked() {
        listeners.forEach(ClickListener::onClick);
    }
}
