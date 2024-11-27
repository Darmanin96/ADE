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
import java.time.*;
import java.util.*;
import java.util.Date;

public class VisitaController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<Visita, LocalDate> Fecha;

    @FXML
    private TableColumn<Visita, String> IdAlumno;

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

    private ObservableList<Visita> visitasLista = FXCollections.observableArrayList();


    @FXML
    void onAñadirAction(ActionEvent event) {
         VisitaCreateController visitaCreateController = new VisitaCreateController();
         Stage stage = new Stage();
         stage.setTitle("NUeva visita");
         stage.setScene(new Scene(visitaCreateController.getRoot()));
         stage.showAndWait();
         if (visitaCreateController.isConfirmar()){
             String idAlumnoVerdad = visitaCreateController.getIdAlumno().getValue();
             Integer idTutorVerdad = visitaCreateController.getIdTutor().getValue();
             String observaciones = visitaCreateController.getObservaciones().getText();
             LocalDate fecha = visitaCreateController.getDate().getValue();
             int nuevoId =  insertarVisita(fecha,observaciones,idAlumnoVerdad,idTutorVerdad);
             if (nuevoId > 0){
                 cargarTablaVisita();
             }else {
                 Alert alerta = new Alert(Alert.AlertType.ERROR);
                 alerta.setTitle("Error");
                 alerta.setHeaderText("No se pudo añadir la visita.");
                 alerta.setContentText("Hubo un problema al insertar la visita en la base de datos.");
                 alerta.showAndWait();
             }
         }
    }

    private int insertarVisita(LocalDate fecha,String observaciones,String idAlumno,Integer idTutor){
            String sql = "INSERT INTO visitas(Fecha_Visita,Observaciones,Id_Alumno,Id_Tutor) VALUES(?,?,?,?)";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fecha.toString());
            stmt.setString(2, observaciones);
            stmt.setString(3, idAlumno);
            stmt.setInt(4, idTutor);
            int i = stmt.executeUpdate();
            if (i > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
            Visita selected = tableVisitas.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Eliminar Visita");
                alert.setHeaderText("Se eliminará la visita con " + selected.getFecha());
                alert.setContentText("¿Estás seguro?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    eliminarVisita(selected.getIdVisita());
                    tableVisitas.getItems().remove(selected);
                }
            }
    }

    private void eliminarVisita(int visitaId){
        String sql = "DELETE FROM Visita WHERE IdVisita = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, visitaId);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IdVisitas.setCellValueFactory(new PropertyValueFactory<>("IdVisita"));
        IdTutor.setCellValueFactory(new PropertyValueFactory<>("IdTutor"));
        IdAlumno.setCellValueFactory(new PropertyValueFactory<>("IdAlumno"));
        Fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        Observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        cargarTablaVisita();
        tableVisitas.setItems(visitasLista);
        Fecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        Observaciones.setCellValueFactory(cellData -> cellData.getValue().observacionesProperty());
        tableVisitas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null) {
                cargar(newValue);
            }
        });
    }

    public void cargar(Visita visitaSeccionada){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VisitaSelected.fxml"));
            Pane pane = loader.load();
            VisitaSelectedController visitaSelectedController = loader.getController();
            visitaSelectedController.obtenerVisita(visitaSeccionada);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarTablaVisita(){
        visitasLista.clear();
        String sql = "SELECT * FROM visitas";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                visitasLista.add(new Visita(
                        rs.getInt("Id_Visita"),
                        rs.getDate("Fecha_Visita").toLocalDate(),
                        rs.getString("Observaciones"),
                        rs.getInt("Id_Alumno"),
                        rs.getInt("Id_Tutor")
                ));
            }
            tableVisitas.setItems(visitasLista);
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

    public TableColumn<Visita, LocalDate> getFecha() {
        return Fecha;
    }

    public void setFecha(TableColumn<Visita, LocalDate> fecha) {
        Fecha = fecha;
    }

    public ObservableList<Visita> getVisitasLista() {
        return visitasLista;
    }

    public void setVisitasLista(ObservableList<Visita> visitasLista) {
        this.visitasLista = visitasLista;
    }

    public TableColumn<Visita, String> getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(TableColumn<Visita, String> idAlumno) {
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
