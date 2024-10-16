package TiendaOnline;

import javax.xml.bind.annotation.*;

import java.util.*;

@XmlRootElement
public class Productos {
    private String nombre;
    private int precio;
    private String categoria;


    public Productos(String nombre, String categoria, int precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }


    public Productos() {

    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @XmlElement
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }


    public static Productos agregarProducto(Scanner sc) {
        System.out.println("Introducir nombre del producto:");
        String nombre = sc.nextLine();
        System.out.println("Introducir precio del producto:");
        int precio = sc.nextInt();
        System.out.println("Introducir categoria del producto:");
        String categoria = sc.nextLine();
        return new Productos(nombre,categoria,precio);

    }


}
