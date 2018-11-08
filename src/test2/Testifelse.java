package test2;


import java.util.ArrayList;
import java.util.List;

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

        Testifelse test = new Testifelse();
        Long seq = new Long(1);
        test.method1(seq);
        System.out.println(seq);
        test.method2(seq);
        System.out.println(seq);

        //
        List<Integer> rowNoList = new ArrayList<>();
        rowNoList.add(1);
        rowNoList.add(2);
        rowNoList.add(3);
        for (Integer rowNo : rowNoList) {
            if (rowNo == 2) {
                break;
            }

        }
    }

    private void method2(Long seq) {
        seq++;
    }

    private void method1(Long seq) {
        seq++;
    }
}
