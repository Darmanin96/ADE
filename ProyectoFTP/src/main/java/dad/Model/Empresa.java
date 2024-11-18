package dad.Model;

import javafx.beans.property.*;

public class Empresa {

    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();
    private StringProperty telefono = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private BooleanProperty participa = new SimpleBooleanProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty especialidad = new SimpleStringProperty();


    public Empresa(StringProperty nombre, StringProperty direccion, StringProperty telefono, StringProperty email, BooleanProperty participa, IntegerProperty id, StringProperty especialidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.participa = participa;
        this.id = id;
        this.especialidad = especialidad;
    }

    public Empresa() {

    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public boolean isParticipa() {
        return participa.get();
    }

    public BooleanProperty participaProperty() {
        return participa;
    }

    public void setParticipa(boolean participa) {
        this.participa.set(participa);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public StringProperty especialidadProperty() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad.set(especialidad);
    }
}
