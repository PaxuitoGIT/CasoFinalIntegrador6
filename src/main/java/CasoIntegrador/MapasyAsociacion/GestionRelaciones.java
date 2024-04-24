package CasoIntegrador.MapasyAsociacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GestionRelaciones extends JFrame {
    // Variables de instancia para los componentes de la interfaz
    JPanel contentPane;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
    JButton agregarRelacionButton, buscarRelacionButton;
    Map<Integer, Character> relaciones;

    // Constructor de la clase
    public GestionRelaciones() {
        // Configuración de la ventana principal
        setTitle("Gestión de Relaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());

        // Inicializamos el HashMap para almacenar las relaciones
        relaciones = new HashMap<>();

        // Creamos el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Número");
        tableModel.addColumn("Letra");

        // Creamos la tabla y le asignamos el modelo
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botón para agregar una nueva relación
        agregarRelacionButton = new JButton("Agregar Relación");
        agregarRelacionButton.addActionListener(e -> agregarRelacion());
        contentPane.add(agregarRelacionButton, BorderLayout.SOUTH);

        // Botón para buscar una relación por número
        buscarRelacionButton = new JButton("Buscar Relación");
        buscarRelacionButton.addActionListener(e -> buscarRelacion());
        contentPane.add(buscarRelacionButton, BorderLayout.NORTH);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

    // Método para agregar una nueva relación al HashMap
    public void agregarRelacion() {
        // Ventana de diálogo para ingresar el número y la letra
        String numeroStr = JOptionPane.showInputDialog("Ingrese un número:");
        String letra = JOptionPane.showInputDialog("Ingrese una letra:");

        // Verificamos que se ingresaron datos y son válidos
        if (numeroStr != null && letra != null && !numeroStr.isEmpty() && !letra.isEmpty()) {
            try {
                int numero = Integer.parseInt(numeroStr);
                // Agregamos la relación al HashMap
                relaciones.put(numero, letra.charAt(0));
                // Actualizamos la tabla con los datos del HashMap
                actualizarTabla();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para buscar una relación por número
    public void buscarRelacion() {
        // Ventana de diálogo para ingresar el número a buscar
        String numeroStr = JOptionPane.showInputDialog("Ingrese el número a buscar:");
        if (numeroStr != null && !numeroStr.isEmpty()) {
            try {
                int numero = Integer.parseInt(numeroStr);
                // Buscamos la relación en el HashMap
                Character letra = relaciones.get(numero);
                if (letra != null) {
                    JOptionPane.showMessageDialog(this, "El número " + numero + " está asociado a la letra: " + letra, "Relación Encontrada", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ninguna relación para el número " + numero, "Relación no Encontrada", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para actualizar la tabla con los datos del HashMap
    public void actualizarTabla() {
        // Limpiamos el modelo de la tabla
        tableModel.setRowCount(0);
        // Agregamos las relaciones del HashMap al modelo de la tabla
        for (Map.Entry<Integer, Character> entry : relaciones.entrySet()) {
            tableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionRelaciones().setVisible(true));
    }
}
