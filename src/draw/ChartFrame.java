package draw;

import javax.swing.*;
import java.awt.*;

/**
 * Created by csw on 2017/9/7.
 * Description:
 */
public class ChartFrame extends JFrame{

    private JScrollPane scrollPane;


    public ChartFrame() {
        initComponents();
    }

    private void initComponents() {
        this.setTitle("统计图");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(GlobalData.width, GlobalData.height);
        this.setResizable(true);
        this.setLocationRelativeTo(null);// 居中显示

        ChartPanel panel = new ChartPanel();

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
