package org.example.accesoSecuencial;

import java.io.*;
import java.util.*;

public class Crear {

    public static void CrearArchivo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo: ");
        String nombre = sc.next();
        System.out.println("Ingresar nombre: ");
        String nombre2 = sc.next();
        System.out.println("Ingresar apellidos: ");
        String apellidos = sc.next();
        System.out.println("Ingresar edad: ");
        int edad = sc.nextInt();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nombre + ".txt"))) {
            bw.write("Nombre: " + nombre2);
            bw.newLine();
            bw.write("Apellido: " + apellidos );
            bw.newLine();
            bw.write("Edad: " + edad);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


        public static void CrearCarpeta() {
            try (BufferedReader reader = new BufferedReader(new FileReader("productos.txt"))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    // Verificamos que la línea no esté vacía
                    if (!linea.trim().isEmpty()) {
                        // Dividimos la línea en partes (id, nombre, precio, categoría)
                        String[] datos = linea.split(",");
                        String categoria = datos[3];  // La categoría
                        String nombreProducto = datos[1];  // El nombre del producto
                        String precioProducto = datos[2];  // El precio del producto

                        // Creamos la carpeta si no existe
                        File directorio = new File(categoria);
                        if (!directorio.exists()) {
                            directorio.mkdir();
                        }

                        // Especificamos la ruta del archivo dentro de la carpeta
                        String rutaArchivo = categoria + "/" + categoria + ".txt";

                        // Escribimos el nombre del producto y su precio en el archivo de la categoría
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
                            bw.write(nombreProducto + ", " + precioProducto);
                            bw.newLine();  // Añadir un salto de línea después de cada producto
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


