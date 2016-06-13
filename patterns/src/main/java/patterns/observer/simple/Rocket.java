package patterns.observer.simple;

public class Rocket implements ClickListener{
    @Override
    public void onClick() {
        System.out.println("Ooooops! Rocket was launched");
    }
}
