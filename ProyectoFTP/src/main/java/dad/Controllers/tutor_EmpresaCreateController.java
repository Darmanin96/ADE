package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class tutor_EmpresaCreateController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Direccion;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private BorderPane root;

    private boolean confirmar = false;

    @FXML
    void onAñadirAction(ActionEvent event) {
        confirmar = true;
        cerrar();
    }


    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    private void limpiar(){
        Nombre.setText("");
        Direccion.setText("");
    }

    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public tutor_EmpresaCreateController(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tutor_EmpresaCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public TextField getDireccion() {
        return Direccion;
    }

    public void setDireccion(TextField direccion) {
        Direccion = direccion;
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

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
