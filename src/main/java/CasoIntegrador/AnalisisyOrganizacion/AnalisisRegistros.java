package CasoIntegrador.AnalisisyOrganizacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class AnalisisRegistros extends JFrame {
    private JComboBox<String> filtroClienteComboBox;
    private JTable transaccionesTable;
    private DefaultTableModel tableModel;
    private JTextField productoField;
    private JTextField cantidadField;
    private JTextField clienteField;
    private JTextField fechaField;

    public AnalisisRegistros() {
        setTitle("Análisis de Registros de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Producto");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Fecha");

        transaccionesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transaccionesTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        JPanel filtrarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel filtroClienteLabel = new JLabel("Filtrar por Cliente:");
        filtrarPanel.add(filtroClienteLabel);

        filtroClienteComboBox = new JComboBox<>();
        filtrarPanel.add(filtroClienteComboBox);

        JButton filtrarButton = new JButton("Filtrar");
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarTransacciones();
            }
        });
        filtrarPanel.add(filtrarButton);

        JButton mostrarTodoButton = new JButton("Mostrar Todo");
        mostrarTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodasTransacciones();
            }
        });
        filtrarPanel.add(mostrarTodoButton);

        controlPanel.add(filtrarPanel, BorderLayout.NORTH);

        JPanel agregarPanel = new JPanel(new GridLayout(5, 2, 6, 5));
        JLabel productoLabel = new JLabel("Producto:");
        agregarPanel.add(productoLabel);
        productoField = new JTextField();
        agregarPanel.add(productoField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        agregarPanel.add(cantidadLabel);
        cantidadField = new JTextField();
        agregarPanel.add(cantidadField);

        JLabel clienteLabel = new JLabel("Cliente:");
        agregarPanel.add(clienteLabel);
        clienteField = new JTextField();
        agregarPanel.add(clienteField);

        JLabel fechaLabel = new JLabel("Fecha:");
        agregarPanel.add(fechaLabel);
        fechaField = new JTextField();
        agregarPanel.add(fechaField);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTransaccion();
            }
        });
        agregarPanel.add(agregarButton);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTransaccion();
            }
        });
        agregarPanel.add(eliminarButton);

        controlPanel.add(agregarPanel, BorderLayout.SOUTH);

        contentPane.add(controlPanel, BorderLayout.EAST);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);

        // Inicializar combo box con clientes únicos
        List<String> clientes = obtenerClientesUnicos();
        for (String cliente : clientes) {
            filtroClienteComboBox.addItem(cliente);
        }

        // Mostrar todas las transacciones al inicio
        mostrarTodasTransacciones();
    }

    private void mostrarTodasTransacciones() {
        List<TransaccionVenta> transacciones = obtenerTransacciones();
        mostrarTransacciones(transacciones);
    }

    private void mostrarTransacciones(List<TransaccionVenta> transacciones) {
        tableModel.setRowCount(0);
        for (TransaccionVenta transaccion : transacciones) {
            tableModel.addRow(new Object[]{transaccion.getProducto(), transaccion.getCantidad(),
                    transaccion.getCliente(), transaccion.getFecha()});
        }
    }

    private void filtrarTransacciones() {
        String clienteSeleccionado = (String) filtroClienteComboBox.getSelectedItem();
        if (clienteSeleccionado != null && !clienteSeleccionado.isEmpty()) {
            List<TransaccionVenta> transaccionesFiltradas = obtenerTransaccionesPorCliente(clienteSeleccionado);
            mostrarTransacciones(transaccionesFiltradas);
        } else {
            mostrarTodasTransacciones();
        }
    }

    private List<String> obtenerClientesUnicos() {
        List<String> clientesUnicos = new ArrayList<>();
        for (TransaccionVenta transaccion : obtenerTransacciones()) {
            if (!clientesUnicos.contains(transaccion.getCliente())) {
                clientesUnicos.add(transaccion.getCliente());
            }
        }
        return clientesUnicos;
    }

    private List<TransaccionVenta> obtenerTransaccionesPorCliente(String cliente) {
        List<TransaccionVenta> transaccionesPorCliente = new ArrayList<>();
        for (TransaccionVenta transaccion : obtenerTransacciones()) {
            if (transaccion.getCliente().equals(cliente)) {
                transaccionesPorCliente.add(transaccion);
            }
        }
        return transaccionesPorCliente;
    }

    private List<TransaccionVenta> obtenerTransacciones() {
        List<TransaccionVenta> transacciones = new ArrayList<>();
        transacciones.add(new TransaccionVenta("Chicle", 100, "Cliente1", "01/04/2024"));
        transacciones.add(new TransaccionVenta("Chocolate", 200, "Cliente2", "05/04/2024"));
        transacciones.add(new TransaccionVenta("Galletas", 300, "Cliente1", "10/04/2024"));
        transacciones.add(new TransaccionVenta("Chuches", 150, "Cliente3", "10/04/2024"));
        transacciones.add(new TransaccionVenta("Caramelos", 50, "Cliente2", "15/04/2024"));
        transacciones.add(new TransaccionVenta("Yogur", 250, "Cliente4", "20/04/2024"));
        transacciones.add(new TransaccionVenta("Leche", 120, "Cliente1", "25/04/2024"));
        return transacciones;
    }

    private void agregarTransaccion() {
        String producto = productoField.getText();
        int cantidad = Integer.parseInt(cantidadField.getText());
        String cliente = clienteField.getText();
        String fecha = fechaField.getText();

        TransaccionVenta transaccion = new TransaccionVenta(producto, cantidad, cliente, fecha);
        tableModel.addRow(new Object[]{transaccion.getProducto(), transaccion.getCantidad(),
                transaccion.getCliente(), transaccion.getFecha()});

        // Si la nueva transacción coincide con el filtro actual, también la mostramos
        String clienteSeleccionado = (String) filtroClienteComboBox.getSelectedItem();
        if (clienteSeleccionado != null && clienteSeleccionado.equals(cliente)) {
            mostrarTransacciones(obtenerTransaccionesPorCliente(clienteSeleccionado));
        }
    }

    private void eliminarTransaccion() {
        int filaSeleccionada = transaccionesTable.getSelectedRow();
        if (filaSeleccionada != -1) {
            tableModel.removeRow(filaSeleccionada);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AnalisisRegistros().setVisible(true);
            }
        });
    }
}