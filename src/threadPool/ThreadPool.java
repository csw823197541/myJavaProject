package threadPool;

import java.util.concurrent.*;

/**
 * Created by csw on 2016/11/29 14:01.
 * Explain:
 */
public class ThreadPool {

    public static void main(String[] args) {

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<Runnable>(10));
//        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newFixedThreadPool(20);
//        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 150; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
//            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，" +
//                    "队列中等待执行的任务数目：" + executor.getQueue().size() + "，" +
//                    "已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
