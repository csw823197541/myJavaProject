package threadLocal;

/**
 * Created by csw on 2017/9/21.
 * Description:
 */
public class DataFactory {

    private static ThreadLocal<Data> dataThreadLocal = new ThreadLocal<Data>() {
        @Override
        protected Data initialValue() {
            return new Data();
        }

        @Override
        public Data get() {
            return super.get();
        }

        @Override
        public void set(Data value) {
            super.set(value);
        }

        @Override
        public void remove() {
            super.remove();
        }
    };

    public static Data getData() {
        return dataThreadLocal.get();
    }

    public static void clear() {
        dataThreadLocal.remove();
    }

}
