package dad.Controllers;

import dad.Conexion.Conectar;
import dad.Model.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class tutorGrupoController implements Initializable {

    @FXML
    private Button añadir;

    @FXML
    private Button eliminar;

    @FXML
    private TableColumn<tutorGrupo, Curso> grupo;

    @FXML
    private TableColumn<tutorGrupo, String> nombre;

    @FXML
    private TableColumn<tutorGrupo, Integer> idAlumno;

    @FXML
    private TableColumn<tutorGrupo, Integer> idTutor;

    @FXML
    private TableView<tutorGrupo> tableGrupo;

    @FXML
    private AnchorPane detallePanel;

    private final ObservableList<tutorGrupo> TutorGrupoLista = FXCollections.observableArrayList();

    @FXML
    void onAñadirAction(ActionEvent event) {
        tutorGrupoCreateController tutorGrupoCreateController = new tutorGrupoCreateController();
        Stage stage = new Stage();
        stage.setTitle("Nuevo Tutor Docente");
        stage.setScene(new Scene(tutorGrupoCreateController.getRoot()));
        stage.showAndWait();
        if (tutorGrupoCreateController.isConfirmar()) {
            String nombre = tutorGrupoCreateController.getNombre().getText();
            Curso grupo = tutorGrupoCreateController.getGrupoSeleccionado();
            //Integer idAlumno = tutorGrupoCreateController.getIdAlumno().getValue();
            int nuevoId = insertarTutorGrupo(nombre, grupo/*, idAlumno*/);
            if (nuevoId > 0) {
                cargarTablaTutorGrupo();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("No se pudo añadir el alumno.");
                alerta.setContentText("Hubo un problema al insertar el alumno en la base de datos.");
                alerta.showAndWait();
            }
        }
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
            tutorGrupo selected = tableGrupo.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Eliminar tutor");
                alert.setHeaderText("Se eliminará el tutor " + selected.getNombre());
                alert.setContentText("¿Estas seguro?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    eliminarTutorGrupo(selected.getId_tutor());
                    tableGrupo.getItems().remove(selected);
                }
            }
    }

    public void cargar(tutorGrupo tutorGrupoSelected) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tutor_GrupoSelected.fxml"));
            Pane pane = loader.load();
            tutorGrupoSelectedController tutorGrupoSelectedController = loader.getController();
            tutorGrupoSelectedController.obtenerTutor(tutorGrupoSelected);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void cargarTablaTutorGrupo() {
        TutorGrupoLista.clear();
        String sql = "SELECT * FROM tutor_grupo";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TutorGrupoLista.add(new tutorGrupo(
                        rs.getInt("id_tutor"),
                        rs.getString("nombre"),
                        Curso.valueOf(rs.getString("grupo"))
                        //rs.getInt("id_alumno")
                ));
            }
            tableGrupo.setItems(TutorGrupoLista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertarTutorGrupo(String nombre, Curso grupo/*, Integer idAlumno*/) {
        String sql = "INSERT INTO tutor_grupo (nombre, grupo) VALUES (?, ?)";
        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setString(2, grupo.toString());
            //stmt.setInt(3, idAlumno);
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

    private void eliminarTutorGrupo(int tutorGrupoId) {
        String sql = "DELETE FROM tutor_grupo WHERE id_tutor = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tutorGrupoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTutor.setCellValueFactory(new PropertyValueFactory<>("id_tutor"));
        //idAlumno.setCellValueFactory(new PropertyValueFactory<>("id_alumno"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        cargarTablaTutorGrupo();
        nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        grupo.setCellValueFactory(cellData -> cellData.getValue().grupoProperty());
        tableGrupo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null){
                cargar(newValue);
            }
        });
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

    public TableColumn<tutorGrupo, Curso> getGrupo() {
        return grupo;
    }

    public void setGrupo(TableColumn<tutorGrupo, Curso> grupo) {
        this.grupo = grupo;
    }

    public TableColumn<tutorGrupo, String> getNombre() {
        return nombre;
    }

    public void setNombre(TableColumn<tutorGrupo, String> nombre) {
        this.nombre = nombre;
    }

    public TableColumn<tutorGrupo, Integer> getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(TableColumn<tutorGrupo, Integer> idAlumno) {
        this.idAlumno = idAlumno;
    }

    public TableColumn<tutorGrupo, Integer> getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(TableColumn<tutorGrupo, Integer> idTutor) {
        this.idTutor = idTutor;
    }

    public TableView<tutorGrupo> getTableGrupo() {
        return tableGrupo;
    }

    public void setTableGrupo(TableView<tutorGrupo> tableGrupo) {
        this.tableGrupo = tableGrupo;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

    public ObservableList<tutorGrupo> getTutorGrupoLista() {
        return TutorGrupoLista;
    }
}
