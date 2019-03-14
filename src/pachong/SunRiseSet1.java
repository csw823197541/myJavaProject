package pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by csw on 2019/03/08.
 * Description:
 */
public class SunRiseSet1 {

    public static void main(String[] args) throws IOException {
        String url = "https://richurimo.51240.com/119.01813973400877__jw__39.19987821952275__time__2019_03__richurimo/";
        Document document = Jsoup.connect(url).timeout(3000).get();
        //通过Document的select方法获取属性结点集合
        Element element = document.select("table").get(1);
        //得到节点的第一个对象
        Elements els = element.select("tr");
        for (Element el : els) {
            Elements ele = el.select("td");
            for (Element elem : ele){
                System.out.print(elem.text()+"\t");
            }
            System.out.print("\n");
        }

    }
}
