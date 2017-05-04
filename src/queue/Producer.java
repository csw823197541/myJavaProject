package queue;

/**
 * Created by csw on 2016/11/26 15:53.
 * Explain:
 */
public class Producer implements Runnable {

    private BreadWindow breadWindow;

    public Producer(BreadWindow breadWindow) {
        this.breadWindow = breadWindow;
    }

    @Override
    public void run() {
        while (breadWindow.num < 100000) {
            breadWindow.producer();
        }
    }
}
