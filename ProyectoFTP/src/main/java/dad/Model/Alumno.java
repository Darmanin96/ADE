package dad.Model;

import javafx.beans.property.*;

public class Alumno {
    private IntegerProperty id_Alumno = new SimpleIntegerProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty apellido = new SimpleStringProperty();
    private StringProperty tutor_empresa = new SimpleStringProperty();
    private StringProperty tutor_grupo = new SimpleStringProperty();
    private BooleanProperty pendientes = new SimpleBooleanProperty();
    private StringProperty programa = new SimpleStringProperty();
    private StringProperty contacto = new SimpleStringProperty();

    public Alumno(StringProperty nombre, StringProperty apellido, StringProperty tutor_empresa, StringProperty tutor_grupo, BooleanProperty pendientes, StringProperty programa, StringProperty contacto) {
        this.id_Alumno=id_Alumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tutor_empresa = tutor_empresa;
        this.tutor_grupo = tutor_grupo;
        this.pendientes = pendientes;
        this.programa = programa;
        this.contacto = contacto;
    }

    public Alumno() {

    }

    public int getId_Alumno() {
        return id_Alumno.get();
    }

    public IntegerProperty id_AlumnoProperty() {
        return id_Alumno;
    }

    public void setId_Alumno(int id_Alumno) {
        this.id_Alumno.set(id_Alumno);
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

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getTutor_empresa() {
        return tutor_empresa.get();
    }

    public StringProperty tutor_empresaProperty() {
        return tutor_empresa;
    }

    public void setTutor_empresa(String tutor_empresa) {
        this.tutor_empresa.set(tutor_empresa);
    }

    public String getTutor_grupo() {
        return tutor_grupo.get();
    }

    public StringProperty tutor_grupoProperty() {
        return tutor_grupo;
    }

    public void setTutor_grupo(String tutor_grupo) {
        this.tutor_grupo.set(tutor_grupo);
    }

    public boolean isPendientes() {
        return pendientes.get();
    }

    public BooleanProperty pendientesProperty() {
        return pendientes;
    }

    public void setPendientes(boolean pendientes) {
        this.pendientes.set(pendientes);
    }

    public String getPrograma() {
        return programa.get();
    }

    public StringProperty programaProperty() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa.set(programa);
    }

    public String getContacto() {
        return contacto.get();
    }

    public StringProperty contactoProperty() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto.set(contacto);
    }
}
