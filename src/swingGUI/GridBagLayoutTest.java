package swingGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by csw on 2017/12/26.
 * Description:
 */
public class GridBagLayoutTest extends JFrame {

    public GridBagLayoutTest() {

        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
//        //上侧的工具选择面板
//        JPanel toolSelectPanel = new JPanel();
//        toolSelectPanel.setBackground(Color.green);
//        this.add(toolSelectPanel, new GBC(0, 0, 2, 1).
//                setFill(GBC.BOTH).setIpad(200, 50).setWeight(100, 0));
//        //左侧的具体工具面板
//        JPanel toolConcretePanel = new JPanel();
//        toolConcretePanel.setBackground(Color.YELLOW);
//        this.add(toolConcretePanel, new GBC(0, 1).
//                setFill(GBC.BOTH).setIpad(70, 90).setWeight(0, 100));
//        //右侧的绘图面板
//        JPanel drawPanel = new JPanel();
//        drawPanel.setBackground(Color.WHITE);
//        this.add(drawPanel, new GBC(1, 1).setFill(GBC.BOTH));
//        //下侧的颜色选择面板
//        JPanel colorPanel = new JPanel();
//        colorPanel.setBackground(Color.LIGHT_GRAY);
//        this.add(colorPanel, new GBC(0, 2, 2, 1).
//                setFill(GBC.BOTH).setIpad(200, 50).setWeight(100, 0));
//        //下侧的状态面板
//        JPanel statePanel = new JPanel();
//        statePanel.setBackground(Color.CYAN);
//        this.add(statePanel, new GBC(0, 3, 2, 1).
//                setFill(GBC.BOTH).setIpad(200, 20).setWeight(100, 0));

        //右侧的绘图面板
        JPanel drawPanel = new JPanel();
        drawPanel.setBackground(Color.GRAY);
        this.add(drawPanel, new GBC(0, 0).setFill(GBC.BOTH).setIpad(50, 50).setWeight(1, 1));

//        JPanel drawPane2 = new JPanel();
//        drawPanel.setBackground(Color.WHITE);
//        this.add(drawPane2, new GBC(1, 1).setFill(GBC.BOTH));

    }
}
