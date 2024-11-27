package dad.CrudBinarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JButton botonCrear;
    private JButton botonLeer;
    private JButton botonEliminar;
    private JButton botonActualizar;

    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Configurar el layout
        setLayout(new GridLayout(3, 1)); // 3 filas para 3 botones

        // Crear botones
        botonCrear = new JButton("Crear Registro");
        botonLeer = new JButton("Leer Registros");
        botonEliminar = new JButton("Eliminar Registro");
        botonActualizar = new JButton("Actualizar Registro");

        // Agregar acción para el botón de Crear
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de Crear
                new CrearGUI().setVisible(true); // Suponiendo que CrearGUI está implementado
            }
        });

        // Agregar acción para el botón de Leer
        botonLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de Leer
                new LeerGUIJtexArea().setVisible(true); // Suponiendo que LeerGUIJtexArea está implementado
            }
        });

        // Agregar acción para el botón de Eliminar
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama a la ventana de EliminarRegistro
                new EliminarRegistro().setVisible(true);
            }
        });

        botonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Agregar botones al frame
        add(botonCrear);
        add(botonLeer);
        add(botonEliminar);
        add(botonActualizar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
}
