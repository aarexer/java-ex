package patterns.observer;

import patterns.observer.simple.Button;
import patterns.observer.simple.Canvas;
import patterns.observer.simple.Rocket;

public class Example {
    public static void main(String[] args) {
        Rocket rocket = new Rocket();
        Canvas canvas = new Canvas();

        Button button = new Button();
        button.addListner(rocket);
        button.addListner(canvas);

        button.clicked();
    }
}
