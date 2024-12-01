package peluapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GastosView extends JPanel {

    public GastosView() {
        setLayout(new BorderLayout());

        // Título
        JLabel title = new JLabel("Gestión de Gastos e Ingresos", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos del formulario
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

        btnAgregar.addActionListener(e -> System.out.println("Agregar gasto: " + txtMonto.getText()));
        btnModificar.addActionListener(e -> System.out.println("Modificar gasto seleccionado"));
        btnEliminar.addActionListener(e -> System.out.println("Eliminar gasto seleccionado"));

        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.EAST);

        // Tabla de gastos
        JTable gastosTable = new JTable(new Object[][]{
                {"1", "10000", "Gasto", "Efectivo", "Compra de Shampoo"},
                {"2", "15000", "Ingreso", "Transferencia", "Pago de cliente"}
        }, new String[]{"ID", "Monto", "Tipo", "Forma de Pago", "Descripción"});

        JScrollPane scrollPane = new JScrollPane(gastosTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
