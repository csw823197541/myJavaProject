package queue;

/**
 * Created by csw on 2016/11/26 15:36.
 * Explain: 买票窗口
 */
public class BreadWindow {

    int maxSize = 10;
    ObjectQueue<Bread> breadQueue = new ObjectQueue<>(maxSize);
    int num = 0;
    boolean isAlive = true;

    public synchronized void producer() {
        if (breadQueue.size() < maxSize) {
            breadQueue.add(new Bread("我是第" + num++ + "面包"));
            this.notifyAll();//唤醒消费者线程
        } else {
            try {
                System.out.println("队列已满...请等待！");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumer() {
        if (breadQueue.size() > 0) {
            System.out.println("消费：" + breadQueue.remove());
            if (breadQueue.isEmpty()) {
                this.isAlive = false;
            }
            this.notify();//唤醒所有生产者线程
        } else {
            try {
                System.out.println("队列已空...请等待！");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
