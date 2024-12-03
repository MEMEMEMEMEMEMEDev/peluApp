package peluapp.view;

import peluapp.model.CitaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CitasView extends JPanel {
    private JTable citasTable;
    private DefaultTableModel tableModel;

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

        // Listeners para los botones
        btnAgregar.addActionListener(this::onAgregarCita);
        btnModificar.addActionListener(this::onModificarCita);
        btnEliminar.addActionListener(this::onEliminarCita);

        controlsPanel.add(btnAgregar);
        controlsPanel.add(btnModificar);
        controlsPanel.add(btnEliminar);

        add(controlsPanel, BorderLayout.WEST);

        // Tabla de citas
        tableModel = new DefaultTableModel(new String[]{"ID", "Cliente", "Fecha/Hora", "Detalles"}, 0);
        citasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(citasTable);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos iniciales desde la base de datos
        cargarCitas();
    }

    // Método para cargar citas desde la base de datos en la tabla
    private void cargarCitas() {
        tableModel.setRowCount(0); // Limpiar tabla
        CitaDAO citaDAO = new CitaDAO();
        List<String[]> citas = citaDAO.obtenerTodasLasCitas();

        for (String[] cita : citas) {
            tableModel.addRow(cita);
        }
    }

    // Método para agregar citas
    private void onAgregarCita(ActionEvent e) {
        String cliente = JOptionPane.showInputDialog(this, "Nombre del cliente:");
        String fechaHora = JOptionPane.showInputDialog(this, "Fecha y hora (YYYY-MM-DD HH:MM:SS):");
        String detalles = JOptionPane.showInputDialog(this, "Detalles de la cita:");

        if (cliente == null || fechaHora == null || cliente.isBlank() || fechaHora.isBlank()) {
            JOptionPane.showMessageDialog(this, "El cliente y la fecha son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CitaDAO citaDAO = new CitaDAO();
        boolean success = citaDAO.agregarCita(cliente, fechaHora, detalles);

        if (success) {
            JOptionPane.showMessageDialog(this, "Cita agregada exitosamente.");
            cargarCitas(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para modificar citas
    private void onModificarCita(ActionEvent e) {
        String id = JOptionPane.showInputDialog(this, "ID de la cita a modificar:");
        String nuevoCliente = JOptionPane.showInputDialog(this, "Nuevo nombre del cliente:");
        String nuevaFechaHora = JOptionPane.showInputDialog(this, "Nueva fecha y hora (YYYY-MM-DD HH:MM:SS):");
        String nuevosDetalles = JOptionPane.showInputDialog(this, "Nuevos detalles de la cita:");

        if (id == null || nuevoCliente == null || nuevaFechaHora == null || id.isBlank() || nuevoCliente.isBlank() || nuevaFechaHora.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CitaDAO citaDAO = new CitaDAO();
        boolean success = citaDAO.modificarCita(Integer.parseInt(id), nuevoCliente, nuevaFechaHora, nuevosDetalles);

        if (success) {
            JOptionPane.showMessageDialog(this, "Cita modificada exitosamente.");
            cargarCitas(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar citas
    private void onEliminarCita(ActionEvent e) {
        String id = JOptionPane.showInputDialog(this, "ID de la cita a eliminar:");

        if (id == null || id.isBlank()) {
            JOptionPane.showMessageDialog(this, "El ID es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CitaDAO citaDAO = new CitaDAO();
        boolean success = citaDAO.eliminarCita(Integer.parseInt(id));

        if (success) {
            JOptionPane.showMessageDialog(this, "Cita eliminada exitosamente.");
            cargarCitas(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
