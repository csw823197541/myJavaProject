package singleton;

/**
 * Created by csw on 2016/11/17 8:53.
 * Explain:
 */
public class Singleton3 {

    private static Singleton3 instance = null;

    private Singleton3() {

    }

    //因为只有创建实例的时候，才需要加锁，所以将创建实例的方法单独开来
    private static synchronized void initSingleton() {
        if (instance == null) {
            instance = new Singleton3();
        }
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            initSingleton();
        }
        return instance;
    }
}
