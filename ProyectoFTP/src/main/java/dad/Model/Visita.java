package dad.Model;

import javafx.beans.property.*;

public class Visita {

    private final IntegerProperty idVisita = new SimpleIntegerProperty();
    private final StringProperty fecha = new SimpleStringProperty();
    private final StringProperty observaciones = new SimpleStringProperty();
    private final IntegerProperty idAlumno = new SimpleIntegerProperty();
    private final IntegerProperty idTutor = new SimpleIntegerProperty();


    public Visita() {
    }

    public Visita(Integer idVisita,String fecha,String observaciones,Integer idAlumno,Integer idTutor){
        this.idVisita.set(idVisita);
        this.fecha.set(fecha);
        this.observaciones.set(observaciones);
        this.idAlumno.set(idAlumno);
        this.idTutor.set(idTutor);
    }


    public int getIdVisita() {
        return idVisita.get();
    }

    public IntegerProperty idVisitaProperty() {
        return idVisita;
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones.get();
    }

    public StringProperty observacionesProperty() {
        return observaciones;
    }

    public int getIdAlumno() {
        return idAlumno.get();
    }

    public IntegerProperty idAlumnoProperty() {
        return idAlumno;
    }

    public int getIdTutor() {
        return idTutor.get();
    }

    public IntegerProperty idTutorProperty() {
        return idTutor;
    }
}
