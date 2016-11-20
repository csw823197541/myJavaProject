package singleton;

import com.sun.istack.internal.Nullable;

import java.util.Objects;

/**
 * Created by csw on 2016/11/16 16:14.
 * Explain:
 */
public class Singleton1 {

    //
    private static Singleton1 instance = null;

    //
    private Singleton1() {

    }

    //
    public static Singleton1 getInstance() {
        if (instance == null) {
            //
            synchronized (Singleton1.class) {
                if (instance == null) {
                    //
                    instance = new Singleton1();
                }
            }
        }
        //
        return instance;
    }

    //
    public Object readResolve() {
        return instance;
    }
}
