package threadPool;

/**
 * Created by csw on 2016/11/29 14:03.
 * Explain:
 */
public class MyTask implements Runnable {

    private int num;

    MyTask(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": 任务" + num + "正在执行。。。");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务" + num + "执行完毕！");
    }
}
