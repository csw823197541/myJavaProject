package swingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView {

    private static boolean programmatic = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        device.setFullScreenWindow(frame);
        device.setDisplayMode(new DisplayMode(800, 600, 32, 60));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent we) {
                if (programmatic) {
                    programmatic = false;
                    frame.setState(JFrame.NORMAL);
                }
            }
        });

        JButton btn = new JButton();
        btn.setText("Btn");
        final JPanel panel = new JPanel();

        panel.add(btn);
        frame.add(panel);

        btn.addActionListener(e -> {
            programmatic = true;
            JOptionPane.showMessageDialog(panel, "Sample");
        });
        frame.setVisible(true);
    }
}
