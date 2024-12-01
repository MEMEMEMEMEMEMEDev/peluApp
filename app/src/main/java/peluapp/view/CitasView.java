package peluapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CitasView extends JPanel {

    public CitasView() {
        setLayout(new BorderLayout());

        // Título
        JLabel title = new JLabel("Gestión de Citas", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Controles
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAgregar = new JButton("Agregar Cita");
        JButton btnModificar = new JButton("Modificar Cita");
        JButton btnEliminar = new JButton("Eliminar Cita");

        // Listeners ficticios
        btnAgregar.addActionListener(this::onAgregarCita);
        btnModificar.addActionListener(this::onModificarCita);
        btnEliminar.addActionListener(this::onEliminarCita);

        controlsPanel.add(btnAgregar);
        controlsPanel.add(btnModificar);
        controlsPanel.add(btnEliminar);

        add(controlsPanel, BorderLayout.CENTER);

        // Tabla de citas (ficticia por ahora)
        JTable citasTable = new JTable(new Object[][]{
                {"1", "Juan Pérez", "2024-12-02 10:00:00", "Corte de cabello"},
                {"2", "Ana López", "2024-12-02 11:00:00", "Coloración"},
        }, new String[]{"ID", "Cliente", "Fecha/Hora", "Detalles"});

        JScrollPane scrollPane = new JScrollPane(citasTable);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void onAgregarCita(ActionEvent e) {
        System.out.println("Agregar cita presionado");
    }

    private void onModificarCita(ActionEvent e) {
        System.out.println("Modificar cita presionado");
    }

    private void onEliminarCita(ActionEvent e) {
        System.out.println("Eliminar cita presionado");
    }
}
