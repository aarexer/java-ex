package core.samples.concurrency;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Illustrates how we can work with threads
 * For example - just call methods
 */
public class Example {
    public static void main(String[] args) throws Exception {
        exampleOfLockIncrement();
    }

    //work with two threads
    static void startTwoThreads() {
        ThreadExample te1 = new ThreadExample("My thread", 1);
        ThreadExample te2 = new ThreadExample("My thread 2", 2);
        te1.start();
        te2.start();
    }

    //example with calling run method() - it's wrong!
    //My thread work in main thread!
    static void wrongRun() {
        ThreadExample te1 = new ThreadExample("My thread", 1);
        ThreadExample te2 = new ThreadExample("My thread 2", 2);
        te1.run();
        te2.run();
    }

    //use constructor of Thread class
    static void startTwoRunnableThreads() {
        Thread te1 = new Thread(new RunnableExample("My thread", 1));
        Thread te2 = new Thread(new RunnableExample("My thread 2", 2));
        te1.start();
        te2.start();
    }

    //Timer thread example
    static void startThreadByTimer() {
        new Timer().schedule(new TimerTaskExample("TimerThread", 2), 60);
    }

    //Example for thread join
    static void joinExample() throws InterruptedException {
        ThreadExample te1 = new ThreadExample("My thread", 1);
        ThreadExample te2 = new ThreadExample("My thread 2", 2);
        te1.start();
        te1.join();
        System.out.println("Main thread!");
        te2.start();
    }

    //Example for thread interrupt by our flag
    static void interruptedThreadByFlag() {
        ThreadWithFlag tFlag = new ThreadWithFlag();
        tFlag.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tFlag.setStopFlag(true);
    }

    //Example for thread interrupt by our flag
    static void interruptedThread() {
        ThreadInterruptExample tFlag = new ThreadInterruptExample();
        tFlag.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tFlag.isInterrupted();
    }

    //Example with wrong interrupt
    //We can't stop thread without checking flag
    static void wrongInterrupted() {
        Thread tr = new ThreadWithoutInterruptCheck();
        tr.start();
        tr.interrupt();
    }

    //Example of interrupted exception while sleep
    static void interruptWhileSleep() {
        Thread tr = new ThreadInterruptWhileSleep();
        tr.start();
        tr.interrupt();
    }

    //Shows us the number of cpu cores
    static void numberOfCoresExample() {
        new ThreadPoolExample().exampleOfCores();
    }

    //Example of ThreadPool
    static void exampleOfThreadExecutor() {
        new ThreadPoolExample().exampleOfTasks();
    }

    //Example Future
    static void exampleOfFuture() throws Exception {
        new ThreadPoolExample().exampleOfFuture();
    }

    //Not atomic increment
    static void exampleOfNotAtomicIncrement() {
        try {
            new SyncExample().exampleOfIncrementNotAtomic();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //atomic increment
    static void exampleOfAtomicIncrement() {
        try {
            new SyncExample().exampleOfIncrementAtomic();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //lock increment
    static void exampleOfLockIncrement() {
        try {
            new SyncExample().exampleOfIncrementWithLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
