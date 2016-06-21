package core.samples.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadExample extends Thread {
    private final int COUNT = 10;
    private String name;
    private int delay;

    public ThreadExample(String name, int delay) {
        this.name = name;
        this.delay = delay;
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " started!");
        try {
            for (int i = 0; i < COUNT; i++) {
                System.out.println(name + " generate number: " + i);
                TimeUnit.SECONDS.sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + name + " finished!");
    }
}
