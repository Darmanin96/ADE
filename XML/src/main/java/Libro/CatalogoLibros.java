package Libro;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "catalogoLibros")
public class CatalogoLibros {
    private List<Libro> libros;

    public CatalogoLibros() {
        this.libros = new ArrayList<>();
    }

    @XmlElement(name = "libro") // Añadir nombre para el elemento de libro
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public boolean eliminarLibro(String titulo) {
        return libros.removeIf(libro -> libro.getTitulo().equalsIgnoreCase(titulo));
    }

    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en el catálogo.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}

