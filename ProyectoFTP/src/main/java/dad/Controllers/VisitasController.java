package dad.Controllers;

import dad.Conexion.*;
import dad.Model.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

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
        VisitaCreateController visitaCreateController = new VisitaCreateController();
        Stage stage = new Stage();
        stage.setTitle("Nueva Visita");
        stage.setScene(new Scene(visitaCreateController.getRoot()));
        stage.showAndWait();
        if (visitaCreateController.isConfirmar()){
            String fecha = visitaCreateController.getDate().toString();
            String observaciones = visitaCreateController.getObservaciones().getText();
            String idAlumno = visitaCreateController.getIdAlumno().getText();
            Integer idALumnoVerdad = Integer.valueOf(idAlumno);
            String idTutor = visitaCreateController.getIdTutor().getText();
            Integer idTutorVerdad = Integer.valueOf(idTutor);
            int nuevoId = insertarVisita(fecha,observaciones,idALumnoVerdad,idTutorVerdad);
            if (nuevoId > 0){
                cargarTablaVisita();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se puede añadir el alumno");
                alert.setContentText("Hubo un problema al insertar la visita en la base de datos");
                alert.showAndWait();
            }
        }

    }

    private int insertarVisita(String fecha, String observaciones, Integer idAlumno, Integer idTutor){
        String sql = "INSERT INTO visitas(Fecha_Visita,Observaciones,Id_Alumno,Id_Tutor) VALUES(?,?,?,?) ";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fecha);
            stmt.setString(2, observaciones);
            stmt.setInt(3, idAlumno);
            stmt.setInt(4, idAlumno);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
       Visita selected = tableVisitas.getSelectionModel().getSelectedItem();
       if (selected != null){
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Eliminar Visita");
           alert.setHeaderText("Se eliminará la visita " + selected.getIdVisita());
           alert.setContentText("¿Estás seguro?");
           Optional<ButtonType> result = alert.showAndWait();
           if (result.isPresent() && result.get()== ButtonType.OK){
               eliminarVisita(selected.getIdVisita());
               tableVisitas.getItems().remove(selected);
           }
       }
    }


    private void eliminarVisita(int IdVisitas){
        String sql = "DELETE FROM visitas WHERE id_Visita=?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, IdVisitas);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Alumno eliminado correctamente.");
            } else {
                System.out.println("No se encontró el alumno.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Visita> Visitas = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IdAlumno.setCellValueFactory(new PropertyValueFactory<>("IdAlumno"));
        Fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        IdTutor.setCellValueFactory(new PropertyValueFactory<>("IdTutor"));
        IdVisitas.setCellValueFactory(new PropertyValueFactory<>("IdVisitas"));
        cargarTablaVisita();
        tableVisitas.setItems(Visitas);
    }

    public void cargar(Visita VisitaSeleccionado){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Visitas.fxml"));
            Pane pane = loader.load();
            VisitasSelectedController visitasSelectedController = loader.getController();
            visitasSelectedController.obtenerVisita(VisitaSeleccionado);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void cargarTablaVisita(){
        Visitas.clear();
        String sql = "select * from visitas";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Visitas.add(new Visita(
                        rs.getInt("Id_Visita"),
                        rs.getString("Fecha de la visita"),
                        rs.getString("Observaciones"),
                        rs.getInt("Id_Alumno"),
                        rs.getInt("Id_Tutor")
                ));
            }
            tableVisitas.setItems(Visitas);
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
