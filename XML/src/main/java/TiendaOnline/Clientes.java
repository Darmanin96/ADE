package TiendaOnline;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class Clientes {
    private String nombre;
    private String direccion;
    private List<Pedidos> listapedidos = new ArrayList<>();

    public Clientes(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Clientes() {
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlElement
    public List<Pedidos> getPedidos() {
        return listapedidos;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        this.listapedidos = pedidos;
    }

    public static Clientes agregarCliente(Scanner sc) {
        System.out.println("Introducir nombre del cliente:");
        String nombre = sc.nextLine();
        System.out.println("Introducir direccion del cliente:");
        String direccion = sc.nextLine();
        return new Clientes(nombre, direccion);
    }



}
