package dad.CrudBinarios;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LeerGUIJtexArea extends JFrame {
    private JTextArea textArea;

    public LeerGUIJtexArea() {
        setTitle("Leer Registros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarRegistros();
    }

    private void initComponents() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void cargarRegistros() {
        StringBuilder registros = new StringBuilder();

        try (RandomAccessFile archivo = new RandomAccessFile("ejemplo_fichero.bin", "r")) {
            while (archivo.getFilePointer() < archivo.length()) {
                int id = archivo.readInt();
                short edad = archivo.readShort();
                float salario = archivo.readFloat();

                registros.append("ID: ").append(id)
                        .append(", Edad: ").append(edad)
                        .append(", Salario: ").append(salario).append("\n");
            }
            textArea.setText(registros.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer los registros: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LeerGUIJtexArea().setVisible(true));
    }
}
