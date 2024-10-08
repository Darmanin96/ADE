import java.io.RandomAccessFile;

public class Read {

    public static void Leer(int id) {
        try (RandomAccessFile file = new RandomAccessFile("C:\\Users\\darma\\Downloads\\ejemplo_fichero.bin", "r")) {
            boolean encontrado = false;
            long posicion = 0;
            while (posicion < file.length()) {
                file.seek(posicion);
                int currentId = file.readInt();
                short edad = file.readShort();
                float salario = file.readFloat();
                if (currentId == id) {
                    encontrado = true;
                    System.out.println("ID: " + currentId);
                    System.out.println("Edad: " + edad);
                    System.out.println("Salario: " + salario);
                    break;
                }
                posicion += Integer.BYTES + Short.BYTES + Float.BYTES;
            }
            if (!encontrado) {
                System.out.println("No se encontró el registro con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al leer del archivo: " + e.getMessage());
        }
    }


}
