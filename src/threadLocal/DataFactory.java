package threadLocal;

/**
 * Created by csw on 2017/9/21.
 * Description:
 */
public class DataFactory {

    private static ThreadLocal<Data> dataThreadLocal = new ThreadLocal<Data>() {
        @Override
        protected Data initialValue() {
            return new Data(new Person("csw2", 1000L));
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

    public static void setData(Data data) {
        dataThreadLocal.set(data);
    }

    public static void clearData() {
        dataThreadLocal.remove();
    }

    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "I am String.";
        }

        @Override
        public String get() {
            return super.get();
        }

        @Override
        public void set(String value) {
            super.set(value);
        }

        @Override
        public void remove() {
            super.remove();
        }
    };

    public static String getStr() {
        return stringThreadLocal.get();
    }

    public static void setStr(String str) {
        stringThreadLocal.set(str);
    }

    public static void clearStr() {
        stringThreadLocal.remove();
    }

}
