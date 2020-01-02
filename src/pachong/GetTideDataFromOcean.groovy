package pachong

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import utils.CalculateUtil

import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by csw on 2019/03/08.
 * Description: 
 */
class GetTideDataFromOcean {

    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd")
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    public static void main(String[] args) throws IOException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf1.parse("2019-5-30"));

        for (int i = 1; i <= 20; i++) {
            calendar.add(Calendar.DATE, 1);
            Date date = calendar.getTime();
            String url = "http://ocean.cnss.com.cn"
            String dateStr = sdf1.format(date)
            get(url, dateStr)
        }
    }

    static void get(String url, String dateStr) {
        Connection connection = Jsoup.connect(url)
        connection.data("state", "亚洲")
        connection.data("country", "中国")
        connection.data("province", "河北")
        connection.data("port", "京唐港")
        connection.data("date", dateStr)

        Document document = connection.timeout(3000).post()
        //通过Document的select方法获取属性结点集合
        Element element = document.select("table").get(0)
        //得到节点的第一个对象
        Elements elements = element.select("tr")
        Elements els1 = elements.get(0).select("td") // 第一行潮时
        Elements els2 = elements.get(1).select("td") // 第一行潮高
        for (int j = 1; j < els1.size(); j++) {
            Double tideHigh = CalculateUtil.div(Double.valueOf(els2.get(j).text()), 100.0)
            String insertSql = "Insert into T_BASE_TIDE (GKEY,TIDE_TIME,TIDE_HEIGHT,PORT_GKEY) values ((select sys_guid() from dual),to_date('" + sdf2.format(sdf2.parse(dateStr + " " + els1.get(j).text() + ":00")) + "','yyyy-mm-dd hh24:mi:ss')," + tideHigh + ",'7C2E5685941108B2E055000000000001');"
            println(insertSql)
        }
    }
}
