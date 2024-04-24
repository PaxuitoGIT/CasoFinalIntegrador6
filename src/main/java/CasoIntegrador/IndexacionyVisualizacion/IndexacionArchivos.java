package CasoIntegrador.IndexacionyVisualizacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;

public class IndexacionArchivos extends JFrame {
    // Variables de instancia para los componentes de la interfaz
    DefaultTableModel tableModel;
    JPanel contentPane;
    JTable table;
    JScrollPane scrollPane;
    JButton indexarButton;

    // Constructor de la clase
    public IndexacionArchivos() {
        // Configuración de la ventana principal
        setTitle("Indexación y Visualización de Archivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        // Creación del panel principal y configuración de los bordes
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());

        // Creamos el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Ruta");

        // Creamos la tabla y le asignamos el modelo
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botón para iniciar la indexación y visualización de archivos
        indexarButton = new JButton("Indexar y Visualizar Archivos");
        indexarButton.addActionListener(e -> indexarYVisualizarArchivos());
        contentPane.add(indexarButton, BorderLayout.NORTH);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

    public void indexarYVisualizarArchivos() {
        // Limpiamos el modelo de la tabla
        tableModel.setRowCount(0);

        // Directorio raíz para indexar
        String directorioRaiz = obtenerDirectorioRaiz();

        if (directorioRaiz != null) {
            try {
                // Explorar el directorio raíz de forma recursiva y agregar archivos al modelo de la tabla
                explorarDirectorio(Paths.get(directorioRaiz));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al acceder al directorio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void explorarDirectorio(Path directorio) throws IOException {
        Files.list(directorio)
                .sorted() // Ordenar alfabéticamente
                .forEach(archivo -> {
                    try {
                        // Agregar el archivo y su ruta al modelo de la tabla
                        tableModel.addRow(new Object[]{archivo.getFileName(), archivo.toAbsolutePath()});
                    } catch (Exception e) {
                        // Manejar cualquier excepción que ocurra al agregar el archivo
                        e.printStackTrace();
                    }
                });
    }

    public String obtenerDirectorioRaiz() {
        // Obtener la ruta del directorio actual del proyecto
        String rutaProyecto = System.getProperty("user.dir"); // Directorio actual del proyecto

        // Mostrar un cuadro de diálogo para que el usuario seleccione el directorio raíz
        JFileChooser fileChooser = new JFileChooser(rutaProyecto);
        fileChooser.setDialogTitle("Seleccionar Directorio Raíz");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showDialog(this, "Seleccionar");
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IndexacionArchivos().setVisible(true));
    }
}
