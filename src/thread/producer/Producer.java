package thread.producer;

/**
 * Created by csw on 2016/11/21 12:21.
 * Explain: 用于生产面包
 */
public class Producer implements Runnable {

    private Basket basket;
    private int id;

    public Producer(int id, Basket basket) {
        this.id = id;
        this.basket = basket;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            Bread bread = new Bread("第" + id + "个生产者生成的面包");
            basket.push(id, bread);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
