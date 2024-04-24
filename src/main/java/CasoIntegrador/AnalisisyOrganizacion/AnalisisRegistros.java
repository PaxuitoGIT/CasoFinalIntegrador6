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
    // Variables de instancia para los componentes de la interfaz
     JComboBox<String> filtroClienteComboBox;
     JTable transaccionesTable;
     JPanel contentPane, controlPanel, agregarPanel, filtrarPanel;
     JScrollPane scrollPane;
     JLabel filtroClienteLabel, productoLabel, cantidadLabel, clienteLabel, fechaLabel;
     JButton filtrarButton, mostrarTodoButton, agregarButton, eliminarButton;
     DefaultTableModel tableModel;
     JTextField productoField, cantidadField, clienteField, fechaField;

     // Constructor de la clase
    public AnalisisRegistros() {
        // Configuración de la ventana principal
        setTitle("Análisis de Registros de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Creación del panel principal y configuración de los bordes
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());

        // Creación del modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Producto");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Fecha");

        // Creación de la tabla y asignación del modelo
        transaccionesTable = new JTable(tableModel);
        scrollPane = new JScrollPane(transaccionesTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Creación del panel de control y configuración del layout
        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        // Creación del panel para filtrar transacciones por cliente
        filtrarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filtroClienteLabel = new JLabel("Filtrar por Cliente:");
        filtrarPanel.add(filtroClienteLabel);

        // Creación del combo box para seleccionar el cliente a filtrar
        filtroClienteComboBox = new JComboBox<>();
        filtrarPanel.add(filtroClienteComboBox);

        // Botón para aplicar el filtro
        filtrarButton = new JButton("Filtrar");
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarTransacciones();
            }
        });
        filtrarPanel.add(filtrarButton);

        // Botón para mostrar todas las transacciones
        mostrarTodoButton = new JButton("Mostrar Todo");
        mostrarTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodasTransacciones();
            }
        });
        filtrarPanel.add(mostrarTodoButton);

        // Agregar el panel de filtrado al panel de control
        controlPanel.add(filtrarPanel, BorderLayout.NORTH);

        // Creación de los paneles
        agregarPanel = new JPanel(new GridLayout(5, 2, 6, 5));
        productoLabel = new JLabel("Producto:");
        agregarPanel.add(productoLabel);
        productoField = new JTextField();
        agregarPanel.add(productoField);

        cantidadLabel = new JLabel("Cantidad:");
        agregarPanel.add(cantidadLabel);
        cantidadField = new JTextField();
        agregarPanel.add(cantidadField);

        clienteLabel = new JLabel("Cliente:");
        agregarPanel.add(clienteLabel);
        clienteField = new JTextField();
        agregarPanel.add(clienteField);

        fechaLabel = new JLabel("Fecha:");
        agregarPanel.add(fechaLabel);
        fechaField = new JTextField();
        agregarPanel.add(fechaField);

        // Botón para agregar el registro
        agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTransaccion(productoField.getText(), Integer.parseInt(cantidadField.getText()),
                        clienteField.getText(), fechaField.getText());
            }
        });
        agregarPanel.add(agregarButton);

        // Botón para eliminar el registro seleccionado
        eliminarButton = new JButton("Eliminar");
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

    // La lógica de mostrar transacciones
    public void mostrarTodasTransacciones() {
        List<TransaccionVenta> transacciones = obtenerTransacciones();
        mostrarTransacciones(transacciones);
    }

    public void mostrarTransacciones(List<TransaccionVenta> transacciones) {
        tableModel.setRowCount(0);
        for (TransaccionVenta transaccion : transacciones) {
            tableModel.addRow(new Object[]{transaccion.getProducto(), transaccion.getCantidad(),
                    transaccion.getCliente(), transaccion.getFecha()});
        }
    }

    // La lógica de filtrar transacciones
    public void filtrarTransacciones() {
        String clienteSeleccionado = (String) filtroClienteComboBox.getSelectedItem();
        if (clienteSeleccionado != null && !clienteSeleccionado.isEmpty()) {
            List<TransaccionVenta> transaccionesFiltradas = obtenerTransaccionesPorCliente(clienteSeleccionado);
            mostrarTransacciones(transaccionesFiltradas);
        } else {
            mostrarTodasTransacciones();
        }
    }

    public List<String> obtenerClientesUnicos() {
        List<String> clientesUnicos = new ArrayList<>();
        for (TransaccionVenta transaccion : obtenerTransacciones()) {
            if (!clientesUnicos.contains(transaccion.getCliente())) {
                clientesUnicos.add(transaccion.getCliente());
            }
        }
        return clientesUnicos;
    }

    public List<TransaccionVenta> obtenerTransaccionesPorCliente(String cliente) {
        List<TransaccionVenta> transaccionesPorCliente = new ArrayList<>();
        for (TransaccionVenta transaccion : obtenerTransacciones()) {
            if (transaccion.getCliente().equals(cliente)) {
                transaccionesPorCliente.add(transaccion);
            }
        }
        return transaccionesPorCliente;
    }

    // Ejemplo de datos de transacciones
    public List<TransaccionVenta> obtenerTransacciones() {
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

    // Métodos para agregar y eliminar transacciones
    public void agregarTransaccion(String producto, int cantidad, String cliente, String fecha) {
        if (producto != null && !producto.isEmpty() && cliente != null && !cliente.isEmpty() && fecha != null && !fecha.isEmpty()) {
            TransaccionVenta transaccion = new TransaccionVenta(producto, cantidad, cliente, fecha);
            tableModel.addRow(new Object[]{transaccion.getProducto(), transaccion.getCantidad(),
                    transaccion.getCliente(), transaccion.getFecha()});

            // Si la nueva transacción coincide con el filtro actual, también la mostramos
            String clienteSeleccionado = (String) filtroClienteComboBox.getSelectedItem();
            if (clienteSeleccionado != null && clienteSeleccionado.equals(cliente)) {
                mostrarTransacciones(obtenerTransaccionesPorCliente(clienteSeleccionado));
            }
        }
    }

    public void eliminarTransaccion() {
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