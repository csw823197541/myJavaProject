package threadLocal;

/**
 * Created by csw on 2017/9/21.
 * Description:
 */
public class Person {

    private String name;

    private Long time;

    public Person(String name, Long time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Long getTime() {
        return time;
    }
}
