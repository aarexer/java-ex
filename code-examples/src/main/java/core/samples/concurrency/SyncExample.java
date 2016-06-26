package core.samples.concurrency;

import java.util.concurrent.atomic.AtomicLong;

public class SyncExample {

    interface Counter {
        long increment();
    }

    private class CounterNotAtomic implements Counter {
        private long counter;

        public long increment() {
            return counter++;
        }
    }

    private class CounterAtomic implements Counter {
        private AtomicLong counter = new AtomicLong(0);

        public long increment() {
            return counter.getAndIncrement();
        }
    }

    private class CounterWithLock implements Counter {
        private long counter;

        public synchronized long increment() {
            return counter++;
        }
    }

    private class Task implements Runnable {
        private Counter counter;

        public Task(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        }
    }

    public void exampleOfIncrementNotAtomic() throws InterruptedException {
        CounterNotAtomic counter = new CounterNotAtomic();
        final int threadNum = 3;
        Thread[] threads = new Thread[threadNum];

        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(new Task(counter));
            threads[i] = t;
            t.start();
        }

        for (Thread t : threads)
            t.join();

        System.out.println("Counter is: " + counter.counter);
    }

    public void exampleOfIncrementAtomic() throws InterruptedException {
        CounterAtomic counter = new CounterAtomic();
        final int threadNum = 3;
        Thread[] threads = new Thread[threadNum];

        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(new Task(counter));
            threads[i] = t;
            t.start();
        }

        for (Thread t : threads)
            t.join();

        System.out.println("Counter is: " + counter.counter);
    }

    public void exampleOfIncrementWithLock() throws InterruptedException {
        CounterWithLock counter = new CounterWithLock();
        final int threadNum = 3;
        Thread[] threads = new Thread[threadNum];

        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(new Task(counter));
            threads[i] = t;
            t.start();
        }

        for (Thread t : threads)
            t.join();

        System.out.println("Counter is: " + counter.counter);
    }

}
