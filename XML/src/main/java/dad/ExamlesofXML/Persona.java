package dad.ExamlesofXML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona {
    private String nombre;
    private int edad;
    private String correo;

    @XmlElement
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @XmlElement
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    @XmlElement
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Persona() {} // Constructor vacío requerido por JAXB

    public Persona(String nombre, int edad, String correo) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
    }
}

