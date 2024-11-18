package dad.Controllers;

import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class EmpresaSelectedController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Correo;

    @FXML
    private TextField Direccion;

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
    void onAñadirAction(ActionEvent event) {

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


    public void obtenerEmpresa(Empresa empresa) {
        if (empresa == null) {
            Nombre.textProperty().bindBidirectional(empresa.nombreProperty());
            Direccion.textProperty().bindBidirectional(empresa.direccionProperty());
            Correo.textProperty().bindBidirectional(empresa.emailProperty());
            Especialidad.textProperty().bindBidirectional(empresa.telefonoProperty());
            Telefono.textProperty().bindBidirectional(empresa.telefonoProperty());
            Participa.selectedProperty().bindBidirectional(empresa.participaProperty());
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

    public TextField getCorreo() {
        return Correo;
    }

    public void setCorreo(TextField correo) {
        Correo = correo;
    }

    public TextField getDireccion() {
        return Direccion;
    }

    public void setDireccion(TextField direccion) {
        Direccion = direccion;
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
}
