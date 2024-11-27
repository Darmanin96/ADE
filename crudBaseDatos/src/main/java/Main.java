import sql.*;

import java.util.*;

    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("Menu");
                System.out.println("1. Crear empleado");
                System.out.println("2. Listar todos los empleados ");
                System.out.println("3. Buscar empleado");
                System.out.println("4. Eliminar empleado");
                System.out.println("5. Modificar empleado");
                System.out.println("6. Salir");
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Introducir id: ");
                        int id = sc.nextInt();
                        System.out.println("Introducir nombre: ");
                        String nombre = sc.next();
                        System.out.println("Introducir apellido: ");
                        String apellido = sc.next();
                        System.out.println("Introducir email: ");
                        String email = sc.next();
                        System.out.println("Introducir salario: ");
                        double salario = sc.nextDouble();
                        System.out.println("Introducir fecha de contratación: ");
                        Date fecha = new Date();
                        SqlSentences.create(id,nombre,apellido,email,salario,fecha);
                        break;
                    case 2:

                        break;
                    case 3:
                        System.out.println("Introducir nombre del empleado que desea buscar: ");
                        String nombre2 = sc.next();

                        break;
                    case 4:
                        System.out.println("Introducir nombre del empleado que desea eliminar: ");
                        String nombre3 = sc.next();

                        break;
                    case 5:
                        System.out.println("Introducir nombre del empleado que desea modificar");
                        String nombre4 = sc.next();

                        break;
                    case 6:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            }while (opcion != 6);
        }
    }

