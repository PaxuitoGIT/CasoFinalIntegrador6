package CasoIntegrador.MapasyAsociacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GestionRelacionesTest {

    private GestionRelaciones gestionRelaciones;

    @BeforeEach
    public void setUp() {
        gestionRelaciones = new GestionRelaciones();
        gestionRelaciones.setVisible(true);
    }

    @Test
    public void testAgregarRelacion() {
        Container container = gestionRelaciones.getContentPane();

        // Verificar si el contenedor contiene algún JScrollPane en su estructura
        boolean jScrollPaneFound = false;
        for (Component component : container.getComponents()) {
            if (component instanceof JScrollPane) {
                jScrollPaneFound = true;
                break;
            }
        }
        assertTrue(jScrollPaneFound);

        // Verificar si el contenedor contiene algún JButton en su estructura
        boolean jButtonFound = false;
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                jButtonFound = true;
                break;
            }
        }
        assertTrue(jButtonFound);
    }
}
