package org.example.accesoAleatorio;

import java.io.*;
import java.util.*;

public class CrearArchivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        leerTodos();
        System.out.println("Ingrese el id: ");
        int id = sc.nextInt();
        System.out.println("Ingrese la edad: ");
        short edad = sc.nextShort();
        System.out.println("Ingrese el salario: ");
        float salario = sc.nextFloat();
        Añadir(id, edad, salario);
        System.out.println("Introducir id: ");
        int id2 = sc.nextInt();
        Buscar(id2);
        System.out.println("Introducir id para modificar: ");
        int id3 = sc.nextInt();
        Modificar(id3);
        System.out.println("Introducir id para eliminar: ");
        int id4 = sc.nextInt();
        eliminar(id4);
        System.out.println("Introducir posicion: ");
        int posicion = sc.nextInt();
        AñadirPosicion(posicion);
        System.out.println("Introducir edad para cambiarlo todo: ");
        short edad2 = sc.nextShort();
        // actualizarTodasLasEdades(edad2);
        //CrearCarpeta();
    }

    public static void actualizarTodasLasEdades(short nuevaEdad) {
        try (RandomAccessFile file = new RandomAccessFile("primero.bin", "rw")) {
            long posicion = 0;
            while (posicion < file.length()) {
                file.seek(posicion);
                file.readInt();  // Leer ID
                file.readShort();  // Leer Edad (que se va a actualizar)
                float salario = file.readFloat();  // Leer Salario
                file.seek(posicion + Integer.BYTES);  // Mover a la posición de la edad
                file.writeShort(nuevaEdad);  // Escribir nueva edad
                posicion += Integer.BYTES + Short.BYTES + Float.BYTES;  // Avanzar a la siguiente entrada
            }

            System.out.println("Se han actualizado todas las edades a: " + nuevaEdad);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Añadir(int id, short edad, float salario) {
        try (RandomAccessFile file = new RandomAccessFile("primero.bin", "rw")) {
            file.seek(file.length());
            file.writeInt(id);
            file.writeShort(edad);
            file.writeFloat(salario);
            System.out.println("Nuevo registro agregado: " + id + " " + edad + " " + salario);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void AñadirPosicion(int posicion) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introducir id: ");
        int id2 = sc.nextInt();
        System.out.println("Introducir edad: ");
        short edad2 = sc.nextShort();
        System.out.println("Introducir salario: ");
        float salario2 = sc.nextFloat();
        try (RandomAccessFile file = new RandomAccessFile("primero.bin", "rw")) {
            file.seek(posicion);
            file.writeInt(id2);
            file.writeShort(edad2);
            file.writeFloat(salario2);
            System.out.println("Nuevo registro agregado: " + id2 + " " + edad2 + " " + salario2 + " en " + posicion);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Buscar(int id) {
        try (RandomAccessFile file = new RandomAccessFile("primero.bin", "r")) {
            boolean encontrado = false;
            long posicion = 0;
            while (posicion < file.length()) {
                file.seek(posicion);
                int currentId = file.readInt();
                short currentEdad = file.readShort();
                float currentSalario = file.readFloat();
                if (currentId == id) {
                    encontrado = true;
                    System.out.println("ID: " + currentId);
                    System.out.println("Edad: " + currentEdad);
                    System.out.println("Salario: " + currentSalario);
                    break;
                }
                posicion += Integer.BYTES + Short.BYTES + Float.BYTES;
            }
            if (!encontrado) {
                System.out.println("No se encontró el registro: " + id);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Modificar(int id) {
        try (RandomAccessFile file = new RandomAccessFile("primero.bin", "rw")) {
            boolean encontrado = false;
            long posicion = 0;
            while (posicion < file.length()) {
                file.seek(posicion);
                int currentId = file.readInt();
                short currentEdad = file.readShort();
                float currentSalario = file.readFloat();
                if (currentId == id) {
                    encontrado = true;
                    System.out.println("ID: " + currentId);
                    System.out.println("Edad: " + currentEdad);
                    System.out.println("Salario: " + currentSalario);
                    break;
                }
                posicion += Integer.BYTES + Short.BYTES + Float.BYTES;
            }
            if (!encontrado) {
                System.out.println("No se encontró el registro: " + id);
                return;
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Introducir nuevo id: ");
            int id2 = sc.nextInt();
            System.out.println("Introducir nueva edad: ");
            short edad2 = sc.nextShort();
            System.out.println("Introducir nuevo salario: ");
            float salario2 = sc.nextFloat();
            file.seek(posicion);
            file.writeInt(id2);
            file.writeShort(edad2);
            file.writeFloat(salario2);
            System.out.println("Registro modificado: " + id + " a " + id2 + " " + edad2 + " " + salario2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminar(int id) {
        String archivoOriginal = "primero.bin";
        String archivoTemporal = "temporal.bin";
        try (RandomAccessFile file = new RandomAccessFile(archivoOriginal, "rw");
             RandomAccessFile file2 = new RandomAccessFile(archivoTemporal, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int id3 = file.readInt();
                short edad2 = file.readShort();
                float salario2 = file.readFloat();
                if (id3 != id) {
                    file2.writeInt(id3);
                    file2.writeShort(edad2);
                    file2.writeFloat(salario2);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File original = new File(archivoOriginal);
        if (original.delete()) {
            if (!new File(archivoTemporal).renameTo(original)) {
                System.out.println("Error al renombrar el archivo");
            }
        } else {
            System.out.println("Error al eliminar el archivo original");
        }
        System.out.println("Registro eliminado: " + id);
    }

    public static void leerTodos() {
        try (RandomAccessFile file = new RandomAccessFile("primero.bin", "r")) {
            long posicion = 0;
            long longitudArchivo = file.length();
            while (posicion < longitudArchivo) {
                file.seek(posicion);
                int id = file.readInt();
                short edad = file.readShort();
                float salario = file.readFloat();
                System.out.println("ID: " + id);
                System.out.println("Edad: " + edad);
                System.out.println("Salario: " + salario);
                posicion += Integer.BYTES + Short.BYTES + Float.BYTES;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CrearCarpeta() {
        try (RandomAccessFile file = new RandomAccessFile("primero.bin", "r")) {
            long posicion = 0;
            long longitudArchivo = file.length();
            // Mapa para agrupar productos por categoría
            Map<String, List<String>> productosPorCategoria = new HashMap<>();
            while (posicion < longitudArchivo) {
                file.seek(posicion);
                int id = file.readInt();
                short edad = file.readShort();
                float salario = file.readFloat();
                // Aquí se podría agregar lógica para asignar categoría (ejemplo, por edad)
                String categoria = (edad < 30) ? "Joven" : (edad < 50) ? "Adulto" : "Mayor";
                String productoInfo = "ID: " + id + ", Edad: " + edad + ", Salario: " + salario;

                // Agrupando por categoría
                productosPorCategoria.putIfAbsent(categoria, new ArrayList<>());
                productosPorCategoria.get(categoria).add(productoInfo);

                posicion += Integer.BYTES + Short.BYTES + Float.BYTES;
            }
            // Crear carpetas y archivos para cada categoría
            for (Map.Entry<String, List<String>> entry : productosPorCategoria.entrySet()) {
                String categoria = entry.getKey();
                List<String> productos = entry.getValue();
                File carpeta = new File(categoria);
                if (!carpeta.exists()) {
                    carpeta.mkdir(); // Crear la carpeta si no existe
                }
                try (PrintWriter writer = new PrintWriter(new FileWriter(new File(carpeta, "productos.txt"), true))) {
                    for (String producto : productos) {
                        writer.println(producto); // Escribir información de productos en el archivo
                    }
                }
            }
            System.out.println("Carpetas y archivos creados con éxito.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
