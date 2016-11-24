package queue;

/**
 * Created by csw on 2016/11/23 11:26.
 * Explain: 队列接口
 */
public interface Queue<T> {

    void add(T t);

    T remove();

    boolean isEmpty();

    boolean isFull();

    int size();

    void resize();
}
