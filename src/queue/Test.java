package queue;

/**
 * Created by csw on 2016/11/24 10:12.
 * Explain:
 */
public class Test {

    public static void main(String[] args) {
        ObjectQueue<String> q = new ObjectQueue<>(10);
        System.out.println(q.isEmpty());
        q.add("1");
        q.add("2");
        q.add("3");
        q.add("4");
        q.add("5");
        q.add("6");
        q.add("7");
        q.add("8");
        q.add("9");
        q.add("10");
        q.remove();
//        q.remove();
//        q.add("11");
//        q.add("12");
        while (!q.isEmpty()) {
            q.remove();
        }
        System.out.println(q.size());

    }
}
