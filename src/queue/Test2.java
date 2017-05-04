package queue;

/**
 * Created by csw on 2016/11/24 10:12.
 * Explain:
 */
public class Test2 {

    public static void main(String[] args) {

        BreadWindow window = new BreadWindow();

        Consumer c = new Consumer(window);
        new Thread(c).start();

        for (int i = 0; i < 5; i++) {
            window.producer();
        }
        for (int i = 0; i < 5; i++) {
            window.producer();
        }


    }
}
