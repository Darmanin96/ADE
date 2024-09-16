import java.io.*;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Empresa {

    public static boolean ValidarEmpresa(String nombre) {
        try {
            FileReader file = new FileReader("C:\\Users\\Dani-PC\\Downloads\\Nulidad.txt");
            BufferedReader bufferedReader = new BufferedReader(file);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] usuario = linea.split("\\s+");
                if (!linea.trim().isEmpty() && usuario.length > 1) {
                    if (usuario[1].equals(nombre)) {
                        bufferedReader.close();
                        return true;
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException | OutOfMemoryError ex) {
            System.out.println("No se ha encontrado el archivo");
        }
        return false;
    }



    public static boolean ValidarEmpresa2(String nombre) {
        if (nombre.length() < 9 || !nombre.substring(0, 7).matches("[a-zA-Z]+") || !nombre.substring(nombre.length() - 3).matches("\\d{3}")) {
            return false;
        }
        return true;
    }

    public static void Correo(String nombre) {
        String archivo = "C:\\Users\\Dani-PC\\Downloads\\Nulidad.txt";
        boolean avisoImpreso = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] usuario = linea.split("\\t+");
                    if (usuario[1].equals(nombre) && !avisoImpreso) {
                        if (buscarEmail(usuario[7])) {
                            System.out.println("Email enviado a : " + usuario[7]);
                        } else {
                            System.out.println("No se encontró un email válido para: " + usuario[1]);
                        }
                        avisoImpreso = true;
                    }
                }
            }

            if (!avisoImpreso) {
                System.out.println("No se encontró ningún email para " + nombre);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean buscarEmail(String linea) {
        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(linea);
        return  matcher.find();
    }

    public static void AñadirEmpresa(int id,String nombre,String pago,String fecha,int idcompra,String nombrenuli,String tel,String correo,String contacto) {
        try {
            File archivo = new File("C:\\Users\\Dani-PC\\Downloads\\Nulidad.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo, true));
            bufferedWriter.write(id + "\t" + nombre + "\t" + pago + "\t" + fecha + "\t" + idcompra + "\t" + nombrenuli + "\t" + tel + "\t" + correo + "\t" + contacto);
            bufferedWriter.close();
            System.out.println("Información agregada correctamente");
        } catch (IOException ex) {
            System.out.println("Error al modificar el archivo: " + ex.getMessage());
        }
    }

    public static boolean validaEmail(String email) {
        // Patrón para validar direcciones de correo electrónico
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // Compilar el patrón en un objeto Pattern
        Pattern pattern = Pattern.compile(emailPattern);
        // Verificar si el correo electrónico coincide con el patrón
        return pattern.matcher(email).matches();
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Set<String> eleccion = new HashSet<>();
            eleccion.add("Correo Electronico");
            eleccion.add("correo electronico");
            eleccion.add("Correo electronico");
            eleccion.add("correo");
            eleccion.add("Correo");
            Set<String> eleccion2 = new HashSet<>();
            eleccion2.add("Fax");
            eleccion2.add("fax");
            int opcion;
            boolean salir = false;
            while (!salir) {
                System.out.println("Menu");
                System.out.println("1. Crear Nulidad de la empresa");
                System.out.println("2. Añadir Empresa");
                System.out.println("3. Salir");
                System.out.println("Elige: ");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Introducir nombre de la empresa: ");
                        String nombre = sc.next();
                        if (!ValidarEmpresa2(nombre)) {
                            System.out.println("El nombre de la empresa no es correcto");
                        } else {
                            System.out.println("El nombre de la empresa es correcto");
                            if (ValidarEmpresa(nombre)) {
                                Carta.imprimirCarta(nombre);
                                System.out.println("Introducir Correo Electrónico o Fax");
                                String correo = sc.next();
                                if (eleccion.contains(correo)){
                                    Correo(nombre);
                                    String rutaDirectorio = "C:\\Users\\Dani-PC\\Desktop\\Carpeta\\";
                                    Carta.EliminarArchivos(rutaDirectorio);
                                    System.out.println("Se ha eliminado el archivo");
                                } else if (eleccion2.contains(correo)) {
                                    System.out.println("Ha elegido el fax");
                                    String rutaDirectorio = "C:\\Users\\Dani-PC\\Desktop\\Carpeta\\";
                                    Carta.EliminarArchivos(rutaDirectorio);
                                    System.out.println("Se ha eliminado el archivo");
                                }else {
                                    System.out.println("Eliga una correcta");
                                }
                            } else {
                                System.out.println("El nombre de la empresa no existe");
                            }
                        }
                        break;
                    case 3:
                        salir = true;
                        System.out.println("Ha salido del programa");
                        break;
                    case 2:
                        System.out.println("Insetar Identidad de la empresa");
                        int id = sc.nextInt();
                        System.out.println("Introducir nombre de la empresa: ");
                        String nombre2 = sc.next();
                        System.out.println("Introducir pago de la empresa: ");
                        String pago2 = sc.next();
                        System.out.println("Fecha de la empresa");
                        String fecha = sc.next();
                        System.out.println("Introducir Id de compra: ");
                        int idcompra = sc.nextInt();
                        System.out.println("Introducir nombre de la nulidad");
                        String nombrenuli = sc.next();
                        System.out.println("Introducir teléfono");
                        String tel = sc.next();
                        String correo;
                        do {
                            System.out.println("Introducir Correo Electronico");
                            correo = sc.next();
                        }while (!validaEmail(correo));
                        System.out.println("Contacto");
                        String contacto = sc.next();
                        AñadirEmpresa(id,nombre2,pago2,fecha,idcompra,nombrenuli,tel,correo,contacto);
                        break;
                    default:
                        System.out.println("Opción incorrecta. Por favor, elige una opción válida.");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Por favor, introduce un número válido.");
        }
    }
}
