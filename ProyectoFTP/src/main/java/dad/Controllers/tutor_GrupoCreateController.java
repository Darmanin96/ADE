package dad.Controllers;

import dad.Model.Curso;
import javafx.collections.FXCollections;
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
    private ChoiceBox<Curso> grupoCBox;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField idEmpresa;

    @FXML
    private TextField Nombre;

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
        grupoCBox.getItems().clear();
    }

    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grupoCBox.setItems(FXCollections.observableArrayList(dad.Model.Curso.values()));

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

    public ChoiceBox<Curso> getGrupoCBox() {
        return grupoCBox;
    }

    public void setGrupoCBox(ChoiceBox<Curso> grupoCBox) {
        this.grupoCBox = grupoCBox;
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

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public TextField getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(TextField idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
