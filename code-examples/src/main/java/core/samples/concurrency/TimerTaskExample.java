package core.samples.concurrency;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTaskExample extends TimerTask {
    private final int COUNT = 10;
    private String taskName;
    private int delay;

    public TimerTaskExample(String taskName, int delay) {
        this.taskName = taskName;
        this.delay = delay;
    }

    @Override
    public void run() {
        System.out.println("Thread " + taskName + " started!");
        try {
            for (int i = 0; i < COUNT; i++) {
                System.out.println(taskName + " generate number: " + i);
                TimeUnit.SECONDS.sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + taskName + " finished!");
    }
}
