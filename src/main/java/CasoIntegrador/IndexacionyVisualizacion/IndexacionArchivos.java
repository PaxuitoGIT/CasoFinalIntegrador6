package CasoIntegrador.IndexacionyVisualizacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class IndexacionArchivos extends JFrame {
    private DefaultTableModel tableModel;

    public IndexacionArchivos() {
        setTitle("Indexación y Visualización de Archivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());

        // Creamos el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Ruta");

        // Creamos la tabla y le asignamos el modelo
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botón para iniciar la indexación y visualización de archivos
        JButton indexarButton = new JButton("Indexar y Visualizar Archivos");
        indexarButton.addActionListener(e -> indexarYVisualizarArchivos());
        contentPane.add(indexarButton, BorderLayout.NORTH);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

    private void indexarYVisualizarArchivos() {
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

    private void explorarDirectorio(Path directorio) throws IOException {
        Files.walkFileTree(directorio, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path archivo, BasicFileAttributes attrs) throws IOException {
                // Agregar el archivo y su ruta al modelo de la tabla
                tableModel.addRow(new Object[]{archivo.getFileName(), archivo.toAbsolutePath()});
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path archivo, IOException exc) throws IOException {
                // Si hay errores al acceder al archivo, mostrar un mensaje de error
                JOptionPane.showMessageDialog(IndexacionArchivos.this, "Error al acceder al archivo: " + archivo.toString() + "\n" + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private String obtenerDirectorioRaiz() {
        // Obtener la ruta del directorio actual del proyecto
        String rutaProyecto = System.getProperty("user.dir");

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
