package thread.producer;

import thread.ThreadTest1;

/**
 * Created by csw on 2016/11/21 12:25.
 * Explain: 用于消费面包
 */
public class Consumer implements Runnable {

    private int id;
    private Basket basket;

    public Consumer(int id, Basket basket) {
        this.id = id;
        this.basket = basket;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            Bread bread = basket.pop(id);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
