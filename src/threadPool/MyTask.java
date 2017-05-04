package threadPool;

/**
 * Created by csw on 2016/11/29 14:03.
 * Explain:
 */
public class MyTask implements Runnable {

    private int num;

    public MyTask(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " task " + num + "正在执行。。。");
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + num + "执行完毕！");
    }
}
