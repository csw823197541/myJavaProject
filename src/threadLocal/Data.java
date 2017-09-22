package threadLocal;


/**
 * Created by csw on 2017/9/21.
 * Description:
 */
public class Data {

    private String name;

    private Person person;

    public Data(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void print() {
        System.out.println(person.getName());
    }
}
