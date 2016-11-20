package thread;

/**
 * Created by csw on 2016/11/18 16:23.
 * Explain:
 */
public class ThreadTest1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
