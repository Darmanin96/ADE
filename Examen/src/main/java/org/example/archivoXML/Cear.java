package org.example.archivoXML;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cear {
    private String nombre;
    private int numeroTelefono;
    private String correo;


    public Cear() {}

    public Cear(String nombre, String correo, int numeroTelefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.numeroTelefono = numeroTelefono;
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @XmlElement
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cear{" +
                "nombre='" + nombre + '\'' +
                ", numeroTelefono=" + numeroTelefono +
                ", correo='" + correo + '\'' +
                '}';
    }
}
