package CasoIntegrador;

import CasoIntegrador.IndexacionyVisualizacion.IndexacionArchivos;
import CasoIntegrador.AnalisisyOrganizacion.OrdenacionYBusqueda;
import CasoIntegrador.DatosDinamicos.GUI;
import CasoIntegrador.AnalisisyOrganizacion.AnalisisRegistros;
import CasoIntegrador.MapasyAsociacion.GestionRelaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    // Variables de instancia para los componentes de la interfaz
    private IndexacionArchivos indexacionArchivos;
    private OrdenacionYBusqueda ordenacionYBusqueda;
    private GUI datosDinamicos;
    private AnalisisRegistros analisisRegistros;
    private GestionRelaciones gestionRelaciones;

    JPanel contentPane;
    JButton indexacionButton, ordenacionButton, datosDinamicosButton, analisisRegistrosButton, gestionRelacionesButton;

    // Constructor de la clase
    public Main() {
        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(5, 1));

        // Botones para abrir las diferentes opciones del menú
        indexacionButton = new JButton("Indexación y Visualización");
        indexacionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirIndexacionArchivos();
            }
        });
        contentPane.add(indexacionButton);

        ordenacionButton = new JButton("Ordenación y Búsqueda");
        ordenacionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirOrdenacionYBusqueda();
            }
        });
        contentPane.add(ordenacionButton);

        datosDinamicosButton = new JButton("Datos Dinámicos");
        datosDinamicosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDatosDinamicos();
            }
        });
        contentPane.add(datosDinamicosButton);

        analisisRegistrosButton = new JButton("Análisis de Registros");
        analisisRegistrosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirAnalisisRegistros();
            }
        });
        contentPane.add(analisisRegistrosButton);

        gestionRelacionesButton = new JButton("Gestión de Relaciones");
        gestionRelacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirGestionRelaciones();
            }
        });
        contentPane.add(gestionRelacionesButton);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

    // Métodos para abrir las diferentes opciones del menú
    private void abrirIndexacionArchivos() {
        if (indexacionArchivos == null) {
            indexacionArchivos = new IndexacionArchivos();
            indexacionArchivos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        indexacionArchivos.setVisible(true);
    }

    private void abrirOrdenacionYBusqueda() {
        if (ordenacionYBusqueda == null) {
            ordenacionYBusqueda = new OrdenacionYBusqueda();
            ordenacionYBusqueda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        ordenacionYBusqueda.setVisible(true);
    }

    private void abrirDatosDinamicos() {
        if (datosDinamicos == null) {
            datosDinamicos = new GUI();
            datosDinamicos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        datosDinamicos.setVisible(true);
    }

    private void abrirAnalisisRegistros() {
        if (analisisRegistros == null) {
            analisisRegistros = new AnalisisRegistros();
            analisisRegistros.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        analisisRegistros.setVisible(true);
    }

    private void abrirGestionRelaciones() {
        if (gestionRelaciones == null) {
            gestionRelaciones = new GestionRelaciones();
            gestionRelaciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        gestionRelaciones.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.setVisible(true); // Mostrar la ventana principal del menú
            }
        });
    }
}
