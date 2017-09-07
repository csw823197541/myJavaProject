package test2;


/**
 * Created by csw on 2017/6/7.
 * Description
 */
public class TestStringBuffer {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.toString());
        if (stringBuffer.toString().equals("")) {
            System.out.println("dfdf");
        }
    }
}
