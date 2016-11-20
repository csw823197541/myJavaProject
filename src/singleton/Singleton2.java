package singleton;

/**
 * Created by csw on 2016/11/17 8:26.
 * Explain:
 */
public class Singleton2 {

    //私有的构造方法
    private Singleton2() {

    }

    //使用一个内部类来维护单例
    private static class SingletonFactory {
        private static Singleton2 instance = new Singleton2();
    }

    //获取实例
    public static Singleton2 getInstance() {
        return SingletonFactory.instance;
    }

    //保证对象序列化时，对象的前后一致性
    public Object readResolve() {
        return getInstance();
    }
}
