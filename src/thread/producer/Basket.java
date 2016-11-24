package thread.producer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by csw on 2016/11/21 11:47.
 * Explain:  篮子类，用于存放面包
 * 篮子假定最多放10个面包
 */
public class Basket {

    private int index = 0;

    private List<Bread> breadList = new ArrayList<>();

    

    /**
     * 此方法用于往篮子里扔面包,
     * 每当厨师生成好一个面包就往篮子里边扔,
     * 由于当某一个厨师在往篮子扔面包的过程（还没扔完，但是面包已经在篮子里），
     * 又有一个厨师要往篮子里扔面包。
     * 如果这是篮子里已经有9个面包的话，最后一个厨师就不能在扔了。
     * 所以需要给这个方法加把锁，等一个厨师扔完后，另外一个厨师才能往篮子里扔。
     * @param id
     * @param bread
     */
    public synchronized void push(int id, Bread bread) {
        System.out.println("生成前篮子里有面包：" + index + " 个");
        // 当厨师发现篮子满了，就在那里不停的等着
        while (index == 9) {
            System.out.println("篮子满了，我开始等等。。。。。。");
            try {
                // 厨师（一个生产线程）开始不停等待
                this.wait(); // 他需要等待顾客(一个消费线程)把它叫醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 唤醒一个正在等待的线程，如果唤醒的线程为生产线程，则又会进入等待状态，
        // 如果为消费线程，则因生产线程生产了面包的缘故，消费线程可以进行消费
        this.notify();
        breadList.add(bread);
        index++;
        System.out.println("    生产了一个面包：" + bread + ",当前面包个数：" + breadList.size());
    }

    public synchronized Bread pop(int id) {
        System.out.println("消费前篮子里有面包：" + index + " 个");
        while (index == 0) {
            System.out.println("篮子空了，我开始等等。。。。。。");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 唤醒一个正在等待的线程，如果唤醒的线程为消费线程，则又会进入等待状态，
        // 如果为生产线程，则因生产线程消费了面包的缘故，生产线程可以进行生产
        this.notify();
        index--;
        System.out.println("    第" + id + "个顾客消费了 --> " + breadList.get(index));
        return breadList.get(index);
    }
}
