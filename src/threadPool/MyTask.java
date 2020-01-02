package threadPool;

/**
 * Created by csw on 2016/11/29 14:03.
 * Explain:
 */
public class MyTask implements Runnable {

    private int num;
    private Method method;

    MyTask(int num) {
        this.num = num;
        method = new Method();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": 任务" + num + "正在执行。。。");
        try {
            Thread.sleep(1000);
            if (num == 10) {
                Thread.sleep(60000);
            }
            method.add(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务" + num + "执行完毕！");
    }
}
