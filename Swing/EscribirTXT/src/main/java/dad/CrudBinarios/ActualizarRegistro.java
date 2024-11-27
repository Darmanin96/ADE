package dad.CrudBinarios;

import javax.swing.*;

public class ActualizarRegistro extends JFrame {
    private JTextArea textArea;



    public ActualizarRegistro() {
        setTitle("Actualizar Registro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    private void initComponents() {
        textArea = new JTextArea();
        textArea.setEditable(false);

    }
}
