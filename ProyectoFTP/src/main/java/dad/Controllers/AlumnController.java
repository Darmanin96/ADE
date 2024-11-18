package dad.Controllers;

import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class AlumnController implements Initializable {

    @FXML
    private TableColumn<Alumno, String> Apellidos;

    @FXML
    private TableColumn<Alumno, String> Contacto;

    @FXML
    private TableColumn<Alumno, String> Nombre;

    @FXML
    private TableColumn<Alumno, Boolean> Pendientes;

    @FXML
    private TableColumn<Alumno, String> Programa;

    @FXML
    private TableColumn<Alumno, Integer> id_Alumn;

    @FXML
    private Button añadir;

    @FXML
    private Button eliminar;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Alumno> tableAlumn;

    @FXML
    private TableColumn<Alumno, String> tutorEmpresa;

    @FXML
    private TableColumn<Alumno, String> tutorGrupo;

    @FXML
    void onAñadirAction(ActionEvent event) {

    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            id_Alumn.setCellValueFactory(new PropertyValueFactory<>("id_Alumn"));
            Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            Apellidos.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
            tutorEmpresa.setCellValueFactory(new PropertyValueFactory<>("tutorEmpresa"));
            tutorGrupo.setCellValueFactory(new PropertyValueFactory<>("tutorGrupo"));
            Pendientes.setCellValueFactory(new PropertyValueFactory<>("Pendientes"));
            Programa.setCellValueFactory(new PropertyValueFactory<>("Programa"));
            Contacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));

    }


    private void cargarTablaAlumno(){

    }

    public AlumnController() {

    }


    public TableColumn<Alumno, String> getApellidos() {
        return Apellidos;
    }

    public void setApellidos(TableColumn<Alumno, String> apellidos) {
        Apellidos = apellidos;
    }

    public TableColumn<Alumno, String> getContacto() {
        return Contacto;
    }

    public void setContacto(TableColumn<Alumno, String> contacto) {
        Contacto = contacto;
    }

    public TableColumn<Alumno, String> getNombre() {
        return Nombre;
    }

    public void setNombre(TableColumn<Alumno, String> nombre) {
        Nombre = nombre;
    }

    public TableColumn<Alumno, Boolean> getPendientes() {
        return Pendientes;
    }

    public void setPendientes(TableColumn<Alumno, Boolean> pendientes) {
        Pendientes = pendientes;
    }

    public TableColumn<Alumno, String> getPrograma() {
        return Programa;
    }

    public void setPrograma(TableColumn<Alumno, String> programa) {
        Programa = programa;
    }

    public Button getAñadir() {
        return añadir;
    }

    public void setAñadir(Button añadir) {
        this.añadir = añadir;
    }

    public Button getEliminar() {
        return eliminar;
    }

    public void setEliminar(Button eliminar) {
        this.eliminar = eliminar;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<Alumno> getTableAlumn() {
        return tableAlumn;
    }

    public void setTableAlumn(TableView<Alumno> tableAlumn) {
        this.tableAlumn = tableAlumn;
    }

    public TableColumn<Alumno, String> getTutorEmpresa() {
        return tutorEmpresa;
    }

    public void setTutorEmpresa(TableColumn<Alumno, String> tutorEmpresa) {
        this.tutorEmpresa = tutorEmpresa;
    }

    public TableColumn<Alumno, String> getTutorGrupo() {
        return tutorGrupo;
    }

    public void setTutorGrupo(TableColumn<Alumno, String> tutorGrupo) {
        this.tutorGrupo = tutorGrupo;
    }

    public TableColumn<Alumno, Integer> getId_Alumn() {
        return id_Alumn;
    }

    public void setId_Alumn(TableColumn<Alumno, Integer> id_Alumn) {
        this.id_Alumn = id_Alumn;
    }
}
