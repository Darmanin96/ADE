package dad.Controllers;

import dad.Conexion.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class AsignacionCreateController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private Button Eliminar;

    @FXML
    private DatePicker FechaFin;

    @FXML
    private DatePicker FechaInicio;

    @FXML
    private ChoiceBox<Integer> IdAlumno;

    @FXML
    private ChoiceBox<Integer> IdEmpresa;

    @FXML
    private ChoiceBox<Integer> IdTutorDocente;

    @FXML
    private ChoiceBox<Integer> IdTutorEmpresa;

    @FXML
    private BorderPane root;

    private boolean confirmar = false;

    @FXML
    void onAñadirAction(ActionEvent event) {
        confirmar=true;
        cerrar();
    }

    private void validarCampo(TextField textField, String regex, String errorMessage) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(regex)) {
                textField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Tooltip tooltip = new Tooltip(errorMessage);
                Tooltip.install(textField, tooltip);
            } else {
                textField.setStyle(null);
                Tooltip.uninstall(textField, null);
            }
        });
    }



    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
        IdAlumno.getSelectionModel().clearSelection();
        IdEmpresa.getSelectionModel().clearSelection();
        IdTutorDocente.getSelectionModel().clearSelection();
        IdTutorEmpresa.getSelectionModel().clearSelection();
        FechaFin.setValue(null);
        FechaInicio.setValue(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            cargarIdAlumno();
            cargarIdTutor();
            cargarIdEmpresa();
            cargarIdTutorEmpresa();
    }




    public AsignacionCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AsignacionCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarIdAlumno(){
        String sql = "SELECT Id_Alumno FROM alumno";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ObservableList<Integer> alumnos = FXCollections.observableArrayList();
            while (rs.next()) {
                alumnos.add(rs.getInt("Id_Alumno"));
            }
            IdAlumno.setItems(alumnos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    private void cargarIdEmpresa(){
        String sql = "SELECT Id_Empresa FROM empresas WHERE Participa=1";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ObservableList<Integer> empresas = FXCollections.observableArrayList();
            while (rs.next()) {
                empresas.add(rs.getInt("Id_Empresa"));
            }
            IdEmpresa.setItems(empresas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void cargarIdTutorEmpresa(){
        String sql = "SELECT Id_Tutor FROM tutor_empresa";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ObservableList<Integer> tutorEmpresa = FXCollections.observableArrayList();
            while (rs.next()) {
                tutorEmpresa.add(rs.getInt("Id_Tutor"));
            }
            IdTutorEmpresa.setItems(tutorEmpresa);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void cargarIdTutor(){
        String sql = "SELECT Id_Tutor FROM tutor_grupo";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Integer> tutor = FXCollections.observableArrayList();
            while (rs.next()) {
                tutor.add(rs.getInt("Id_Tutor"));
            }
            IdTutorDocente.setItems(tutor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Button getAñadir() {
        return Añadir;
    }

    public void setAñadir(Button añadir) {
        Añadir = añadir;
    }

    public Button getCancelar() {
        return Cancelar;
    }

    public void setCancelar(Button cancelar) {
        Cancelar = cancelar;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public DatePicker getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(DatePicker fechaFin) {
        FechaFin = fechaFin;
    }

    public DatePicker getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(DatePicker fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public ChoiceBox<Integer> getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(ChoiceBox<Integer> idAlumno) {
        IdAlumno = idAlumno;
    }

    public ChoiceBox<Integer> getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(ChoiceBox<Integer> idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public ChoiceBox<Integer> getIdTutorDocente() {
        return IdTutorDocente;
    }

    public void setIdTutorDocente(ChoiceBox<Integer> idTutorDocente) {
        IdTutorDocente = idTutorDocente;
    }

    public ChoiceBox<Integer> getIdTutorEmpresa() {
        return IdTutorEmpresa;
    }

    public void setIdTutorEmpresa(ChoiceBox<Integer> idTutorEmpresa) {
        IdTutorEmpresa = idTutorEmpresa;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
