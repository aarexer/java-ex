package core.samples.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptExample extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("i still alive");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("That's all...");
    }
}
