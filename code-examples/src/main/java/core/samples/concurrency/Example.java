package core.samples.concurrency;

import java.util.Timer;

/**
 * Illustrates how we can work with threads
 * For example - just call methods
 */
public class Example {
    public static void main(String[] args) {
        startThreadByTimer();
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

    static void startThreadByTimer() {
        new Timer().schedule(new TimerTaskExample("TimerThread", 2), 60 * 1000);
    }
}
