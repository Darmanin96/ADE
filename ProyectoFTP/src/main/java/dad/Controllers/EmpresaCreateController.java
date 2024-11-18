package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class EmpresaCreateController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Correo;

    @FXML
    private TextField Especialidad;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private CheckBox Participa;

    @FXML
    private TextField Plazas;

    @FXML
    private TextField Telefono;

    @FXML
    private BorderPane root;

    @FXML
    private TextField Direccion;


    private boolean confirmar = false;

    @FXML
    void onAñadirAction(ActionEvent event) {
        confirmar = true;
        cerrar();

    }



    private void cerrar(){
        Stage stage =(Stage) root.getScene().getWindow();
        stage.close();
    }


    private void limpiar(){
        Nombre.setText("");
        Correo.setText("");
        Especialidad.setText("");
        Participa.setSelected(false);
        Plazas.setText("");
        Telefono.setText("");
        Direccion.setText("");


    }
    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }


    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public EmpresaCreateController(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EmpresaCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TextField getDireccion() {
        return Direccion;
    }

    public void setDireccion(TextField direccion) {
        Direccion = direccion;
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

    public TextField getCorreo() {
        return Correo;
    }

    public void setCorreo(TextField correo) {
        Correo = correo;
    }

    public TextField getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(TextField especialidad) {
        Especialidad = especialidad;
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

    public CheckBox getParticipa() {
        return Participa;
    }

    public void setParticipa(CheckBox participa) {
        Participa = participa;
    }

    public TextField getPlazas() {
        return Plazas;
    }

    public void setPlazas(TextField plazas) {
        Plazas = plazas;
    }

    public TextField getTelefono() {
        return Telefono;
    }

    public void setTelefono(TextField telefono) {
        Telefono = telefono;
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
