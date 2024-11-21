package dad.Model;

import javafx.beans.property.*;

public class tutorGrupo {

    private final IntegerProperty id_tutor = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty grupo = new SimpleStringProperty();
    private final StringProperty id_alumno = new SimpleStringProperty();


    public tutorGrupo() {
    }

    public tutorGrupo(int id_tutor, String nombre, String grupo, String id_alumno) {
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
        return Integer.parseInt(id_alumno.get());
    }

    public StringProperty id_alumnoProperty() {
        return id_alumno;
    }


    public void setId_alumno(int id_alumno) {
        this.id_alumno.set(String.valueOf(id_alumno));
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor.set(id_tutor);
    }


}



