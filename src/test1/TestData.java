package test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by csw on 2017/3/8 21:07.
 * Explain:
 */
public class TestData {

    private List<DPCraneSelectBay> dpCraneSelectBays;

    public void init(){
        dpCraneSelectBays = new ArrayList<>();
        int nc = 3;
        int nb = 10;
        for (int i = 0; i < nc; i++) {
            for (int j = 0; j < nb; j++) {
                dpCraneSelectBays.add(new DPCraneSelectBay(new Pair(""+i, j)));
            }
        }
    }

    public DPCraneSelectBay getDpCraneSelectBay(Pair pair) {
        for (DPCraneSelectBay dpCraneSelectBay : dpCraneSelectBays) {
            if (dpCraneSelectBay.equals(pair)) {
                return dpCraneSelectBay;
            }
        }
        return null;
    }
}
