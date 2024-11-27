package dad.Model;

import javafx.beans.property.*;

public class Empresa {

    private final IntegerProperty id_Empresa = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty direccion = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty correo = new SimpleStringProperty();
    private final BooleanProperty participa = new SimpleBooleanProperty();
    private final ObjectProperty<Integer> plazas = new SimpleObjectProperty<>();
    private final StringProperty especialidad = new SimpleStringProperty();

    public Empresa(int id_Empresa, String nombre, String direccion, String telefono, String email, boolean participa, Integer plazas, String especialidad) {
        this.id_Empresa.set(id_Empresa);
        this.nombre.set(nombre);
        this.direccion.set(direccion);
        this.telefono.set(telefono);
        this.correo.set(email);
        this.participa.set(participa);
        this.especialidad.set(especialidad);
        this.plazas.set(plazas);
    }

    public Empresa() {
    }

    public Integer getPlazas() {
        return plazas.get();
    }

    public ObjectProperty<Integer> plazasProperty() {
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

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
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


