package examples.concurency.philosophers;

public class Chopstick {
    private boolean taken;
    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }

        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
