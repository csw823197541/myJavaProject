package queue;

/**
 * Created by csw on 2016/11/21 11:44.
 * Explain: 面包类，用于存放厨师生产的面包
 */
public class Bread {

    private String producer;

    public Bread(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return producer;
    }
}
