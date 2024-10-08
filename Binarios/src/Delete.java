import java.io.File;
import java.io.RandomAccessFile;

public class Delete {


public static void Eliminar(int idDelete) {
    String archivoOriginal = "C:\\Users\\darma\\Downloads\\ejemplo_fichero.bin";
    String archivoTemporal = "C:\\Users\\darma\\Downloads\\ejemplo_fichero.bin_temp.bin";
    try (RandomAccessFile file = new RandomAccessFile(archivoOriginal, "rw");
         RandomAccessFile temporal = new RandomAccessFile(archivoTemporal, "rw")) {
        while (file.getFilePointer() < file.length()) {
            int id = file.readInt();
            short edad = file.readShort();
            float salario = file.readFloat();
            if (id != idDelete) {
                temporal.writeInt(id);
                temporal.writeShort(edad);
                temporal.writeFloat(salario);
            }
        }
    } catch (Exception e) {
        System.out.println("Error al eliminar el registro: " + e.getMessage());
    }
    File originalFile = new File(archivoOriginal);
    if (originalFile.delete()) {
        if (!new File(archivoTemporal).renameTo(originalFile)) {
            System.out.println("Error al renombrar el archivo temporal.");
        }
    } else {
        System.out.println("Error al eliminar el archivo original.");
    }
    System.out.println("Registro eliminado con ID: " + idDelete);
    }
}


