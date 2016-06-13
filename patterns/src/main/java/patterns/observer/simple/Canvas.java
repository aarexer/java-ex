package patterns.observer.simple;

public class Canvas implements ClickListener{
    @Override
    public void onClick() {
        System.out.println("Canvas and clicked!");
    }
}
