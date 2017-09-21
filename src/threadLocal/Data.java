package threadLocal;


/**
 * Created by csw on 2017/9/21.
 * Description:
 */
public class Data {

    private Person person;

    public Data() {

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
