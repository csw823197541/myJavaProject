package test;

/**
 * Created by csw on 2017/3/7 16:24.
 * Explain:
 */
public class CollectionTests {

    public static void main(String[] args) {
//        TestData testData = new TestData();
//        testData.addOA(new OA(1));
//        testData.addOA(new OA(2));
//        testData.addOA(new OA(3));
//        OA oa = testData.getOAById(1);
//        Integer id = oa.getId();
//        testData.removeOAById(1);
//        testData.removeOAById(2);
        Double a = new Double(100D);
        Double b = new Double(10D);
        System.out.println(a.compareTo(b));
        Long l1= new Long(100L);
        Long l2 = 100L;
        System.out.println(l1 == l2);

//        Long[] wt1 = {10L, 30L, 20L, 40L, 10L, 50L, 60L, 20L, 20L, 3L};
////        Long[] wt1 = {20L, 20L, 20L, 30L, 10L, 20L};
//        OA[] oas = new OA[3];
//        oas[0] = new OA(0);
//        oas[1] = new OA(1);
//        oas[2] = new OA(2);
//        int nc = 3;
//        long all = 0L;
//        for (int i = 0; i < wt1.length; i++) {
//            all += wt1[i];
//        }
//
//        long mean = all / nc;
//        long rem = all % mean;
//        System.out.println(rem);
//        long tmp = 0L;
//        int c = 0;
//        int cSize = 0;
//        for (int i = 0; i < wt1.length; i++) {
//            tmp += wt1[i];
//            cSize += 1;
//            c = c == nc ? nc - 1 : c;
//            oas[c].setFrom(i + 1 - cSize);
//            oas[c].setTo(i);
//            if (tmp == mean) {
//                c++;
//                tmp = 0L;
//                cSize = 0;
//            } else if (tmp > mean) {
//                if (c < nc - 1)
//                    oas[c].setToWT(wt1[i] - (tmp - mean));
//                tmp = tmp - mean;
//                c++;
//                if (c < nc)
//                    oas[c].setFromWT(tmp);
//                cSize = 1;
//            }
//        }
//
//        for (int i = 0; i < oas.length; i++) {
//            System.out.println(oas[i].getFrom() +"  "+ oas[i].getTo() + "   " + oas[i].getFromWT() + "  " + oas[i].getToWT());
//        }
    }
}
