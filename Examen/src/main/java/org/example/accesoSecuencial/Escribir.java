package org.example.accesoSecuencial;

import java.io.*;

public class Escribir {
    public static void Añadir(int id, String nombre, double sueldo, String categoria){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("productos.txt", true))) {
            bw.write( id + ",");
            bw.write( nombre + ",");
            bw.write( sueldo + ",");
            bw.write( categoria + ",");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
