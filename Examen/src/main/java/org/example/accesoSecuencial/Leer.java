package org.example.accesoSecuencial;

import java.io.*;

public class Leer {
    public static void LeerTodos(){
        try(BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void LeerEspecifico(int id){
        try(BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            String linea;
            while ((linea = br.readLine()) !=null){
                String[] datos = linea.split(",");
                if(datos[0].equals(String.valueOf(id))){
                    System.out.println("ID: "  + datos[0]);
                    System.out.println("Nombre: " + datos[1]);
                    System.out.println("Precio: " + datos[2]);
                    System.out.println("Categoria: " + datos[3]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
