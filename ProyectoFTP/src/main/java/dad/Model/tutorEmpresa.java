package dad.Model;

import javafx.beans.property.*;

public class tutorEmpresa {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty contacto = new SimpleStringProperty();

    public tutorEmpresa() {
    }


    public tutorEmpresa(int id, String nombre, String contacto) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.contacto.set(contacto);
    }

    // Getters
    public int getId() {
        return id.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getContacto() {
        return contacto.get();
    }

    // Setters
    public void setId(int id) {
        this.id.set(id);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setContacto(String contacto) {
        this.contacto.set(contacto);
    }

    // Propiedades
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty contactoProperty() {
        return contacto;
    }
}
