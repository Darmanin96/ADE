package dad.Controllers;

import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class VisitasController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<Visita, Date> Fecha;

    @FXML
    private TableColumn<Visita, Integer> IdAlumno;

    @FXML
    private TableColumn<Visita, Integer> IdTutor;

    @FXML
    private TableColumn<Visita, Integer> IdVisitas;

    @FXML
    private TableColumn<Visita, String> Observaciones;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Visita> tableVisitas;

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

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TableColumn<Visita, Date> getFecha() {
        return Fecha;
    }

    public void setFecha(TableColumn<Visita, Date> fecha) {
        Fecha = fecha;
    }

    public TableColumn<Visita, Integer> getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(TableColumn<Visita, Integer> idAlumno) {
        IdAlumno = idAlumno;
    }

    public TableColumn<Visita, Integer> getIdTutor() {
        return IdTutor;
    }

    public void setIdTutor(TableColumn<Visita, Integer> idTutor) {
        IdTutor = idTutor;
    }

    public TableColumn<Visita, Integer> getIdVisitas() {
        return IdVisitas;
    }

    public void setIdVisitas(TableColumn<Visita, Integer> idVisitas) {
        IdVisitas = idVisitas;
    }

    public TableColumn<Visita, String> getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(TableColumn<Visita, String> observaciones) {
        Observaciones = observaciones;
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

    public TableView<Visita> getTableVisitas() {
        return tableVisitas;
    }

    public void setTableVisitas(TableView<Visita> tableVisitas) {
        this.tableVisitas = tableVisitas;
    }
}
