package CasoIntegrador.IndexacionyVisualizacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IndexacionArchivosTest {

    private IndexacionArchivos indexacionArchivos;

    @BeforeEach
    public void setUp() {
        indexacionArchivos = new IndexacionArchivos();
    }

    @Test
    public void testExplorarDirectorio() throws Exception {
        // Ruta del directorio del proyecto
        String directorioProyecto = System.getProperty("user.dir");

        // Explorar el directorio del proyecto
        indexacionArchivos.explorarDirectorio(Paths.get(directorioProyecto));

        // Obtener el modelo de la tabla y la cantidad de filas
        DefaultTableModel tableModel = (DefaultTableModel) indexacionArchivos.table.getModel();
        int rowCount = tableModel.getRowCount();

        // Verificar que se hayan agregado las filas esperadas (puedes adaptar este valor según tu estructura de directorios)
        assertEquals(7, rowCount); // Por ejemplo, aquí esperamos que se agregue al menos 7 filas debido a los archivos del proyecto
    }
}
