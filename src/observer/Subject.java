package observer;

/**
 * Created by csw on 2016/11/17 9:06.
 * Explain:
 */
public interface Subject {

    void add(Observer observer);

    void del(Observer observer);

    void notifyObservers();

//    void operate();
}
