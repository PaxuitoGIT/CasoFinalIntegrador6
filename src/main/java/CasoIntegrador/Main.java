package CasoIntegrador;

import CasoIntegrador.AnalisisyOrganizacion.Busqueda;
import CasoIntegrador.DatosDinamicos.ListaDatos;
import CasoIntegrador.DatosDinamicos.Pareja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Vector;

public class Main extends JFrame {
    JPanel panNorth;
    JLabel lblId, lblNombre, lblVentas;
    JTextField txtId, txtNombre, txtVentas;
    JTable tabMain;
    DefaultTableModel dtmMain;
    JButton btnAgregar, btnEliminar, btnModificar, btnBuscar;

    ListaDatos listaDatos = new ListaDatos();

    public Main() {
        panNorth = new JPanel(new GridLayout(5, 2));

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

        dtmMain = new DefaultTableModel() {};
        tabMain = new JTable(dtmMain);

        dtmMain.addColumn("ID");
        dtmMain.addColumn("Nombre");
        dtmMain.addColumn("Ventas");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtmMain);
        sorter.setComparator(0, Comparator.comparingInt(str -> Integer.parseInt((String) str))); //Soluciona la ordenación por ID
        tabMain.setRowSorter(sorter);


        setTitle("Tabla de Ventas");
        setSize(500,500);
        setLocation(100,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreInput = JOptionPane.showInputDialog("Ingrese el nombre a buscar");
                if (nombreInput != null) {
                    int idEncontrado = Busqueda.buscarIdPorNombreEnLista(listaDatos, nombreInput);
                    if (idEncontrado != -1) {
                        JOptionPane.showMessageDialog(Main.this, "El ID asociado a " + nombreInput + " es: " + idEncontrado);
                    } else {
                        JOptionPane.showMessageDialog(Main.this, "No se encontró ningún registro con el nombre: " + nombreInput);
                    }
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabMain.getSelectedRow();
                if(filaSeleccionada != -1) {
                    dtmMain.setValueAt(txtId.getText(), filaSeleccionada, 0);
                    dtmMain.setValueAt(txtNombre.getText(), filaSeleccionada, 1);
                    dtmMain.setValueAt(txtVentas.getText(), filaSeleccionada, 2);
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Seleccione una fila");
                }
            }
        });

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
        new Main();
    }
}
