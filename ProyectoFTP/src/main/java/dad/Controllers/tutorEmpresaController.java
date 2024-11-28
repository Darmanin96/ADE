package dad.Controllers;

import dad.Conexion.*;
import static dad.Conexion.Conectar.getConnection;
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

public class tutorEmpresaController implements Initializable {


    @FXML
    private Button Añadir;

    @FXML
    private TableColumn<tutorEmpresa, String> Cointacto;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<tutorEmpresa, String> Nombre;

    @FXML
    private TableColumn<tutorEmpresa, Integer> idTutor;

    @FXML
    private BorderPane root;

    @FXML
    private TableColumn<tutorEmpresa, String> nombreEmpresa;

    @FXML
    private TableColumn<tutorEmpresa, Integer> idEmpresa;

    @FXML
    private TableView<tutorEmpresa> table_TutorEmpresa;

    private ObservableList<tutorEmpresa> tutor = FXCollections.observableArrayList();

    private Stage tutorEmpresaStage;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    void onAñadirAction(ActionEvent event) {
        if (tutorEmpresaStage == null || !tutorEmpresaStage.isShowing()) {
            tutor_EmpresaCreateController tutor_EmpresaCreateController = new tutor_EmpresaCreateController();
            tutorEmpresaStage = new Stage();
            tutorEmpresaStage.setTitle("Nuevo tutor de Empresa");
            tutorEmpresaStage.initModality(Modality.APPLICATION_MODAL);
            tutorEmpresaStage.setScene(new Scene(tutor_EmpresaCreateController.getRoot()));
            tutorEmpresaStage.setOnHidden(e -> {
                tutorEmpresaStage = null;
                if (tutor_EmpresaCreateController.isConfirmar()) {
                    String Nombre = tutor_EmpresaCreateController.getNombre().getText();
                    String Contanto = tutor_EmpresaCreateController.getDireccion().getText();
                    Integer idEmpresa = tutor_EmpresaCreateController.getIdEmpresas().getValue();
                    String nombreEmpresa = obtenerNombreEmpresa(idEmpresa);
                    int nuevoId = insertarTutorEmpresa(Nombre, Contanto,idEmpresa,nombreEmpresa);
                    if (nuevoId > 0) {
                        cargarTablaTutorEmpresa();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No se puede añadir el tutor");
                        alert.setContentText("Hubo un problema al insertar el tutor en la base de datos");
                        alert.showAndWait();
                    }
                }
            });
            tutorEmpresaStage.show();
        } else {
            tutorEmpresaStage.requestFocus();
        }

    }

    private String obtenerNombreEmpresa(Integer idEmpresa) {
        String sql = "SELECT nombre FROM empresas WHERE Id_Empresa = ?";
        String nombre = "";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombre;
    }

    private int insertarTutorEmpresa(String Nombre, String Contanto,Integer idEmpresa, String nombreEmpresa) {
        String sql = "INSERT INTO tutor_empresa(Nombre,Contacto,Id_Empresa,nombreEmpresa) VALUES(?,?,?,?)";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, Nombre);
            stmt.setString(2, Contanto);
            stmt.setInt(3, idEmpresa);
            stmt.setString(4, nombreEmpresa);
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
        tutorEmpresa selected = table_TutorEmpresa.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Empresa");
            alert.setHeaderText("Se eliminará la empresa " + selected.getNombre());
            alert.setContentText("¿Estás seguro?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                eliminarEmpresa(selected.getId());
                table_TutorEmpresa.getItems().remove(selected);
            }
        }
    }

    private void eliminarEmpresa(int idtutorEmpresa) {
        String sql = "DELETE FROM tutor_empresa WHERE Id_Tutor = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idtutorEmpresa);
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
            Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            idTutor.setCellValueFactory(new PropertyValueFactory<>("Id_Tutor"));
            Cointacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));
            idEmpresa.setCellValueFactory(new PropertyValueFactory<>("Id_Empresa"));
            nombreEmpresa.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
            cargarTablaTutorEmpresa();
            table_TutorEmpresa.setItems(tutor);
            idTutor.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
            Nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
            Cointacto.setCellValueFactory(cellData -> cellData.getValue().contactoProperty());
            table_TutorEmpresa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                detallePanel.setVisible(newValue != null);
                if (newValue != null) {
                    cargar(newValue);
                }
            });
        }


    public void cargar(tutorEmpresa tutorEmpresaSeleccionado){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tutor_EmpresaSelected.fxml"));
            Pane pane = loader.load();
            tutorEmpresaSelectedController tutorEmpresaSelectedController = loader.getController();
            tutorEmpresaSelectedController.obtenertutorEmpresa(tutorEmpresaSeleccionado);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void cargarTablaTutorEmpresa(){
        tutor.clear();
        String sql = "SELECT * FROM tutor_empresa";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tutor.add(new tutorEmpresa(
                        rs.getInt("Id_Tutor"),
                        rs.getString("Nombre"),
                        rs.getString("Contacto"),
                        rs.getInt("Id_Empresa"),
                        rs.getString("nombreEmpresa")
                ));
            }
            table_TutorEmpresa.setItems(tutor);
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

    public TableColumn<tutorEmpresa, String> getCointacto() {
        return Cointacto;
    }

    public void setCointacto(TableColumn<tutorEmpresa, String> cointacto) {
        Cointacto = cointacto;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TableColumn<tutorEmpresa, String> getNombre() {
        return Nombre;
    }

    public void setNombre(TableColumn<tutorEmpresa, String> nombre) {
        Nombre = nombre;
    }

    public TableColumn<tutorEmpresa, Integer> getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(TableColumn<tutorEmpresa, Integer> idTutor) {
        this.idTutor = idTutor;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<tutorEmpresa> getTable_TutorEmpresa() {
        return table_TutorEmpresa;
    }

    public void setTable_TutorEmpresa(TableView<tutorEmpresa> table_TutorEmpresa) {
        this.table_TutorEmpresa = table_TutorEmpresa;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

    public ObservableList<tutorEmpresa> getTutor() {
        return tutor;
    }

    public void setTutor(ObservableList<tutorEmpresa> tutor) {
        this.tutor = tutor;
    }

    public TableColumn<tutorEmpresa, String> getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(TableColumn<tutorEmpresa, String> nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public TableColumn<tutorEmpresa, Integer> getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(TableColumn<tutorEmpresa, Integer> idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Stage getTutorEmpresaStage() {
        return tutorEmpresaStage;
    }

    public void setTutorEmpresaStage(Stage tutorEmpresaStage) {
        this.tutorEmpresaStage = tutorEmpresaStage;
    }
}
