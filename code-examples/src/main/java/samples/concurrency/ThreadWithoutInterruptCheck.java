package samples.concurrency;

public class ThreadWithoutInterruptCheck extends Thread {
    @Override
    public void run() {

        while (true) {
            System.out.println("i still alive");
        }
    }
}
