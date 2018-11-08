package threadPool;

import java.util.concurrent.*;

/**
 * Created by csw on 2016/11/29 14:01.
 * Explain:
 */
public class ThreadPool {

    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        int threadNum = Runtime.getRuntime().availableProcessors();
        System.out.println("系统当前可用的处理器数: " + threadNum);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 100, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>() {
        });
//        ExecutorService executor = Executors.newSingleThreadExecutor();

//        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
//        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 22; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，" +
                    "队列中等待执行的任务数目：" + executor.getQueue().size() + "，" +
                    "已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();

        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long ed = System.currentTimeMillis();
        System.out.println("总耗时：" + (ed - st));
    }
}
