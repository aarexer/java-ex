package core.samples.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadWithFlag extends Thread {
    private volatile boolean stopFlag = false;

    @Override
    public void run() {
        while (!stopFlag) {
            System.out.println("i still alive");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("That's all...");
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }
}
