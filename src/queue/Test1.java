package queue;

/**
 * Created by csw on 2016/11/24 10:12.
 * Explain:
 */
public class Test1 {

    public static void main(String[] args) {

        BreadWindow window = new BreadWindow();

        Producer p = new Producer(window);
        Consumer c = new Consumer(window);

        new Thread(p).start();
        new Thread(c).start();

    }
}
