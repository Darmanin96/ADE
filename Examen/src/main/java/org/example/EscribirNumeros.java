package org.example;

import java.io.*;

public class EscribirNumeros {
    public static void main(String[] args) {
        String archivo = "entrada.txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo,true));
            for (int i = 0; i <= 10; i++) {
                System.out.println(i);
                bw.write(String.valueOf(i));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
