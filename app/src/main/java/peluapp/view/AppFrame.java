package peluapp.view;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    public AppFrame() {
        setTitle("PeluApp - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void setContent(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
