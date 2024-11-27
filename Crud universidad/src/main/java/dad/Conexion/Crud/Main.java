package dad.Conexion.Crud;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Seleccionar que base de datos desea trabajar");
            System.out.println("1. Cursos");
            System.out.println("2. Estudiantes");
            System.out.println("3. Inscripcciones");
            System.out.println("4. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:

                    break;
                    case 2:

                        break;
                        case 3:

                            break;
                            default:
                                System.out.println("Opcion no valida");
            }
        }while (opcion != 4);
    }
}
