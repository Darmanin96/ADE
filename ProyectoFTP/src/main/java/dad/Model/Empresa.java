package dad.Model;

import javafx.beans.property.*;

public class Empresa {

    private IntegerProperty id_Empresa = new SimpleIntegerProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();
    private StringProperty telefono = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private BooleanProperty participa = new SimpleBooleanProperty();
    private IntegerProperty plazas = new SimpleIntegerProperty();
    private IntegerProperty especialidad = new SimpleIntegerProperty();

    public Empresa(int id_Empresa, String nombre, String direccion, String telefono, String email, boolean participa, int plazas,int especialidad) {
        this.id_Empresa.set(id_Empresa);
        this.nombre.set(nombre);
        this.direccion.set(direccion);
        this.telefono.set(telefono);
        this.email.set(email);
        this.participa.set(participa);
        this.especialidad.set(especialidad);
        this.plazas.set(plazas);
    }

    public Empresa() {}

    public int getPlazas() {
        return plazas.get();
    }

    public IntegerProperty plazasProperty() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas.set(plazas);
    }

    public int getId_Empresa() {
        return id_Empresa.get();
    }

    public IntegerProperty id_EmpresaProperty() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa.set(id_Empresa);
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

    public int getEspecialidad() {
        return especialidad.get();
    }

    public IntegerProperty especialidadProperty() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad.set(especialidad);
    }
}
