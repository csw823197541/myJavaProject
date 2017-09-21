package thread;

/**
 * Created by csw on 2016/11/21 11:31.
 * Explain:
 */

class Ticket implements Runnable {

    private Integer number = 5;

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + ", 第" + i + "次" + "卖票---->" + --number);
            }
        }
    }
}

public class TicketSell {

    public static void main(String[] args) {
        Ticket tk = new Ticket();
        Thread thread1 = new Thread(tk, "窗口一");
        Thread thread2 = new Thread(tk, "窗口二");
        Thread thread3 = new Thread(tk, "窗口三");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

