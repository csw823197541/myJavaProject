package draw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by csw on 2017/9/7.
 * Description:
 */
public class ChartPanel extends JPanel {

    private int leftMargin = 80;
    private int topMargin = 50;

    private int height = 600;
    private int width = 700;
    private int block = 500;

    private int timeStep = 2;
    private int n = 6;

    private int gap = 5;

    public ChartPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(GlobalData.width, GlobalData.height));
        this.setSize(GlobalData.width, GlobalData.height);
        this.setOpaque(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        Font font=new Font("宋体",Font.PLAIN,18);
        g2d.setFont(font);

        //y轴
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(leftMargin, topMargin, leftMargin, height);
        g2d.drawString("时间:", leftMargin - 50, topMargin);
        int b = block / n;
        int allTime = timeStep * n;
        for (int j = 0; j <= n; j++) {
            String tStr = String.valueOf(j * timeStep + 8);
            int timeY = height - j * b;
            g2d.drawString(tStr, leftMargin - 50, timeY);
            g2d.drawLine(leftMargin - gap, timeY, leftMargin, timeY);
        }

        //x轴
        g2d.setPaint(Color.BLACK);
        g2d.drawLine(leftMargin, height, width + leftMargin, height);
        int t = (width - leftMargin) / GlobalData.str.length;
        for (int i = 0; i < GlobalData.str.length; i++) {
            String xStr = GlobalData.str[i];
            int x = 2 * leftMargin + i * t;
            g2d.drawString(xStr, x + t / 3, height + 4 * gap);
            g2d.drawLine(x, height, x, height + gap);
        }

        //画块
        for (int i = 0; i < GlobalData.str.length; i++) {
            int x = 2 * leftMargin + i * t;
            int xx = x + t / 3;
            int y = height - (block * (GlobalData.d[i] - 8)) / allTime - 2;
            int w = t / 3;
            int h = (GlobalData.u[i] - GlobalData.d[i]) * block / allTime;
            g2d.setPaint(GlobalData.colors[i]);
            g2d.drawRect(xx, y, w, h);
            g2d.fillRect(xx, y, w, h);
        }
    }
}
