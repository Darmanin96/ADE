package dad.CrudBinarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearGUI extends JFrame {
    private JTextField idField;
    private JTextField edadField;
    private JTextField salarioField;
    private JButton guardarButton;

    public CrearGUI() {
        setTitle("Crear Registro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Crear los campos de texto
        idField = new JTextField(10);
        edadField = new JTextField(5);
        salarioField = new JTextField(10);

        // Crear el botón de guardar
        guardarButton = new JButton("Guardar");

        // Panel para los campos de texto y etiquetas
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID (int):"));
        panel.add(idField);
        panel.add(new JLabel("Edad (short):"));
        panel.add(edadField);
        panel.add(new JLabel("Salario (float):"));
        panel.add(salarioField);
        panel.add(guardarButton);

        // Agregar el panel al frame
        add(panel, BorderLayout.CENTER);

        // Acción del botón Guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRegistro();
            }
        });
    }

    // Método para guardar el registro en un archivo binario
    private void guardarRegistro() {
        try {
            int id = Integer.parseInt(idField.getText());
            short edad = Short.parseShort(edadField.getText());
            float salario = Float.parseFloat(salarioField.getText());

            try (RandomAccessFile archivo = new RandomAccessFile("ejemplo_fichero.bin", "rw")) {
                archivo.seek(archivo.length()); // Mover al final del archivo
                archivo.writeInt(id);
                archivo.writeShort(edad);
                archivo.writeFloat(salario);
                JOptionPane.showMessageDialog(this, "Registro guardado exitosamente.");
                limpiarCampos();
            }
        } catch (NumberFormatException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el registro: " + ex.getMessage());
        }
    }

    // Limpiar los campos de texto
    private void limpiarCampos() {
        idField.setText("");
        edadField.setText("");
        salarioField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrearGUI().setVisible(true));
    }
}
