package org.example.archivoXML;

import javax.xml.bind.*;

import java.io.*;

public class Main {
    private static final String archivo = "archivo.xml";

    public static void main(String[] args) {

        guardarArchivo();

    }


    private static void guardarArchivo() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Cear.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            //marshaller.marshal(Cear, new File(archivo));
            System.out.println("Archivo guardado exitosamente");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
