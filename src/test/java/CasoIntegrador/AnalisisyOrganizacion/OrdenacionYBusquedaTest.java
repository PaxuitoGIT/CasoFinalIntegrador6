package CasoIntegrador.AnalisisyOrganizacion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class OrdenacionYBusquedaTest {

    private OrdenacionYBusqueda ordenacionYBusqueda;

    @BeforeEach
    public void setUp() {
        ordenacionYBusqueda = new OrdenacionYBusqueda();
    }

    @Test
    public void testAgregarNombre() {
        int rowCountBefore = ordenacionYBusqueda.getNombresTableModel().getRowCount();
        ordenacionYBusqueda.agregarNombre("NuevoNombre");
        int rowCountAfter = ordenacionYBusqueda.getNombresTableModel().getRowCount();
        assertEquals(rowCountBefore + 1, rowCountAfter);
    }

    @Test
    public void testAgregarVenta() {
        int rowCountBefore = ordenacionYBusqueda.ventasTableModel.getRowCount();
        ordenacionYBusqueda.agregarVenta("NuevoProducto", 100);
        int rowCountAfter = ordenacionYBusqueda.ventasTableModel.getRowCount();
        assertEquals(rowCountBefore + 1, rowCountAfter);
    }

    @Test
    public void testCambiarOrdenNombres() {
        ordenacionYBusqueda.cambiarOrdenNombres();
        // Verificar el estado esperado después de cambiar el orden
    }

    @Test
    public void testCambiarOrdenVentas() {
        ordenacionYBusqueda.cambiarOrdenVentas();
        // Verificar el estado esperado después de cambiar el orden
    }
}
