package dad.Model;


import javafx.beans.property.*;

import java.time.*;

public class Asignacion {
    private final IntegerProperty idAsignacion = new SimpleIntegerProperty();
    private final IntegerProperty idAlumno = new SimpleIntegerProperty();
    private final IntegerProperty idEmpresa = new SimpleIntegerProperty();
    private final IntegerProperty idTutorEmpresa = new SimpleIntegerProperty();
    private final IntegerProperty idTutorGrupo = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> fechaInicio = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> fechaFin = new SimpleObjectProperty<>();
    private final StringProperty Nombre = new SimpleStringProperty();
    private final StringProperty Apellido = new SimpleStringProperty();
    private final StringProperty Empresa = new SimpleStringProperty();

    public Asignacion() {
    }

   public Asignacion(Integer idAsignacion, Integer idAlumno, Integer idEmpresa,Integer idTutorGrupo, Integer idTutorEmpresa, String Nombre, String Apellido, String Empresa,LocalDate fechaInicio, LocalDate fechaFin){
        this.idAsignacion.set(idAsignacion);
        this.idAlumno.set(idAlumno);
        this.idEmpresa.set(idEmpresa);
        this.idTutorGrupo.set(idTutorGrupo);
        this.idTutorEmpresa.set(idTutorEmpresa);
        this.Nombre.set(Nombre);
        this.Apellido.set(Apellido);
        this.Empresa.set(Empresa);
        this.fechaInicio.set(fechaInicio);
        this.fechaFin.set(fechaFin);
   }



    public String getNombre() {
        return Nombre.get();
    }

    public StringProperty nombreProperty() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido.get();
    }

    public StringProperty apellidoProperty() {
        return Apellido;
    }

    public String getEmpresa() {
        return Empresa.get();
    }

    public StringProperty empresaProperty() {
        return Empresa;
    }

    public int getIdAsignacion() {
        return idAsignacion.get();
    }

    public IntegerProperty idAsignacionProperty() {
        return idAsignacion;
    }

    public int getIdAlumno() {
        return idAlumno.get();
    }

    public IntegerProperty idAlumnoProperty() {
        return idAlumno;
    }

    public int getIdEmpresa() {
        return idEmpresa.get();
    }

    public IntegerProperty idEmpresaProperty() {
        return idEmpresa;
    }

    public int getIdTutorEmpresa() {
        return idTutorEmpresa.get();
    }

    public IntegerProperty idTutorEmpresaProperty() {
        return idTutorEmpresa;
    }

    public int getIdTutorGrupo() {
        return idTutorGrupo.get();
    }

    public IntegerProperty idTutorGrupoProperty() {
        return idTutorGrupo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio.get();
    }

    public ObjectProperty<LocalDate> fechaInicioProperty() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin.get();
    }

    public ObjectProperty<LocalDate> fechaFinProperty() {
        return fechaFin;
    }
}
