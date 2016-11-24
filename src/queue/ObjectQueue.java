package queue;

/**
 * Created by csw on 2016/11/24 9:43.
 * Explain: 基于数组实现的队列
 */
public class ObjectQueue<T> implements Queue<T> {

    private T[] data;
    private int front;//队列第一个对象的位置
    private int rear;//队列最后一个对象的位置
    private int size;//队列中元素个数
    private int maxSize = 0;//队列数组最大空间

    public ObjectQueue(int maxSize) {
        this.maxSize = maxSize;
        data = (T[]) new Object[this.maxSize];
        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public void add(T t) {
        if (isFull()) {
            resize();//如果队列满了，则翻倍扩充队列大小
        }
        data[rear++] = t;
        size++;
        System.out.println("The queue add object: " + t + ", current size is: " + size);
    }

    @Override
    public T remove() {
        T temp = data[front];

        //将元素前移
        front++;
        size--;
        for (int i = 0; i < size; i++) {
            data[i] = data[front++];
        }
        //结束

        rear--;
        front = 0;
        System.out.println("The queue remove object: " + temp + ", then the size is: " + size);
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void resize() {
        T[] tmp = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, tmp, 0, data.length);
        data = tmp;
        tmp = null;
    }
}
