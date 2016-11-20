package observer;

/**
 * Created by csw on 2016/11/17 9:21.
 * Explain:
 */
public class Test {

    public static void main(String[] args) {
        MySubject sub = new MySubject();
        sub.add(new Observer1());
        sub.add(new Observer2());
        sub.operate();
    }
}
