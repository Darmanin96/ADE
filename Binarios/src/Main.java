import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        try {
            while (!salir) {
                int opcion;
                System.out.println("Menu");
                System.out.println("1. Crear  ");
                System.out.println("2. Read  ");
                System.out.println("3. Update  ");
                System.out.println("4. Delete  ");
                System.out.println("5. Salir  ");
                System.out.print("  Opcion: ");
                opcion = sc.nextInt();
                switch (opcion){
                    case 1:
                        System.out.println("Introducir Id: ");
                        int id = sc.nextInt();
                        System.out.println("Introducir edad: ");
                        short edad = sc.nextShort();
                        System.out.println("Introducir salario: ");
                        float salario = sc.nextFloat();
                        Crear.Escribir(id,edad,salario);
                        break;
                    case 2:
                        System.out.println("Introducir Id: ");
                        int idread =sc.nextInt();
                        Read.Leer(idread);
                        break;
                    case 3:
                        System.out.println("Introducir Id que desea editar: ");
                        int idUpdate = sc.nextInt();
                        Update.Actualizar(idUpdate);
                        break;
                    case 4:
                        System.out.println("Introducir Id que desea eliminar: ");
                        int idDelete = sc.nextInt();
                        Delete.Eliminar(idDelete);
                        break;
                    case 5:
                        salir=true;
                        System.out.println("Ha salido del programa");
                        break;
                    default:
                        System.out.println("Opción incorrecta. Por favor eliga una opción ");
                }
            }
        } catch (Exception e) {
        }
    }

}
