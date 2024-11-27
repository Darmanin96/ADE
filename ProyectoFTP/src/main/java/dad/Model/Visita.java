package dad.Model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Visita {

    private final IntegerProperty idVisita = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> fecha = new SimpleObjectProperty<>();
    private final StringProperty observaciones = new SimpleStringProperty();
    private final IntegerProperty idAlumno = new SimpleIntegerProperty();
    private final IntegerProperty idTutor = new SimpleIntegerProperty();

    public Visita() {
    }

    public Visita(Integer idVisita, LocalDate fecha, String observaciones, Integer idAlumno, Integer idTutor) {
        this.idVisita.set(idVisita);
        this.fecha.set(fecha);
        this.observaciones.set(observaciones);
        this.idAlumno.set(idAlumno);
        this.idTutor.set(idTutor);
    }

    public int getIdVisita() {
        return idVisita.get();
    }

    public void setIdVisita(int idVisita) {
        this.idVisita.set(idVisita);
    }

    public IntegerProperty idVisitaProperty() {
        return idVisita;
    }

    public LocalDate getFecha() {
        return fecha.get();
    }

    public void setFecha(LocalDate fecha) {
        this.fecha.set(fecha);
    }

    public ObjectProperty<LocalDate> fechaProperty() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones.get();
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }

    public StringProperty observacionesProperty() {
        return observaciones;
    }

    public int getIdAlumno() {
        return idAlumno.get();
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno.set(idAlumno);
    }

    public IntegerProperty idAlumnoProperty() {
        return idAlumno;
    }

    public int getIdTutor() {
        return idTutor.get();
    }

    public void setIdTutor(int idTutor) {
        this.idTutor.set(idTutor);
    }

    public IntegerProperty idTutorProperty() {
        return idTutor;
    }
}
