package pachong;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.CalculateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by csw on 2018/12/4.
 * Description:
 */
public class HttpUtil {

    private static int timeout = 10 * 1000;// 以秒为单位
    private static String host = "117.149.197.204";//117.177.243.7
    private static int port = 80;
    private static String cookie = "PHPSESSID=booj4v8i8ptcocc9fve7n1p402; Hm_lvt_9031c4bc2cecacb6d760cb393192453e=1550805843,1550902693,1550919057,1551681987; Hm_lpvt_9031c4bc2cecacb6d760cb393192453e=1551682002";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 主入口方法
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse("2019-3-29"));

        // 所有的潮汐时间点，每个小时对应的潮水高度
        Map<Long, Double> timeTideHeightMap = new LinkedHashMap<>();
        for (int i = 1; i <= 20; i++) { // 拉取多长时间的数据
            calendar.add(Calendar.DATE, 1);
            Date date = calendar.getTime();
//            System.out.println(sdf.format(date));
            String url = "http://ocean.cnss.com.cn/index.php?m=resource&c=tide&a=get_tide_data&portid=22&date=" + sdf.format(date);
            String result = get(url);
            System.out.println(result);

            Gson gson = new Gson();
            List<Tide> tideList = gson.fromJson(result, new TypeToken<List<Tide>>() {
            }.getType());
            if (tideList.size() > 0) {
                for (long[] data : tideList.get(0).getData()) {
                    double tideH = CalculateUtil.div(data[1], 100.0);
                    timeTideHeightMap.put(data[0], tideH);
                    // 将数据保存为sql语句的模式
//                    String sqlStr = "Insert into T_BASE_TIDE (GKEY,TIDE_TIME,TIDE_HEIGHT,PORT_GKEY) values ((select sys_guid() from dual),to_date('" + sdf1.format(new Date(data[0])) + "','yyyy-mm-dd hh24:mi:ss')," + tideH + ",'7C2E5685941108B2E055000000000001');";
//                    System.out.println(sqlStr);
                }
            }
        }
        // 间隔时间取高潮/低潮
        boolean h = true; // 高潮标志
        List<Long> timeList = new ArrayList<>(timeTideHeightMap.keySet());
        Collections.sort(timeList);
        for (int i = 1; i < timeList.size(); i++) { // 至少有两个时间点
            if (h) {
                if (timeTideHeightMap.get(timeList.get(i)).compareTo(timeTideHeightMap.get(timeList.get(i - 1))) < 0) { // 说明上一个时间点是高潮
                    h = false;
                    // 将数据保存为sql语句的模式
                    String sqlStr = "Insert into T_BASE_TIDE (GKEY,TIDE_TIME,TIDE_HEIGHT,PORT_GKEY) values ((select sys_guid() from dual),to_date('" + sdf1.format(new Date(timeList.get(i - 1))) + "','yyyy-mm-dd hh24:mi:ss')," + timeTideHeightMap.get(timeList.get(i - 1)) + ",'7C2E5685941108B2E055000000000001');";
                    System.out.println(sqlStr);
                }
            } else {
                if (timeTideHeightMap.get(timeList.get(i)).compareTo(timeTideHeightMap.get(timeList.get(i - 1))) > 0) { // 说明上一个时间点是低潮
                    h = true;
                    // 将数据保存为sql语句的模式
                    String sqlStr = "Insert into T_BASE_TIDE (GKEY,TIDE_TIME,TIDE_HEIGHT,PORT_GKEY) values ((select sys_guid() from dual),to_date('" + sdf1.format(new Date(timeList.get(i - 1))) + "','yyyy-mm-dd hh24:mi:ss')," + timeTideHeightMap.get(timeList.get(i - 1)) + ",'7C2E5685941108B2E055000000000001');";
                    System.out.println(sqlStr);
                }
            }
        }

    }

    /**
     * 向指定URL发送GET方法的请求
     */
    public static String get(String url) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);

            // 创建代理服务器
            InetSocketAddress address = new InetSocketAddress(host, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address); //http 代理

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection(proxy);

            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-type", "text/html; charset=utf-8");
            connection.setRequestProperty("Cookie", cookie);
            connection.setRequestProperty("Host", "ocean.cnss.com.cn");
            connection.setRequestProperty("Referer", "http://ocean.cnss.com.cn/");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
