package org.example.accesoSecuencial;

import java.io.*;
import java.util.*;

public class Eliminar {

    public static void eliminarArchivo(){
        Scanner sc = new Scanner(System.in);
        try {
            File archivo = new File("archivo.txt");
            if (!archivo.exists()) {
                System.out.println("Archivo no encontrado");
                return;
            }else {
                String opcion;
                do {
                    System.out.println("Esta seguro");
                    opcion = sc.next();
                }while (!opcion.equals("Si"));
                archivo.delete();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
