package pachong;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2018/12/4.
 * Description:
 */
public class HttpUtil {

    private static int timeout = 10 * 1000;// 以秒为单位
    private static String host = "111.206.130.86";//117.177.243.7
    private static int port = 80;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 主入口方法
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse("2018-11-30"));

        for (int i = 1; i <= 30; i++) { // 拉取多长时间的数据
            calendar.add(Calendar.DATE, 1);
            Date date = calendar.getTime();
//            System.out.println(sdf.format(date));
            String url = "http://ocean.cnss.com.cn/index.php?m=resource&c=tide&a=get_tide_data&portid=22&date=" + sdf.format(date);
            String result = get(url);
//            System.out.println(result);

            // 将数据保存为sql语句的模式
            Gson gson = new Gson();
            List<Tide> tideList = gson.fromJson(result, new TypeToken<List<Tide>>() {
            }.getType());
            if (tideList.size() > 0) {
                for (long[] data : tideList.get(0).getData()) {
                    double tideH = CalculateUtil.div(data[1], 100.0);
                    String sqlStr = "Insert into T_BASE_TIDE (GKEY,TIDE_TIME,TIDE_HEIGHT,PORT_GKEY) values ((select sys_guid() from dual),to_date('" + sdf1.format(new Date(data[0])) + "','yyyy-mm-dd hh24:mi:ss')," + tideH + ",'7C2E5685941108B2E055000000000001');";
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
            connection.setRequestProperty("Cookie", "PHPSESSID=ds1b491pn413aa35nrhuhk4jr5; Hm_lvt_9031c4bc2cecacb6d760cb393192453e=1543905773,1543905936,1543905984,1543906017; Hm_lpvt_9031c4bc2cecacb6d760cb393192453e=1543906112; vuFxR_tide_checkcode=64e4AgAGVgAEUQVSAAQDVVMHAVRQA1YFBwYCVw0IAlBQDgcEAA9X");
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
