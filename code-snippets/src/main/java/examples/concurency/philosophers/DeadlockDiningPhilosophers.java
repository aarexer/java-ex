package examples.concurency.philosophers;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadlockDiningPhilosophers {
    public static void main(String[] args) throws IOException {
        int ponder = 5;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }

        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(i, ponder, sticks[i], sticks[(i + 1) % size]));
        }

        System.out.println("Enter for exit");
        System.in.read();
        exec.shutdown();
    }
}
