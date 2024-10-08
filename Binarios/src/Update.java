import java.io.RandomAccessFile;
import java.util.Scanner;

public class Update {

    public static void Actualizar(int id) {
        try (RandomAccessFile file = new RandomAccessFile("C:\\Users\\darma\\Downloads\\ejemplo_fichero.bin", "rw")) {
            boolean encontrado = false;
            long posicion = 0;
            while (posicion < file.length()) {
                file.seek(posicion);
                int currentId = file.readInt();
                short edad = file.readShort();
                float salario = file.readFloat();
                if (currentId == id) {
                    encontrado = true;
                    System.out.println("Registro que desea actualizar: ");
                    System.out.println("ID: " + currentId);
                    System.out.println("Edad: " + edad);
                    System.out.println("Salario: " + salario);
                    break;
                }
                posicion += Integer.BYTES + Short.BYTES + Float.BYTES;
            }

            if (!encontrado) {
                System.out.println("No se encontró el registro con ID: " + id);
                return;
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("Introducir nuevo id:");
            int nuevoId = sc.nextInt();
            System.out.println("Introducir nueva edad:");
            short nuevaEdad = sc.nextShort();
            System.out.println("Introducir nuevo salario:");
            float nuevoSalario = sc.nextFloat();
            file.seek(posicion);
            file.writeInt(nuevoId);
            file.writeShort(nuevaEdad);
            file.writeFloat(nuevoSalario);
            System.out.println("Registro actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
