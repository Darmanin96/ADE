package org.example;

import java.io.*;

public class CopiarArchivos {
    public static void main(String[] args) {
        String archivo = "entrada.txt";
        String archivo2 = "destino.txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
            String linea;
            String[] copia = bufferedReader.readLine().split(" ");
            while ((linea = bufferedReader.readLine()) != null) {
            System.out.println(linea);
            copia = linea.split(" ");
            }
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo2));
                bufferedWriter.write(String.join(" ", copia));
            }catch (IOException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
