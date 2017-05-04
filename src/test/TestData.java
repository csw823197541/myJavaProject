package test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csw on 2017/3/7 16:25.
 * Explain:
 */
public class TestData {

    private Map<Integer, OA> oaMap;

    public TestData() {
        oaMap = new HashMap<>();
    }

    public void addOA(OA oa) {
        this.oaMap.put(oa.getId(), oa);
    }

    public void removeOAById(Integer id) {
        this.oaMap.remove(id);
    }

    public OA getOAById(Integer id) {
        return this.oaMap.get(id);
    }
}
