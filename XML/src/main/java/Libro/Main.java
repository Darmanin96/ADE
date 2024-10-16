package Libro;

import javax.xml.bind.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String ARCHIVO = "catalogo.xml";
    private static CatalogoLibros catalogoLibros = new CatalogoLibros();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cargarCatalogo();
        int opcion;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Eliminar Libro");
            System.out.println("3. Listar Libros");
            System.out.println("4. Guardar Catálogo");
            System.out.println("5. Cargar Catálogo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    agregarLibro(sc);
                    break;
                case 2:
                    eliminarLibro(sc);
                    break;
                case 3:
                    listarLibros();
                    break;
                case 4:
                    guardarCatalogo();
                    break;
                case 5:
                    cargarCatalogo();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private static void agregarLibro(Scanner sc) {
        System.out.print("Introduzca el título del libro: ");
        String titulo = sc.nextLine();
        System.out.print("Introduzca el autor del libro: ");
        String autor = sc.nextLine();
        System.out.print("Introduzca el año de publicación: ");
        int año = sc.nextInt();
        sc.nextLine();
        System.out.print("Introduzca el género del libro: ");
        String genero = sc.nextLine();
        Libro libro = new Libro(titulo, autor, año, genero);
        catalogoLibros.agregarLibro(libro);
        System.out.println("Libro agregado.");
    }

    private static void eliminarLibro(Scanner sc) {
        System.out.print("Introduzca el título del libro a eliminar: ");
        String titulo = sc.nextLine();
        if (catalogoLibros.eliminarLibro(titulo)) {
            System.out.println("Libro eliminado.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private static void listarLibros() {
        catalogoLibros.listarLibros();
    }

    private static void guardarCatalogo() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CatalogoLibros.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(catalogoLibros, new File(ARCHIVO));
            System.out.println("Catálogo guardado en " + ARCHIVO);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void cargarCatalogo() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            System.out.println("El archivo " + ARCHIVO + " no existe. Se creará un nuevo archivo.");
            return;
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CatalogoLibros.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(catalogoLibros, file);
            System.out.println("Catálogo cargado desde " + ARCHIVO);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
