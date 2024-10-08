import java.io.RandomAccessFile;

public class Crear {

    public static void Escribir(int id, short edad, float salario){
        try(RandomAccessFile file = new RandomAccessFile("C:\\Users\\darma\\Downloads\\ejemplo_fichero.bin", "rw")) {
            file.seek(file.length());
            file.writeInt(id);
            file.writeShort(edad);
            file.writeFloat(salario);
            System.out.println("Nuevo registro agregado correctamente: ID: " + id + ", Edad: " + edad + ", Salario: " + salario);
        } catch (Exception e) {
        }
    }

}