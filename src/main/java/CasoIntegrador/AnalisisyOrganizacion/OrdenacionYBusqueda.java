package CasoIntegrador.AnalisisyOrganizacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class OrdenacionYBusqueda extends JFrame {
    private JTable nombresTable;
    private JTable ventasTable;
    private DefaultTableModel nombresTableModel;
    private DefaultTableModel ventasTableModel;
    private boolean ordenarPorProducto = true;
    private boolean ordenarNombresAscendente = true;

    public OrdenacionYBusqueda() {
        setTitle("Ordenación y Búsqueda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(1, 2));

        // Panel para los nombres
        JPanel nombresPanel = new JPanel(new BorderLayout());
        nombresPanel.setBorder(BorderFactory.createTitledBorder("Nombres"));

        nombresTableModel = new DefaultTableModel();
        nombresTableModel.addColumn("Nombres");

        nombresTable = new JTable(nombresTableModel);
        JScrollPane nombresScrollPane = new JScrollPane(nombresTable);
        nombresPanel.add(nombresScrollPane, BorderLayout.CENTER);

        JButton agregarNombreButton = new JButton("Agregar Nombre");
        agregarNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNombre();
            }
        });
        nombresPanel.add(agregarNombreButton, BorderLayout.SOUTH);

        JButton cambiarOrdenNombresButton = new JButton("Cambiar Orden");
        cambiarOrdenNombresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarOrdenNombres();
            }
        });
        nombresPanel.add(cambiarOrdenNombresButton, BorderLayout.NORTH);

        contentPane.add(nombresPanel);

        // Panel para las ventas
        JPanel ventasPanel = new JPanel(new BorderLayout());
        ventasPanel.setBorder(BorderFactory.createTitledBorder("Ventas"));

        ventasTableModel = new DefaultTableModel();
        ventasTableModel.addColumn("Producto");
        ventasTableModel.addColumn("Cantidad");

        ventasTable = new JTable(ventasTableModel);
        JScrollPane ventasScrollPane = new JScrollPane(ventasTable);
        ventasPanel.add(ventasScrollPane, BorderLayout.CENTER);

        JButton agregarVentaButton = new JButton("Agregar Venta");
        agregarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVenta();
            }
        });
        ventasPanel.add(agregarVentaButton, BorderLayout.SOUTH);

        JButton cambiarOrdenVentasButton = new JButton("Cambiar Orden");
        cambiarOrdenVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarOrdenVentas();
            }
        });
        ventasPanel.add(cambiarOrdenVentasButton, BorderLayout.NORTH);

        contentPane.add(ventasPanel);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);

        // Inicializar nombres y ventas
        inicializarNombres();
        inicializarVentas();
    }

    private void inicializarNombres() {
        TreeSet<String> nombres = new TreeSet<>();
        nombres.add("Juan");
        nombres.add("María");
        nombres.add("Pedro");
        nombres.add("Ana");
        nombres.add("Luis");
        nombres.add("Sofía");

        for (String nombre : nombres) {
            nombresTableModel.addRow(new Object[]{nombre});
        }
    }

    private void inicializarVentas() {
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta("BMW", 100));
        ventas.add(new Venta("Mercedes", 200));
        ventas.add(new Venta("Citroen", 150));
        ventas.add(new Venta("Honda", 50));
        ventas.add(new Venta("Flat", 300));
        ventas.add(new Venta("Kia", 250));

        for (Venta venta : ventas) {
            ventasTableModel.addRow(new Object[]{venta.getProducto(), venta.getCantidad()});
        }
    }

    private void agregarNombre() {
        String nombre = JOptionPane.showInputDialog("Ingrese un nombre:");
        if (nombre != null && !nombre.isEmpty()) {
            nombresTableModel.addRow(new Object[]{nombre});
            ordenarNombres();
        }
    }

    private void agregarVenta() {
        String producto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        if (producto != null && !producto.isEmpty()) {
            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad:");
            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    ventasTableModel.addRow(new Object[]{producto, cantidad});
                    ordenarVentas();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void cambiarOrdenNombres() {
        ordenarNombresAscendente = !ordenarNombresAscendente;
        ordenarNombres();
    }

    private void cambiarOrdenVentas() {
        ordenarPorProducto = !ordenarPorProducto;
        ordenarVentas();
    }

    private void ordenarNombres() {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < nombresTableModel.getRowCount(); i++) {
            nombres.add((String) nombresTableModel.getValueAt(i, 0));
        }
        if (ordenarNombresAscendente) {
            Collections.sort(nombres);
        } else {
            Collections.sort(nombres, Collections.reverseOrder());
        }
        for (int i = 0; i < nombres.size(); i++) {
            nombresTableModel.setValueAt(nombres.get(i), i, 0);
        }
    }

    private void ordenarVentas() {
        List<Object[]> ventas = new ArrayList<>();
        for (int i = 0; i < ventasTableModel.getRowCount(); i++) {
            Object[] venta = new Object[]{ventasTableModel.getValueAt(i, 0), ventasTableModel.getValueAt(i, 1)};
            ventas.add(venta);
        }
        if (ordenarPorProducto) {
            Collections.sort(ventas, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] v1, Object[] v2) {
                    return ((String) v1[0]).compareTo((String) v2[0]);
                }
            });
        } else {
            Collections.sort(ventas, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] v1, Object[] v2) {
                    return ((Integer) v1[1]).compareTo((Integer) v2[1]);
                }
            });
        }
        for (int i = 0; i < ventas.size(); i++) {
            ventasTableModel.setValueAt(ventas.get(i)[0], i, 0);
            ventasTableModel.setValueAt(ventas.get(i)[1], i, 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrdenacionYBusqueda().setVisible(true);
            }
        });
    }
}