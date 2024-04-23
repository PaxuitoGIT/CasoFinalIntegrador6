package CasoIntegrador.AnalisisyOrganizacion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class AnalisisRegistrosTest {

    private AnalisisRegistros analisisRegistros;

    @BeforeEach
    public void setUp() {
        analisisRegistros = new AnalisisRegistros();
    }

    @Test
    public void testAgregarTransaccion() {
        // Verificar que la tabla tenga los datos iniciales que son 7
        assertEquals(7, analisisRegistros.tableModel.getRowCount());

        // Agregar una nueva transacción
        String producto = "ProductoTest";
        int cantidad = 100;
        String cliente = "ClienteTest";
        String fecha = "01/01/2024";
        analisisRegistros.agregarTransaccion(producto, cantidad, cliente, fecha);

        // Verificar que se haya agregado la transacción correctamente de 7 a 8 con el dato anterior
        assertEquals(8, analisisRegistros.tableModel.getRowCount());
        assertEquals("ProductoTest", analisisRegistros.tableModel.getValueAt(7, 0));
        assertEquals(100, analisisRegistros.tableModel.getValueAt(7, 1));
        assertEquals("ClienteTest", analisisRegistros.tableModel.getValueAt(7, 2));
        assertEquals("01/01/2024", analisisRegistros.tableModel.getValueAt(7, 3));
    }
}
