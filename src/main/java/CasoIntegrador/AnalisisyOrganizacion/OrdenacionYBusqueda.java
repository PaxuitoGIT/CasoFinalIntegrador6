package CasoIntegrador.AnalisisyOrganizacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class OrdenacionYBusqueda extends JFrame {
    private JTable nombresTable;
    private JTable ventasTable;
    private DefaultTableModel tableModel;

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

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombres");

        nombresTable = new JTable(tableModel);
        JScrollPane nombresScrollPane = new JScrollPane(nombresTable);
        nombresPanel.add(nombresScrollPane, BorderLayout.CENTER);

        contentPane.add(nombresPanel);

        // Panel para las ventas
        JPanel ventasPanel = new JPanel(new BorderLayout());
        ventasPanel.setBorder(BorderFactory.createTitledBorder("Ventas"));

        DefaultTableModel ventasTableModel = new DefaultTableModel();
        ventasTableModel.addColumn("Producto");
        ventasTableModel.addColumn("Cantidad");

        ventasTable = new JTable(ventasTableModel);
        JScrollPane ventasScrollPane = new JScrollPane(ventasTable);
        ventasPanel.add(ventasScrollPane, BorderLayout.CENTER);

        contentPane.add(ventasPanel);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);

        // Ordenar nombres usando TreeSet
        TreeSet<String> nombres = new TreeSet<>();
        nombres.add("Juan");
        nombres.add("María");
        nombres.add("Pedro");
        nombres.add("Ana");

        for (String nombre : nombres) {
            tableModel.addRow(new Object[]{nombre});
        }

        // Ordenar ventas por diferentes criterios
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta("Producto1", 100));
        ventas.add(new Venta("Producto2", 200));
        ventas.add(new Venta("Producto3", 150));

        // Ordenar ventas por cantidad
        Collections.sort(ventas, new Comparator<Venta>() {
            @Override
            public int compare(Venta v1, Venta v2) {
                return v1.getCantidad() - v2.getCantidad();
            }
        });

        // Mostrar las ventas ordenadas en la tabla
        for (Venta venta : ventas) {
            ventasTableModel.addRow(new Object[]{venta.getProducto(), venta.getCantidad()});
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