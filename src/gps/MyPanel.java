package gps;

import javax.swing.*;
import java.awt.*;

/**
 * Created by csw on 2020/01/02.
 * Description:
 */
public class MyPanel extends JPanel {

    public MyPanel() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 创建 Graphics 的副本, 需要改变 Graphics 的参数,
        // 这里必须使用副本, 避免影响到 Graphics 原有的设置
        Graphics2D g2d = (Graphics2D) g.create();

        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置画笔颜色
        g2d.setColor(Color.RED);

        // 2. 多点绘制折线: 点(50, 100), 点(100, 130), 点(150, 70), 点(200, 100)
        String area = "118.973880768754,39.2213527282299;118.979940760627,39.2230574706852;118.982635868167,39.2171899209347;118.97532086506,39.2151068055366;118.974818140901,39.2172873334882;118.974216676793,39.2202079676251;118.973880768754,39.2213527282299";
        String[] areas = area.split(";");
        int[] xPoints = new int[areas.length];
        int[] yPoints = new int[areas.length];
        int nPoints = areas.length;

        int xm = Integer.MAX_VALUE;
        int ym = Integer.MAX_VALUE;
        for (int i = 0; i < nPoints; i++) {
            double[] xy = MCT84Bl2xy(Double.valueOf(areas[i].split(",")[0]), Double.valueOf(areas[i].split(",")[1]));
//            double[] xy = MCT84Bl2xy(Double.valueOf(areas[i].split(",")[1]), Double.valueOf(areas[i].split(",")[0]));
            xPoints[i] = (int) xy[0];
            yPoints[i] = (int) xy[1];
            System.out.println(xPoints[i] + "," + yPoints[i]);
            if (xPoints[i] < xm) {
                xm = xPoints[i];
            }
            if (yPoints[i] < ym) {
                ym = yPoints[i];
            }
        }
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = xPoints[i] - xm;
            yPoints[i] = yPoints[i] - ym;
            System.out.println(xPoints[i] + "," + yPoints[i]);
        }


        g2d.drawPolyline(xPoints, yPoints, nPoints);

        // 自己创建的副本用完要销毁掉
        g2d.dispose();
    }

    public double[] toXy(double lat, double lon) {
        double L = 6381372 * Math.PI * 2; // 地球周长
        double W = L;// 平面展开后，x轴等于周长
        double H = L / 2;// y轴约等于周长一半
        double mill = 2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间
        double x = lon * Math.PI / 180;// 将经度从度数转换为弧度
        double y = lat * Math.PI / 180;// 将纬度从度数转换为弧度
        y = 1.25 * Math.log(Math.tan(0.25 * Math.PI + 0.4 * y));// 米勒投影的转换
        // 弧度转为实际距离
        x = (W / 2) + (W / (2 * Math.PI)) * x;
        y = (H / 2) - (H / (2 * mill)) * y;

        double[] result = new double[2];
        result[0] = (int) x;
        result[1] = (int) y;
        return result;
    }

    public double[] MCT84Bl2xy(double l, double B) {
        l = l * Math.PI / 180;
        B = B * Math.PI / 180;

        double B0 = 30 * Math.PI / 180;

        double N = 0, e = 0, a = 0, b = 0, e2 = 0, K = 0;
        a = 6378137;
        b = 6356752.3142;
        e = Math.sqrt(1 - (b / a) * (b / a));
        e2 = Math.sqrt((a / b) * (a / b) - 1);
        double CosB0 = Math.cos(B0);
        N = (a * a / b) / Math.sqrt(1 + e2 * e2 * CosB0 * CosB0);
        K = N * CosB0;

        double Pi = Math.PI;
        double SinB = Math.sin(B);

        double tan = Math.tan(Pi / 4 + B / 2);
        double E2 = Math.pow((1 - e * SinB) / (1 + e * SinB), e / 2);
        double xx = tan * E2;

        double xc = K * Math.log(xx);
        double yc = K * l;

        double[] result = new double[2];
        result[0] = (int) xc;
        result[1] = (int) yc;
        return result;
    }

}
