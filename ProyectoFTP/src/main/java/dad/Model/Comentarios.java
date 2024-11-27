package dad.Model;

import javafx.beans.property.*;
import javafx.fxml.*;

import java.time.*;

public class Comentarios  {
    private final IntegerProperty idComentario = new SimpleIntegerProperty();
    private final StringProperty Comentario = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fechaComentario = new SimpleObjectProperty<>();
    private final IntegerProperty idEmpresa = new SimpleIntegerProperty();
    private final StringProperty nombreEmpresa  = new SimpleStringProperty();


    public Comentarios() {
    }

    public Comentarios(Integer idComentario, String Comentario, LocalDate fechaComentario, Integer idEmpresa, String nombreEmpresa) {
        this.idComentario.set(idComentario);
        this.Comentario.set(Comentario);
        this.fechaComentario.set(fechaComentario);
        this.idEmpresa.set(idEmpresa);
        this.nombreEmpresa.set(nombreEmpresa);
    }



    public int getIdComentario() {
        return idComentario.get();
    }

    public IntegerProperty idComentarioProperty() {
        return idComentario;
    }

    public String getComentario() {
        return Comentario.get();
    }

    public StringProperty comentarioProperty() {
        return Comentario;
    }

    public LocalDate getFechaComentario() {
        return fechaComentario.get();
    }

    public ObjectProperty<LocalDate> fechaComentarioProperty() {
        return fechaComentario;
    }

    public int getIdEmpresa() {
        return idEmpresa.get();
    }

    public IntegerProperty idEmpresaProperty() {
        return idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa.get();
    }

    public StringProperty nombreEmpresaProperty() {
        return nombreEmpresa;
    }
}
