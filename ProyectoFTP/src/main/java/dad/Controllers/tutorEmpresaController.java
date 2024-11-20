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
    private TableView<tutorEmpresa> table_TutorEmpresa;

    private ObservableList<tutorEmpresa> tutor = FXCollections.observableArrayList();

    @FXML
    private AnchorPane detallePanel;

    @FXML
    void onAñadirAction(ActionEvent event) {
        tutor_EmpresaCreateController tutor_EmpresaCreateController = new tutor_EmpresaCreateController();
        Stage stage = new Stage();
        stage.setTitle("Crear Tutor de Empresa");
        stage.setScene(new Scene(tutor_EmpresaCreateController.getRoot()));
        stage.showAndWait();
        if (tutor_EmpresaCreateController.isConfirmar()){
            String Nombre = tutor_EmpresaCreateController.getNombre().getText();
            String Contanto = tutor_EmpresaCreateController.getNombre().getText();
            int nuevoId = insertarTutorEmpresa(Nombre, Contanto);
            if (nuevoId > 0){
                cargarTablaTutorEmpresa();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se puede añadir el tutor");
                alert.setContentText("Hubo un problema al insertar el tutor en la base de datos");
                alert.showAndWait();
            }
        }
    }

    private int insertarTutorEmpresa(String Nombre, String Contanto){
        String sql = "INSERT INTO tutor_empresa(Nombre,Contacto) VALUES(?,?)";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, Nombre);
            stmt.setString(2, Contanto);
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
        String sql = "DELETE FROM tutorEmpresa WHERE idTutorEmpresa = ?";
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
                        rs.getString("Contacto")
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
}
