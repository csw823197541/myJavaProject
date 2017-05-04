package queue;

/**
 * Created by csw on 2016/11/26 15:56.
 * Explain:
 */
public class Consumer implements Runnable {

    private BreadWindow breadWindow;

    public Consumer(BreadWindow breadWindow) {
        this.breadWindow = breadWindow;
    }

    @Override
    public void run() {
        while (breadWindow.isAlive) {
            breadWindow.consumer();
        }
    }
}
