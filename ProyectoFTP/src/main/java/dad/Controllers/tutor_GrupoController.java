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

import java.io.IOException;
import java.net.*;
import java.sql.*;
import java.util.*;

public class tutor_GrupoController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<tutorGrupo, String> Grupo;

    @FXML
    private TableColumn<tutorGrupo, String> Nombre;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private TableColumn<tutorGrupo, Integer> idTutor;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<tutorGrupo> tableGrupo;

    private ObservableList<tutorGrupo> tutorGrupo = FXCollections.observableArrayList();

    @FXML
    void onAñadirAction(ActionEvent event) {
        tutor_GrupoCreateController tutor_GrupoCreateController = new tutor_GrupoCreateController();
        Stage stage = new Stage();
        stage.setTitle("Nuevo tutor de grupo");
        stage.setScene(new Scene(tutor_GrupoCreateController.getRoot()));
        stage.showAndWait();
        if (tutor_GrupoCreateController.isConfirmar()){
            tutor_GrupoController tutor_GrupoController = new tutor_GrupoController();
            String nombre = tutor_GrupoCreateController.getNombre().getText();
            String grupo = tutor_GrupoCreateController.getGrupoCBox().getValue().toString();
            int nuevoId = insertarTutorGrupo(nombre,grupo);
            if (nuevoId > 0) {
                cargarTablaTutorGrupo();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("No se pudo añadir el tutor.");
                alerta.setContentText("Hubo un problema al insertar el tutor en la base de datos.");
                alerta.showAndWait();
            }
        }
    }

    private int insertarTutorGrupo(String nombre, String grupo){
        String sql = "INSERT INTO tutor_grupo (Nombre,Grupo) VALUES (?,?)";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setString(2, grupo);
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
        tutorGrupo selected = tableGrupo.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar tutor de grupo");
            alert.setHeaderText("Se eliminará el tutor" + selected.getNombre());
            alert.setContentText("¿Estás seguro?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
             eliminarTutorGrupo(selected.getId_tutor());
             tableGrupo.getItems().remove(selected);
            }
        }
    }

    private void eliminarTutorGrupo(int tutorgrupoId) {
        String sql = "DELETE FROM tutor_grupo WHERE Id_Tutor = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tutorgrupoId);
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
        idTutor.setCellValueFactory(new PropertyValueFactory<>("idTutor"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        cargarTablaTutorGrupo();
        tableGrupo.setItems(tutorGrupo);
        idTutor.setCellValueFactory(cellData -> cellData.getValue().id_tutorProperty().asObject());
        Nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        Grupo.setCellValueFactory(cellData -> cellData.getValue().grupoProperty().asString());
        tableGrupo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null) {
                cargar(newValue);
            }
        });

    }

    public void cargar(tutorGrupo tutorGrupoSeleccionado){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tutor_GrupoSelected.fxml"));
            Pane pane = loader.load();
            tutor_GrupoSelectedController tutorGrupoSelectedController = loader.getController();
            tutorGrupoSelectedController.obtenerTutorGrupo(tutorGrupoSeleccionado);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void cargarTablaTutorGrupo() {
        String sql = "SELECT * FROM tutor_grupo";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tutorGrupo.add(new tutorGrupo(
                        rs.getInt("Id_Tutor"),
                        rs.getString("Nombre"),
                        Curso.valueOf(rs.getString("grupo"))
                ));
            }
            tableGrupo.setItems(tutorGrupo);
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

    public TableColumn<tutorGrupo, String> getGrupo() {
        return Grupo;
    }

    public void setGrupo(TableColumn<tutorGrupo, String> grupo) {
        Grupo = grupo;
    }

    public TableColumn<tutorGrupo, String> getNombre() {
        return Nombre;
    }

    public void setNombre(TableColumn<tutorGrupo, String> nombre) {
        Nombre = nombre;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

    public TableColumn<tutorGrupo, Integer> getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(TableColumn<tutorGrupo, Integer> idTutor) {
        this.idTutor = idTutor;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<tutorGrupo> getTableGrupo() {
        return tableGrupo;
    }

    public void setTableGrupo(TableView<tutorGrupo> tableGrupo) {
        this.tableGrupo = tableGrupo;
    }
}
