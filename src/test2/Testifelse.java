package test2;


/**
 * Created by csw on 2017/6/7.
 * Description
 */
public class Testifelse {

    public static void main(String[] args) {
        int n = 10;
        if (n > 7) {
            if (n > 8) {
                if (n > 9) {
                    System.out.println("y");
                }
//                else {
//                    System.out.println("e9");
//                }
            }
//            else {
//                System.out.println("e8");
//            }
        }
        System.out.println("e7");

        String str = "csw";
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length);
    }
}
