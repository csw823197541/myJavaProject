package swingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by csw on 2016/12/14 13:00.
 * Explain:
 */
public class InFrame extends JInternalFrame implements ActionListener {

    UIManager.LookAndFeelInfo looks[];
    private JButton bn = new JButton("this is a test ");

    public InFrame() {
        super("InFrame ");
        looks = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(looks[1].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con = this.getContentPane();
        FlowLayout fl = new FlowLayout();
        con.setLayout(fl);
        setLocation(200, 150);
        bn.addActionListener(this);
        con.add(bn);
        this.setContentPane(con);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        InFrame subfrm = new InFrame();
        this.getParent().add(subfrm);
    }
}
