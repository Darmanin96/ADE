package TiendaOnline;

import javax.xml.bind.*;

import java.util.*;

public class Main {


    private static final String ARCHIVO = "catalogo.xml";
    private List<Clientes> clientesList = new ArrayList<>();
    private List<Productos> productosList = new ArrayList<>();
    private List<Pedidos> pedidosList = new ArrayList<>();



    public static void main(String[] args) {
        Main main = new Main();
        main.Menu();
    }

    public void Menu(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n Menu");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Agregar Producto");
            System.out.println("3. Realizar Pedidos");
            System.out.println("4. Listar pedidos de un cliente");
            System.out.println("5. Guardar la información");
            System.out.println("6. Cargar la información");
            System.out.println("7. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Clientes nuevoCliente = Clientes.agregarCliente(sc);
                    clientesList.add(nuevoCliente);
                    System.out.println("\n Cliente agregado exitosamente");
                    break;
                case 2:
                    Productos pnuevoProducto = Productos.agregarProducto(sc);
                    productosList.add(pnuevoProducto);
                    System.out.println("\n Producto agregado exitosamente");
                    break;
                case 3:
                    Pedidos.realizarPedido(clientesList,productosList);
                    break;
                case 4:

                    break;
                case 5:

                    break;

                case 6:

                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion != 7);
    }

}
