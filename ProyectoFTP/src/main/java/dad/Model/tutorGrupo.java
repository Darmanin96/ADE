package dad.Model;

import javafx.beans.property.*;

public class tutorGrupo {

    private final IntegerProperty id_tutor = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty grupo = new SimpleStringProperty();
    private final IntegerProperty id_alumno = new SimpleIntegerProperty();


    public tutorGrupo() {
    }

    public tutorGrupo(int id_tutor, String nombre, String grupo, int id_alumno) {
        this.id_tutor.set(id_tutor);
        this.nombre.set(nombre);
        this.grupo.set(grupo);
        this.id_alumno.set(id_alumno);
    }



    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getGrupo() {
        return grupo.get();
    }

    public StringProperty grupoProperty() {
        return grupo;
    }


    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setGrupo(String grupo) {
        this.grupo.set(grupo);
    }

    public int getId_tutor() {
        return id_tutor.get();
    }

    public IntegerProperty id_tutorProperty() {
        return id_tutor;
    }

    public int getId_alumno() {
        return id_alumno.get();
    }

    public IntegerProperty id_alumnoProperty() {
        return id_alumno;
    }


}



