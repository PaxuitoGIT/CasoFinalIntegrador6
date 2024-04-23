package CasoIntegrador;

import CasoIntegrador.IndexacionyVisualizacion.IndexacionArchivos;
import CasoIntegrador.AnalisisyOrganizacion.OrdenacionYBusqueda;
import CasoIntegrador.DatosDinamicos.GUI;
import CasoIntegrador.AnalisisyOrganizacion.AnalisisRegistros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private IndexacionArchivos indexacionArchivos;
    private OrdenacionYBusqueda ordenacionYBusqueda;
    private GUI datosDinamicos;
    private AnalisisRegistros analisisRegistros;

    public Main() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(4, 1));

        JButton indexacionButton = new JButton("Indexación y Visualización");
        indexacionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirIndexacionArchivos();
            }
        });
        contentPane.add(indexacionButton);

        JButton ordenacionButton = new JButton("Ordenación y Búsqueda");
        ordenacionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirOrdenacionYBusqueda();
            }
        });
        contentPane.add(ordenacionButton);

        JButton datosDinamicosButton = new JButton("Datos Dinámicos");
        datosDinamicosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirDatosDinamicos();
            }
        });
        contentPane.add(datosDinamicosButton);

        JButton analisisRegistrosButton = new JButton("Análisis de Registros");
        analisisRegistrosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirAnalisisRegistros();
            }
        });
        contentPane.add(analisisRegistrosButton);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.setVisible(true);
            }
        });
    }
}
