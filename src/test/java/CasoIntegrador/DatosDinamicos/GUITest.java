package CasoIntegrador.DatosDinamicos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITest {

    private GUI gui;

    @BeforeEach
    public void setUp() {
        gui = new GUI();
    }

    @Test
    public void testAgregar() {
        // Simular la entrada de datos
        gui.txtId.setText("1");
        gui.txtNombre.setText("Producto A");
        gui.txtVentas.setText("100");

        // Llamar directamente al ActionListener del botón Agregar
        for (ActionListener listener : gui.btnAgregar.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(gui.btnAgregar, ActionEvent.ACTION_PERFORMED, null));
        }

        // Verificar si se ha agregado una fila a la tabla
        assertEquals(1, gui.dtmMain.getRowCount());
    }

    @Test
    public void testEliminar() {
        // Agregar un dato para eliminar
        Pareja<Integer, String> pareja = new Pareja<>(1, "Producto A");
        gui.listaDatos.agregarDatoPar(pareja);
        gui.dtmMain.addRow(new Object[]{"1", "Producto A", "100"});

        // Seleccionar la fila en la tabla
        gui.tabMain.setRowSelectionInterval(0, 0);

        // Llamar directamente al ActionListener del botón Eliminar
        for (ActionListener listener : gui.btnEliminar.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(gui.btnEliminar, ActionEvent.ACTION_PERFORMED, null));
        }

        // Verificar si se ha eliminado la fila de la tabla
        assertEquals(0, gui.dtmMain.getRowCount());
    }
}
