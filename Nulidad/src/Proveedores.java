import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Proveedores {


    public static double Pagos(String nombre) {
        double total = 0; // Inicializar el total de pagos
        try {
            ArrayList<Double> pagos = new ArrayList<>();
            FileReader file = new FileReader("C:\\Users\\Dani-PC\\Downloads\\Nulidad.txt");
            BufferedReader bufferedReader = new BufferedReader(file);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] usuario = linea.split("\\t+");
                if (!linea.trim().isEmpty()) {
                    if (usuario[1].equals(nombre)) {
                            String tercero = usuario[2].replaceAll("[^\\d,]", "");
                            tercero = tercero.replace(',', '.');
                            try {
                                double valor = Double.parseDouble(tercero);
                                pagos.add(valor);
                                total += valor; // Agregar el valor al total de pagos
                            } catch (NumberFormatException ex) {
                                System.out.println("Error al convertir en double: " + tercero);
                        }
                    }
                }
            }
            bufferedReader.close();
            // No necesitas llamar a Sumar aquí, ya que ya estás calculando el total de pagos
        } catch (IOException | OutOfMemoryError ex) {
            System.out.println("No se ha encontrado el archivo");
        }
        return total; // Devolver el total de pagos calculado
    }

    public static String PagosMostrar(String nombre) {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader file = new FileReader("C:\\Users\\Dani-PC\\Downloads\\Nulidad.txt");
            BufferedReader bufferedReader = new BufferedReader(file);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] usuario = linea.split("\\t+");
                if (!linea.trim().isEmpty()) {
                    if (usuario[1].equals(nombre)) {
                            String tercero = usuario[2].replaceAll("[^\\d,]", "");
                            tercero = tercero.replace(',', '.');
                            sb.append(tercero).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString(); // Devolver el contenido del StringBuilder como una cadena
    }


    public static void Sumar(ArrayList<String> lineas, String nombre, ArrayList<Double> pagos) {
        for (String linea : lineas) {
            String[] usuario = linea.split("\\t+");
            if (usuario[1].equals(nombre)) {
                    String tercercampo = usuario[2].replaceAll("[^\\d,]", "");
                    // Reemplazar la coma por un punto
                    tercercampo = tercercampo.replace(',', '.');
                    try {
                        double valor = Double.parseDouble(tercercampo);
                        pagos.add(valor);
                        Mostrar(tercercampo); // Llamar al método Mostrar con el valor de tercercampo
                        double total = CalcularTotal(pagos); // Calcular el total de los pagos
                        System.out.println("El total de los pagos para " + nombre + " es: " + total);
                    } catch (NumberFormatException ex) {
                        System.out.println("Error al convertir en double: " + tercercampo);
                    }
                }
            }
        }


    public static void Mostrar(String tercercampo) {
        System.out.println(tercercampo); // Imprimir el valor del tercer campo
    }

    public static double CalcularTotal(ArrayList<Double> pagos) {
        double total = 0;
        for (double valor : pagos) {
            total += valor;
        }
        return total;
    }
}



