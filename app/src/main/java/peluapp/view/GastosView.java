package peluapp.view;

import peluapp.model.GastoDAO;
import peluapp.utils.InputValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GastosView extends JPanel {
    private JTable gastosTable;
    private DefaultTableModel tableModel;

    public GastosView() {
        setLayout(new BorderLayout());

        // Título
        JLabel title = new JLabel("Gestión de Gastos e Ingresos", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblMonto = new JLabel("Monto:");
        JTextField txtMonto = new JTextField();

        JLabel lblTipo = new JLabel("Tipo:");
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Gasto", "Ingreso"});

        JLabel lblFormaPago = new JLabel("Forma de Pago:");
        JComboBox<String> cbFormaPago = new JComboBox<>(new String[]{"Transferencia", "Efectivo", "Débito", "Crédito", "Canje"});

        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextField txtDescripcion = new JTextField();

        formPanel.add(lblMonto);
        formPanel.add(txtMonto);

        formPanel.add(lblTipo);
        formPanel.add(cbTipo);

        formPanel.add(lblFormaPago);
        formPanel.add(cbFormaPago);

        formPanel.add(lblDescripcion);
        formPanel.add(txtDescripcion);

        add(formPanel, BorderLayout.WEST);

        // Botones
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(e -> onAgregarGasto(txtMonto, cbTipo, cbFormaPago, txtDescripcion));
        btnModificar.addActionListener(e -> onModificarGasto(txtMonto, cbTipo, cbFormaPago, txtDescripcion));
        btnEliminar.addActionListener(e -> onEliminarGasto());

        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.EAST);

        // Tabla de gastos
        tableModel = new DefaultTableModel(new String[]{"ID", "Monto", "Tipo", "Forma de Pago", "Descripción"}, 0);
        gastosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(gastosTable);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos iniciales desde la base de datos
        cargarGastos();
    }

    // Método para cargar gastos desde la base de datos
    private void cargarGastos() {
        tableModel.setRowCount(0); // Limpiar tabla
        GastoDAO gastoDAO = new GastoDAO();
        List<String[]> gastos = gastoDAO.obtenerTodosLosGastos();

        for (String[] gasto : gastos) {
            tableModel.addRow(gasto);
        }
    }

    // Método para agregar gastos
    private void onAgregarGasto(JTextField txtMonto, JComboBox<String> cbTipo, JComboBox<String> cbFormaPago, JTextField txtDescripcion) {
        String monto = txtMonto.getText();
        String tipo = (String) cbTipo.getSelectedItem();
        String formaPago = (String) cbFormaPago.getSelectedItem();
        String descripcion = txtDescripcion.getText();

        if (!InputValidator.validarNumero(monto, 1, Integer.MAX_VALUE)) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!InputValidator.validarTexto(descripcion, 255)) {
            JOptionPane.showMessageDialog(this, "La descripción no es válida o supera los 255 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GastoDAO gastoDAO = new GastoDAO();
        boolean success = gastoDAO.agregarGasto(Double.parseDouble(monto), tipo, formaPago, descripcion);

        if (success) {
            JOptionPane.showMessageDialog(this, "Gasto agregado exitosamente.");
            cargarGastos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para modificar gastos
    private void onModificarGasto(JTextField txtMonto, JComboBox<String> cbTipo, JComboBox<String> cbFormaPago, JTextField txtDescripcion) {
        String id = JOptionPane.showInputDialog(this, "ID del gasto a modificar:");
        String monto = txtMonto.getText();
        String tipo = (String) cbTipo.getSelectedItem();
        String formaPago = (String) cbFormaPago.getSelectedItem();
        String descripcion = txtDescripcion.getText();

        if (!InputValidator.validarNumero(id, 1, Integer.MAX_VALUE)) {
            JOptionPane.showMessageDialog(this, "El ID no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!InputValidator.validarNumero(monto, 1, Integer.MAX_VALUE)) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!InputValidator.validarTexto(descripcion, 255)) {
            JOptionPane.showMessageDialog(this, "La descripción no es válida o supera los 255 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GastoDAO gastoDAO = new GastoDAO();
        boolean success = gastoDAO.modificarGasto(Integer.parseInt(id), Double.parseDouble(monto), tipo, formaPago, descripcion);

        if (success) {
            JOptionPane.showMessageDialog(this, "Gasto modificado exitosamente.");
            cargarGastos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar gastos
    private void onEliminarGasto() {
        String id = JOptionPane.showInputDialog(this, "ID del gasto a eliminar:");

        if (!InputValidator.validarNumero(id, 1, Integer.MAX_VALUE)) {
            JOptionPane.showMessageDialog(this, "El ID no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GastoDAO gastoDAO = new GastoDAO();
        boolean success = gastoDAO.eliminarGasto(Integer.parseInt(id));

        if (success) {
            JOptionPane.showMessageDialog(this, "Gasto eliminado exitosamente.");
            cargarGastos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
