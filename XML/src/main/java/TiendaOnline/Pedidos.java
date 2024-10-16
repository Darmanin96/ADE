package TiendaOnline;

import javax.xml.bind.annotation.*;

import java.util.*;

@XmlRootElement
public class Pedidos {
    private Date fecha;
    private List<Productos> pedidos = new ArrayList<>();

    public Pedidos(Date fecha, List<Productos> pedidos) {
        this.fecha = fecha;
        this.pedidos = pedidos;
    }

    public Pedidos() {

    }

    @XmlElement
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlElement
    public List<Productos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Productos> pedidos) {
        this.pedidos = pedidos;
    }



    public static void realizarPedido(List<Clientes> clientesList,List<Productos> productosList) {
            System.out.println("Introducir nombre: ");
            Scanner sc = new Scanner(System.in);
            String nombre = sc.nextLine();
            for (Clientes clientes : clientesList){
                if (clientes.getNombre().equals(nombre)){
                    for (Productos productos : productosList){
                        System.out.println(productos);
                    }
                }
            }
    }

}
