package swingGUI;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by csw on 2016/12/14 12:58.
 * Explain:
 */
public class FrameTest extends JFrame implements ActionListener{

    private JButton bn=new JButton( "this is a test ");
    public FrameTest(){
        super( "this is a test ");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con=this.getContentPane();
        FlowLayout fl=new FlowLayout();
        con.setLayout(fl);
        bn.addActionListener(this);
        con.add(bn);
        this.setContentPane(con);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        InFrame subfrm=new InFrame();
        this.getContentPane().add(subfrm);
    }
    public static void main(String[] args){
        new FrameTest();
    }
}
