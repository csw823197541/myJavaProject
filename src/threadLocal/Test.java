package threadLocal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by csw on 2017/9/21.
 * Description:
 */
public class Test {

    public static void main(String[] args) {

        Data data = DataFactory.getData();
//        data.setPerson(new Person("csw1", 1000L));
        data.setName("test");

        new Thread(new TestData(new Person("csw1", 12000L)), "线程1").start();
        new Thread(new TestData(), "线程2").start();

//        for (int i = 0; i < 2; i++) {
//            Thread thread = new Thread() {
//                @Override
//                public void run() {
////                    System.out.println("改变前：" + Thread.currentThread().getName() + "：" + DataFactory.getStr());
////                    DataFactory.setStr(Thread.currentThread().getName());
////                    System.out.println("改变后：" + Thread.currentThread().getName() + "：" + DataFactory.getStr());
//                    System.out.println("改变前：" + Thread.currentThread().getName() + "：" + DataFactory.getData().getPerson().getName());
////                    DataFactory.setStr(Thread.currentThread().getName());
//                    DataFactory.getData().setPerson(new Person(Thread.currentThread().getName(), 1000L));
//                    try {
//                        sleep(1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("改变后：" + Thread.currentThread().getName() + "：" + DataFactory.getData().getPerson().getName());
//                }
//            };
//            thread.start();
//        }

        Map<String, String> stringStringMap = new HashMap<>();
        System.out.println(new ArrayList<>(stringStringMap.keySet()));
    }

}

class TestData implements Runnable {

    private Data data;
    private Person person;

    public TestData() {
        data = DataFactory.getData();
        person = data.getPerson();
    }

    public TestData(Person person) {
        this.data = DataFactory.getData();
        this.person = person;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始(name:" + data.getPerson().getName() + ")--将name改成：" + Thread.currentThread().getName());
            data.getPerson().setName(Thread.currentThread().getName());
            sleep(person.getTime());
            System.out.println(Thread.currentThread().getName() + "结束--" + data.getPerson().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
