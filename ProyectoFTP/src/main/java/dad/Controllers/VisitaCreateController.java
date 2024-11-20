package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class VisitaCreateController implements Initializable {

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

    private boolean confirmar = true;

    @FXML
    void onActualizarAction(ActionEvent event) {
        confirmar=true;
        cerrar();
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    private void limpiar(){
        Date.setValue(null);
        IdAlumno.setText(null);
        IdTutor.setText(null);
    }

    private void cerrar(){
        Stage stage = (Stage) Cancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public VisitaCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VisitaCreate.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
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
}
