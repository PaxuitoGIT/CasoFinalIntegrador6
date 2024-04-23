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
     JTable nombresTable, ventasTable;
     JPanel contentPane, nombresPanel, ventasPanel;
     JScrollPane nombresScrollPane, ventasScrollPane;
     DefaultTableModel nombresTableModel, ventasTableModel;
     boolean ordenarPorProducto, ordenarNombresAscendente = true;
     JButton agregarNombreButton, cambiarOrdenNombresButton, agregarVentaButton, cambiarOrdenVentasButton;

    public OrdenacionYBusqueda() {
        setTitle("Ordenación y Búsqueda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(1, 2));

        // Panel para los nombres
        nombresPanel = new JPanel(new BorderLayout());
        nombresPanel.setBorder(BorderFactory.createTitledBorder("Nombres"));

        nombresTableModel = new DefaultTableModel();
        nombresTableModel.addColumn("Nombres");

        nombresTable = new JTable(nombresTableModel);
        nombresScrollPane = new JScrollPane(nombresTable);
        nombresPanel.add(nombresScrollPane, BorderLayout.CENTER);

        agregarNombreButton = new JButton("Agregar Nombre");
        agregarNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNombre(JOptionPane.showInputDialog("Ingrese un nombre:"));
            }
        });
        nombresPanel.add(agregarNombreButton, BorderLayout.SOUTH);

        cambiarOrdenNombresButton = new JButton("Cambiar Orden");
        cambiarOrdenNombresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarOrdenNombres();
            }
        });
        nombresPanel.add(cambiarOrdenNombresButton, BorderLayout.NORTH);

        contentPane.add(nombresPanel);

        // Panel para las ventas
        ventasPanel = new JPanel(new BorderLayout());
        ventasPanel.setBorder(BorderFactory.createTitledBorder("Ventas"));

        ventasTableModel = new DefaultTableModel();
        ventasTableModel.addColumn("Producto");
        ventasTableModel.addColumn("Cantidad");

        ventasTable = new JTable(ventasTableModel);
        ventasScrollPane = new JScrollPane(ventasTable);
        ventasPanel.add(ventasScrollPane, BorderLayout.CENTER);

        agregarVentaButton = new JButton("Agregar Venta");
        agregarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVenta(JOptionPane.showInputDialog("Ingrese un producto:"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese una cantidad:")));
            }
        });
        ventasPanel.add(agregarVentaButton, BorderLayout.SOUTH);

        cambiarOrdenVentasButton = new JButton("Cambiar Orden");
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

    public DefaultTableModel getNombresTableModel() {
        return nombresTableModel;
    }
    public void inicializarNombres() {
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

    public void inicializarVentas() {
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

    public void agregarNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            nombresTableModel.addRow(new Object[]{nombre});
            ordenarNombres();
        }
    }

    public void agregarVenta(String producto, int cantidad) {
        ventasTableModel.addRow(new Object[]{producto, cantidad});
        ordenarVentas();
    }


    public void cambiarOrdenNombres() {
        ordenarNombresAscendente = !ordenarNombresAscendente;
        ordenarNombres();
    }

    public void cambiarOrdenVentas() {
        ordenarPorProducto = !ordenarPorProducto;
        ordenarVentas();
    }

    public void ordenarNombres() {
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

    public void ordenarVentas() {
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