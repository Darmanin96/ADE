package org.example;

import java.io.*;

public class ContarPalabras {
    public static void main(String[] args) {
        String archivo = "entrada.txt";
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea;
            int contador = 0;
            while ((linea = entrada.readLine()) != null) {
                for (int i = 0; i < linea.length(); i++) {
                    contador++;
                }
            }
            System.out.println("El archivo tiene " + contador + " palabras");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
