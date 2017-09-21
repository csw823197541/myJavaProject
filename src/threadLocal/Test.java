package threadLocal;


import static java.lang.Thread.sleep;

/**
 * Created by csw on 2017/9/21.
 * Description:
 */
public class Test {

    public static void main(String[] args) {

        String name = "张三";
        Long time = 2000L;

        Data data = DataFactory.getData();
        data.setPerson(new Person(name, time));

        new Thread(new TestData(new Person("csw", 12000L)), "线程1").start();
        new Thread(new TestData(), "线程2").start();

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
            System.out.println(Thread.currentThread().getName() + "开始--将person改成：" + person.getName());
            data.setPerson(person);
            sleep(person.getTime());
            System.out.println(Thread.currentThread().getName() + "结束--" + data.getPerson().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
