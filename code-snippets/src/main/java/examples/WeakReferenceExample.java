package examples;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    public static void main(String[] args) {
        WeakReference<Integer> ref = new WeakReference<>(1000);
        System.out.println("Before gc: " + ref.get());

        for (int i = 0; ref.get() != null; i++) {
            System.gc();
            System.out.printf("After gc, iteration: %d, ref: %d", i, ref.get());
        }
    }
}
