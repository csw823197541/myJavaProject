package thread;

/**
 * Created by csw on 2016/11/18 16:25.
 * Explain:
 */
public class Test {

    public static void main(String[] args) {
        ThreadTest1 test1 = new ThreadTest1();
        test1.start();
        ThreadTest1 test2 = new ThreadTest1();
        test2.start();
    }
}
