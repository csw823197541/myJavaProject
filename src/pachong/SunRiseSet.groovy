package pachong

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern;

/**
 * Created by csw on 2019/03/08.
 * Description: 
 */
class SunRiseSet {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd")
    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd")
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    public static void main(String[] args) throws IOException {
        for (int i = 3; i <= 12; i++) {
            String url = "https://richurimo.51240.com/119.01813973400877__jw__39.19987821952275__time__2019_" + i + "__richurimo/";
            get(url)
        }
    }

    static void get(String url) {
        Document document = Jsoup.connect(url).timeout(3000).get()
        //通过Document的select方法获取属性结点集合
        Element element = document.select("table").get(1)
        //得到节点的第一个对象
        Elements elements = element.select("tr")
        for (int i = 1; i < elements.size(); i++) { // 第一行是标题，从第二行开始
            Elements els = elements.get(i).select("td")
//            System.out.print(els.get(0).text() + "\t")
            String regEx = "[^0-9]"
            Pattern pattern = Pattern.compile(regEx)
            Matcher matcher = pattern.matcher(els.get(0).text())
            String dateStr = sdf1.format(sdf.parse(matcher.replaceAll("").trim()));
            String sunrise = dateStr + " " + els.get(1).text()
            String sunset = dateStr + " " + els.get(3).text()
            String dawn = dateStr + " " + els.get(5).text()
            String dusk = dateStr + " " + els.get(6).text()
//            System.out.print(dateStr + "\t")
//            System.out.print(sunrise + "\t")
//            System.out.print(sunset + "\t")
//            System.out.print(dawn + "\t")
//            System.out.print(dusk + "\t")
//            System.out.print("\n");
            String sql = "Insert into T_BASE_DAYANDNIGHT (GKEY,DATE_TIME,SUNRISE,SUNSET,DAWN,DUSK) values ((select sys_guid() from dual),to_date('" + dateStr + " 00:00:00','yyyy-mm-dd hh24:mi:ss'),to_date('" + sunrise + "','yyyy-mm-dd hh24:mi:ss'),to_date('" + sunset + "','yyyy-mm-dd hh24:mi:ss'),to_date('" + dawn + "','yyyy-mm-dd hh24:mi:ss'),to_date('" + dusk + "','yyyy-mm-dd hh24:mi:ss'));"
            println(sql)
        }
    }
}
