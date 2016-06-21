package core.samples.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptWhileSleep extends Thread {
    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("i still alive");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //catch exception while sleep
                e.printStackTrace();
                //and after print exception - interrupt our thread
                Thread.currentThread().interrupt();
            }
        }
    }
}
