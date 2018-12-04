package pachong;

import java.util.List;

/**
 * Created by csw on 2018/12/4.
 * Description:
 */
public class Tide {

    private String name;
    private List<long[]> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<long[]> getData() {
        return data;
    }

    public void setData(List<long[]> data) {
        this.data = data;
    }
}
