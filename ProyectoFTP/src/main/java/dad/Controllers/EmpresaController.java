package dad.Controllers;

import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class EmpresaController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private TableColumn<Empresa, String> Correo;

    @FXML
    private TableColumn<Empresa, String> Direccion;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<Empresa, String> Especialidad;

    @FXML
    private TableColumn<Empresa, Integer> Id_Empresa;

    @FXML
    private TableColumn<Empresa, String> Nombre;

    @FXML
    private TableColumn<Empresa, Boolean> Participa;

    @FXML
    private TableColumn<Empresa, Integer> Plazas;

    @FXML
    private TableColumn<Empresa, String> Telefono;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Empresa> tableEmpresa;


    @FXML
    void onAñadirAction(ActionEvent event) {

    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public Button getAñadir() {
        return Añadir;
    }

    public void setAñadir(Button añadir) {
        Añadir = añadir;
    }

    public TableColumn<Empresa, String> getCorreo() {
        return Correo;
    }

    public void setCorreo(TableColumn<Empresa, String> correo) {
        Correo = correo;
    }

    public TableColumn<Empresa, String> getDireccion() {
        return Direccion;
    }

    public void setDireccion(TableColumn<Empresa, String> direccion) {
        Direccion = direccion;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TableColumn<Empresa, String> getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(TableColumn<Empresa, String> especialidad) {
        Especialidad = especialidad;
    }

    public TableColumn<Empresa, Integer> getId_Empresa() {
        return Id_Empresa;
    }

    public void setId_Empresa(TableColumn<Empresa, Integer> id_Empresa) {
        Id_Empresa = id_Empresa;
    }

    public TableColumn<Empresa, String> getNombre() {
        return Nombre;
    }

    public void setNombre(TableColumn<Empresa, String> nombre) {
        Nombre = nombre;
    }

    public TableColumn<Empresa, Boolean> getParticipa() {
        return Participa;
    }

    public void setParticipa(TableColumn<Empresa, Boolean> participa) {
        Participa = participa;
    }

    public TableColumn<Empresa, Integer> getPlazas() {
        return Plazas;
    }

    public void setPlazas(TableColumn<Empresa, Integer> plazas) {
        Plazas = plazas;
    }

    public TableColumn<Empresa, String> getTelefono() {
        return Telefono;
    }

    public void setTelefono(TableColumn<Empresa, String> telefono) {
        Telefono = telefono;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<Empresa> getTableEmpresa() {
        return tableEmpresa;
    }

    public void setTableEmpresa(TableView<Empresa> tableEmpresa) {
        this.tableEmpresa = tableEmpresa;
    }
}
