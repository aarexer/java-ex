package examples.concurency.philosophers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable{
    private final int id;
    private final int ponderFactor;
    private Chopstick left;
    private Chopstick right;

    private Random random = new Random(47);

    public Philosopher(int id, int ponderFactor, Chopstick left, Chopstick right) {
        this.id = id;
        this.ponderFactor = ponderFactor;
        this.left = left;
        this.right = right;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }

        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " get right");
                right.take();
                System.out.println(this + " get left");
                left.take();
                System.out.println("Eating..");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
