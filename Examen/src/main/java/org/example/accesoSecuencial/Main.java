package org.example.accesoSecuencial;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int opcion;
            boolean salir = false;
            while (!salir) {
                System.out.println("Menu");
                System.out.println("1. Añadir");
                System.out.println("2. Modificar");
                System.out.println("3. Eliminar");
                System.out.println("4. Buscar");
                System.out.println("5. Eliminar archivo");
                System.out.println("6. Crear archivo");
                System.out.println("7. Leer todos");
                System.out.println("8. BUscar");
                System.out.println("9. Crear carpetas de un archivo");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el ID");
                        int id = sc.nextInt();
                        System.out.println("Ingrese el nombre");
                        String nombre = sc.next();
                        System.out.println("Ingrese el precio");
                        double precio = sc.nextDouble();
                        System.out.println("Ingrese la categoria");
                        String categoria = sc.next();
                        Escribir.Añadir(id,nombre,precio,categoria);
                        break;
                    case 2:
                        System.out.println("Ingrese el ID");
                        int id2 = sc.nextInt();
                        System.out.println("Ingresar nombre: ");
                        String nombre2 = sc.next();
                        System.out.println("Ingresar precio: ");
                        double precio2 = sc.nextDouble();
                        System.out.println("Ingrese la categoria: ");
                        String categoria2 = sc.next();
                        Escribir.Añadir(id2,nombre2,precio2,categoria2);
                        break;
                        case 3:
                            break;
                            case 4:
                                break;
                                case 5:
                                    Eliminar.eliminarArchivo();
                                    break;
                                    case 6:
                                        Crear.CrearArchivo();
                                        break;
                                            case 7:
                                                Leer.LeerTodos();
                                                break;
                                                    case 8:
                                                        System.out.println("Ingrese el ID");
                                                        int id3 = sc.nextInt();
                                                        Leer.LeerEspecifico(id3);
                                                        break;
                                                        case 9:
                                                            Crear.CrearCarpeta();
                                                            break;

                    default:
                        System.out.println("Opcion no valida");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
