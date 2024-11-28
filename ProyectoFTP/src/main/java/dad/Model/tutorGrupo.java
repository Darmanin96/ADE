package dad.Model;

import javafx.beans.property.*;

public class tutorGrupo {

    private final IntegerProperty id_tutor = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final ObjectProperty<Curso> grupo = new SimpleObjectProperty<>();
    //private final IntegerProperty id_alumno = new SimpleIntegerProperty();


    public tutorGrupo() {
    }

    public tutorGrupo(int id_tutor, String nombre, Curso grupo/*, Integer id_alumno*/) {
        this.id_tutor.set(id_tutor);
        this.nombre.set(nombre);
        this.grupo.set(grupo);
        //this.id_alumno.set(id_alumno);
    }



    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setGrupo(Curso grupo) {
        this.grupo.set(grupo);
    }

    public Curso getGrupo() {
        return grupo.get();
    }

    public ObjectProperty<Curso> grupoProperty() {
        return grupo;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getId_tutor() {
        return id_tutor.get();
    }

    public IntegerProperty id_tutorProperty() {
        return id_tutor;
    }
/*
    public int getId_alumno() {
        return id_alumno.get();
    }

    public IntegerProperty id_alumnoProperty() {
        return id_alumno;
    }*/



}



