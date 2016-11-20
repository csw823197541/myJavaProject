package observer;

import java.util.Vector;

/**
 * Created by csw on 2016/11/17 9:16.
 * Explain:
 */
public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : vector) {
            observer.update();
        }
    }
}
