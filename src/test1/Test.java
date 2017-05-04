package test1;


/**
 * Created by csw on 2017/3/8 21:07.
 * Explain:
 */
public class Test {

    public static void main(String[] args) {
        TestData testData = new TestData();
        testData.init();
        Pair pair = new Pair("0", 0);
        Pair pair1 = new Pair("0", 1);
        DPCraneSelectBay dpCraneSelectBay1 = testData.getDpCraneSelectBay(pair);
        DPCraneSelectBay dpCraneSelectBay2 = testData.getDpCraneSelectBay(pair);
        DPCraneSelectBay dpCraneSelectBay3 = testData.getDpCraneSelectBay(pair1);
        assert dpCraneSelectBay3 != null;
        dpCraneSelectBay3.setDpWorkTime(100L);
        System.out.println("dfdsfa");
    }
}
