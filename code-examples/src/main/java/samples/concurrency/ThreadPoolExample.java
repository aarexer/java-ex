package samples.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExample {
    private ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private List<Future> futureList = new ArrayList<>();

    static class Task implements Callable<Integer> {

        private int number;

        public Task(int number) {
            this.number = number;
        }

        @Override
        public Integer call() throws Exception {
            int result = 0;
            for(int i = 1; i <= number; i++)
                result += i;

            return result;
        }
    }

    public void exampleOfCores() {
        final int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cores: " + cores);
    }

    public void exampleOfTasks() {
        for (int i = 0; i < 5; i++) {
            threadPool.submit(new ThreadExample("Thread#" + i, 2));
        }

        threadPool.shutdown();
    }

    public void exampleOfFuture() throws Exception {
        for (int i = 0; i < 5; i++) {
            Future future = threadPool.submit(new Task(i));
            futureList.add(future);
        }

        for (Future future : futureList) {
            System.out.println("Result: " + future.get());
        }

        threadPool.shutdown();
    }
}
