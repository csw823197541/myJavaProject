package test1;

import java.io.*;

/**
 * Created by csw on 2017/2/28 16:22.
 * Explain: DP选择的结果<craneId, bayNo>：桥机选择哪个倍位作业
 */
public class Pair<A, B> implements Serializable {

    private A first;

    private B second;

    public Pair() {}

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public Pair deepCopy() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            return (Pair) ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
