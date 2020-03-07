package gps;

import javax.swing.*;
import java.awt.*;

/**
 * Created by csw on 2020/01/02.
 * Description:
 */
public class GpsConvert {

    public static void main(String[] args) {

        // 创建窗口对象
        MyFrame frame = new MyFrame();
        // 显示窗口
        frame.setVisible(true);
    }

//    public double[] toXy(double lat, double lon) {
//        double L = 6381372 * Math.PI * 2;//地球周长
//        double W = L;// 平面展开后，x轴等于周长
//        double H = L / 2;// y轴约等于周长一半
//        double mill = 2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间
//        double x = lon * Math.PI / 180;// 将经度从度数转换为弧度
//        double y = lat * Math.PI / 180;// 将纬度从度数转换为弧度
//        y = 1.25 * Math.log(Math.tan(0.25 * Math.PI + 0.4 * y));// 米勒投影的转换
//        // 弧度转为实际距离
//        x = (W / 2) + (W / (2 * Math.PI)) * x;
//        y = (H / 2) - (H / (2 * mill)) * y;
//        double[] result = new double[2];
//        result[0] = (int) x;
//        result[1] = (int) y;
//        return result;
//    }

//    public double toGps(double x, double y)
//    {
//        double L = 6381372 * Math.PI * 2;//地球周长
//        double W = L;// 平面展开后，x轴等于周长
//        double H = L / 2;// y轴约等于周长一半
//        double mill = 2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间
//        double lat;
//        lat = ((H / 2 - y) * 2 * mill) / (1.25 * H);
//        lat = ((atan(exp(lat)) - 0.25 * Math.PI) * 180) / (0.4 * Math.PI);
//        double lon;
//        lon = (x - W / 2) * 360 / W;
//        double* result = new double[2];
//        result[0] = lon;
//        result[1] = lat;
//        return result;
//    }
}
