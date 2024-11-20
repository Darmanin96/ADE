package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class tutor_GrupoCreateController implements Initializable {

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Grupo;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField idAlumno;

    @FXML
    private BorderPane root;

    @FXML
    void onActualizarAction(ActionEvent event) {
        confirmar = true;
        cerrar();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }

    @FXML
    void ónCancelarAction(ActionEvent event) {
        cerrar();
    }

    private boolean confirmar = false;

    private void limpiar(){
        Nombre.setText("");
        idAlumno.setText("");
        Grupo.setText("");
    }

    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public tutor_GrupoCreateController(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/tutor_GrupoCreate.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public TextField getGrupo() {
        return Grupo;
    }

    public void setGrupo(TextField grupo) {
        Grupo = grupo;
    }

    public Button getLimpiar() {
        return Limpiar;
    }

    public void setLimpiar(Button limpiar) {
        Limpiar = limpiar;
    }

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
    }

    public TextField getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(TextField idAlumno) {
        this.idAlumno = idAlumno;
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
