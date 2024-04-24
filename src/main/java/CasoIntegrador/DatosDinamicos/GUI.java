package CasoIntegrador.DatosDinamicos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Vector;

public class GUI extends JFrame {
    // Variables de instancia para los componentes de la interfaz
    JPanel panNorth;
    JLabel lblId, lblNombre, lblVentas;
    JTextField txtId, txtNombre, txtVentas;
    JTable tabMain;
    DefaultTableModel dtmMain;
    JButton btnAgregar, btnEliminar, btnModificar, btnBuscar;

    ListaDatos listaDatos = new ListaDatos();

    // Constructor de la clase
    public GUI() {
        panNorth = new JPanel(new GridLayout(5, 2));

        // Creación de los componentes de la interfaz
        lblId = new JLabel("ID:");
        lblNombre = new JLabel("Nombre:");
        lblVentas = new JLabel("Ventas:");

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtVentas = new JTextField();

        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnBuscar = new JButton("Buscar");

        // Agregamos los componentes al panel
        panNorth.add(lblId);
        panNorth.add(txtId);
        panNorth.add(lblNombre);
        panNorth.add(txtNombre);
        panNorth.add(lblVentas);
        panNorth.add(txtVentas);
        panNorth.add(btnAgregar);
        panNorth.add(btnModificar);
        panNorth.add(btnEliminar);
        panNorth.add(btnBuscar);

        // Creación del modelo de la tabla
        dtmMain = new DefaultTableModel() {};
        tabMain = new JTable(dtmMain);

        dtmMain.addColumn("ID");
        dtmMain.addColumn("Nombre");
        dtmMain.addColumn("Ventas");

        // Agregamos un ordenador a la tabla que si se pincha en la cabecera de la columna, ordene
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtmMain);
        sorter.setComparator(0, Comparator.naturalOrder());
        tabMain.setRowSorter(sorter);


        setTitle("Tabla de Ventas");
        setSize(500,500);
        setLocation(100,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Acciones de los botones

        // Acción del botón agregar que agrega un nuevo registro a la tabla y a la lista de datos
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pareja<Integer, String> pareja = new Pareja<>(Integer.parseInt(txtId.getText()), txtNombre.getText());
                listaDatos.agregarDatoPar(pareja);
                Vector row = new Vector();
                row.add(txtId.getText());
                row.add(txtNombre.getText());
                row.add(txtVentas.getText());
                dtmMain.addRow(row);
                txtId.setText("");
                txtNombre.setText("");
                txtVentas.setText("");
                txtId.requestFocus();
            }
        });

        // Acción del botón buscar que busca un nombre en la lista de datos y muestra el ID asociado (no usa hashmap)
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreInput = JOptionPane.showInputDialog("Ingrese el nombre a buscar");
                if (nombreInput != null) {
                    int idEncontrado = Buscar.buscarIdPorNombreEnLista(listaDatos, nombreInput);
                    if (idEncontrado != -1) {
                        JOptionPane.showMessageDialog(GUI.this, "El ID asociado a " + nombreInput + " es: " + idEncontrado);
                    } else {
                        JOptionPane.showMessageDialog(GUI.this, "No se encontró ningún registro con el nombre: " + nombreInput);
                    }
                }
            }
        });

        // Acción del botón modificar que modifica un registro de la tabla y de la lista de datos
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabMain.getSelectedRow();
                if(filaSeleccionada != -1) {
                    dtmMain.setValueAt(txtId.getText(), filaSeleccionada, 0);
                    dtmMain.setValueAt(txtNombre.getText(), filaSeleccionada, 1);
                    dtmMain.setValueAt(txtVentas.getText(), filaSeleccionada, 2);
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Seleccione una fila");
                }
            }
        });

        // Acción del botón eliminar que elimina un registro de la tabla y de la lista de datos
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tabMain.getSelectedRow();
                if (i!=-1) {
                    listaDatos.eliminarDatoPar(listaDatos.getDatosPares().get(i));
                    dtmMain.removeRow(i);
                }
            }
        });

        add(panNorth, BorderLayout.NORTH);
        add(new JScrollPane(tabMain), BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    // Clase interna para buscar un ID por nombre en una lista de datos
    public static class Buscar {
        public static int buscarIdPorNombreEnLista(ListaDatos listaDatos, String nombre) {
            for (Pareja<Integer, String> pareja : listaDatos.getDatosPares()) {
                if (pareja.getSegundoElemento().equals(nombre)) {
                    return pareja.getPrimerElemento();
                }
            }
            return -1; // Si no se encuentra el nombre, devolvemos -1
        }
    }
}

