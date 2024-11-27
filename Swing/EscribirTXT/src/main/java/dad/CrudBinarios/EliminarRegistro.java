package dad.CrudBinarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EliminarRegistro extends JFrame {
    private JTextField idField;
    private JButton eliminarButton;

    public EliminarRegistro() {
        setTitle("Eliminar Registro");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Crear componentes
        idField = new JTextField(10);
        eliminarButton = new JButton("Eliminar");

        // Configurar el panel
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Ingrese el ID a eliminar:"));
        panel.add(idField);

        // Agregar botón de eliminar al panel
        panel.add(eliminarButton);

        // Agregar el panel al JFrame
        add(panel, BorderLayout.CENTER);

        // Acción del botón Eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idDelete = Integer.parseInt(idField.getText());
                    eliminarRegistro(idDelete);  // Llamar al método de eliminación
                    JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                }
            }
        });
    }

    // Método para eliminar el registro con el ID dado
    public void eliminarRegistro(int idDelete) {
        String archivoOriginal = "ejemplo_fichero.bin";
        String archivoTemporal = "temporal.bin";

        try (RandomAccessFile file = new RandomAccessFile(archivoOriginal, "rw");
             RandomAccessFile temporal = new RandomAccessFile(archivoTemporal, "rw")) {

            while (file.getFilePointer() < file.length()) {
                int id = file.readInt();
                short edad = file.readShort();
                float salario = file.readFloat();
                if (id != idDelete) {
                    temporal.writeInt(id);
                    temporal.writeShort(edad);
                    temporal.writeFloat(salario);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro: " + e.getMessage());
        }

        // Renombrar el archivo temporal al archivo original
        File original = new File(archivoOriginal);
        File tempFile = new File(archivoTemporal);

        if (original.delete()) {
            if (!tempFile.renameTo(original)) {
                JOptionPane.showMessageDialog(null, "Error al renombrar el archivo temporal.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el archivo original.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EliminarRegistro().setVisible(true));
    }
}
