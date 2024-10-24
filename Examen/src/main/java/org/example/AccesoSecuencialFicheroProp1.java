package org.example;

import java.io.*;

public class AccesoSecuencialFicheroProp1 {
    public static void main(String[] args) {
        String archivo = "productos.txt";

        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = entrada.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split(",");

                    // Crear directorio si no existe
                    File directorio = new File(datos[3]);
                    if (!directorio.exists()) {
                        directorio.mkdir();
                    }

                    // Definir la ruta del archivo por categoría
                    String ruta = datos[3] + "/" + datos[3] + ".txt";

                    // Abrir el archivo en modo "append" para agregar más productos
                    try (FileWriter escribir = new FileWriter(ruta, true)) {
                        escribir.write(datos[1] + ", " + datos[2] + "\n");
                    } catch (IOException e) {
                        System.err.println("Error al escribir en el archivo: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo no se encontró: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
