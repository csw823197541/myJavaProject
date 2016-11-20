package observer;

/**
 * Created by csw on 2016/11/17 9:19.
 * Explain:
 */
public class MySubject extends AbstractSubject {

    public void operate() {
        System.out.println("update self!");
        notifyObservers();
    }
}
