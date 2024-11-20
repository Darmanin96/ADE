package dad.Controllers;

import dad.Conexion.*;
import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.time.format.DateTimeFormatter;

import java.net.*;
import java.sql.*;
import java.util.*;

public class VisitasSelectedController implements Initializable {

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private DatePicker Date;

    @FXML
    private Button Eliminar;

    @FXML
    private TextField IdAlumno;

    @FXML
    private TextField IdTutor;

    @FXML
    private TextField Observaciones;

    @FXML
    private BorderPane root;

    private Visita visita;



    @FXML
    void onActualizarAction(ActionEvent event) {
        String fecha = Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String idAlumno = IdAlumno.getText();
        Integer idAlumnoVerdad = Integer.parseInt(IdAlumno.getText());
        Integer idTutorVerdad = Integer.parseInt(IdTutor.getText());
        String observaciones = Observaciones.getText();
        String sql = "UPDATE visitas SET Fecha_Visita=?,Observaciones=?,Id_Alumno=?,Id_Tutor=? WHERE Id_Visita=?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,fecha);
            stmt.setString(2,observaciones);
            stmt.setInt(3,idAlumnoVerdad);
            stmt.setInt(4,idTutorVerdad);
            stmt.setInt(5,visita.getIdVisita());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El tutor ha sido actualizado con éxito.");
            } else {
                System.err.println("No se encontró un tutor con ese ID.");
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar el tutor: " + e.getMessage());
        }
    }

    @FXML
    void onCancelarAction(ActionEvent event) {

    }

    @FXML
    void onLimpiarAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void obtenerVisita(Visita visita){
            if (visita != null){

            }
    }

    public Button getActualizar() {
        return Actualizar;
    }

    public void setActualizar(Button actualizar) {
        Actualizar = actualizar;
    }

    public Button getCancelar() {
        return Cancelar;
    }

    public void setCancelar(Button cancelar) {
        Cancelar = cancelar;
    }

    public DatePicker getDate() {
        return Date;
    }

    public void setDate(DatePicker date) {
        Date = date;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TextField getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(TextField idAlumno) {
        IdAlumno = idAlumno;
    }

    public TextField getIdTutor() {
        return IdTutor;
    }

    public void setIdTutor(TextField idTutor) {
        IdTutor = idTutor;
    }

    public TextField getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(TextField observaciones) {
        Observaciones = observaciones;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
}
