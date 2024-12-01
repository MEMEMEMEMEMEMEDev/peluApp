package peluapp.view;

import peluapp.controller.AppController;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {

    private final AppController controller;

    public MainMenuView(AppController controller) {
        this.controller = controller;

        setTitle("Peluquería - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuCitas = new JMenu("Citas");
        JMenu menuGastos = new JMenu("Gastos");

        JMenuItem itemCitas = new JMenuItem("Administrar Citas");
        itemCitas.addActionListener(e -> controller.showCitasView());
        menuCitas.add(itemCitas);

        JMenuItem itemGastos = new JMenuItem("Administrar Gastos");
        itemGastos.addActionListener(e -> controller.showGastosView());
        menuGastos.add(itemGastos);

        menuBar.add(menuCitas);
        menuBar.add(menuGastos);
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("Bienvenido a PeluApp", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(title, BorderLayout.CENTER);

        add(mainPanel);
    }

    public void setContent(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
