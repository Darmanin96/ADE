package org;

import org.SQL.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n Menu");
            System.out.println("Seleccione la base de datos");
            System.out.println("1. Alumno");
            System.out.println("2. Empresas");
            System.out.println("3. Tutor grupo");
            System.out.println("4. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Alumno.Menu();
                    break;
                    case 2:
                        Empresa.Menu();
                        break;
                        case 3:
                            tutorGrupo.Menu();
                            break;
                            case 4:
                                System.out.println("Saliendo...");
                                break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion != 4);
    }
}
