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

public class ComentariosController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private TableColumn<Comentarios, String> Comentario;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<Comentarios, LocalDate> Fecha;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private TableColumn<Comentarios, Integer> idComentario;

    @FXML
    private TableColumn<Comentarios, Integer> idEmpresa;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Comentarios> tableComentarios;

    private ObservableList<Comentarios> comentariosList = FXCollections.observableArrayList();


    @FXML
    void onAñadirAction(ActionEvent event) {
        ComentariosCreateController comentariosCreateController = new ComentariosCreateController();
        Stage stage = new Stage();
        stage.setTitle("Nuevos Comentarios");
        stage.setScene(new Scene(comentariosCreateController.getRoot()));
        stage.showAndWait();
        if (comentariosCreateController.isConfirmar()){
            String comentarios = comentariosCreateController.getComentario().getText();
            LocalDate fecha = comentariosCreateController.getFecha().getValue();
            String id = comentariosCreateController.getIdEmpresa().getId();
            Integer idEmpresa = Integer.parseInt(id);
            int nuevoId = insertarComentarios(comentarios,fecha,idEmpresa);
            if (nuevoId > 0){
                cargarTablaComentarios();
            }else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("No se pudo añadir la visita.");
                alerta.setContentText("Hubo un problema al insertar la visita en la base de datos.");
                alerta.showAndWait();
            }
        }
    }

    private int insertarComentarios(String comentarios, LocalDate fecha, Integer idEmpresa) {
        String sql = "INSERT INTO comentarios_empresa(Comentario,Fecha_Comentario,Id_Empresa) VALUES(?,?,?)";

        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, comentarios);
            stmt.setString(2, fecha.toString());
            stmt.setInt(3, idEmpresa);
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
        return  -1;
    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    private void eliminarVisita(int visitaId){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Comentario.setCellValueFactory(new PropertyValueFactory<>("Comentario"));
        Fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha_Comentario"));
        idEmpresa.setCellValueFactory(new PropertyValueFactory<>("Id_Empresa"));
        cargarTablaComentarios();
        tableComentarios.setItems(comentariosList);
        Comentario.setCellValueFactory(cellData -> cellData.getValue().comentarioProperty());
        Fecha.setCellValueFactory(cellData -> cellData.getValue().fechaComentarioProperty());
        tableComentarios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null) {
                cargar(newValue);
            }
        });
    }

    public void cargar(Comentarios comentarioSeleccionado){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectedView.fxml"));
            Pane pane = loader.load();
            ComentariosCreateController controller = loader.getController();

            alumnSelectedController.obtenerAlumno(comentarioSeleccionado);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void cargarTablaComentarios(){
        comentariosList.clear();
        String sql = "SELECT * FROM comentarios_empresa";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                comentariosList.add(new Comentarios(
                        rs.getInt("Id_Comentario"),
                        rs.getString("Comentario"),
                        rs.getDate("Fecha_Comentario").toLocalDate(),
                        rs.getInt("Id_Empresa")
                ));
            }
            tableComentarios.setItems(comentariosList);
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

    public TableColumn<Comentarios, String> getComentario() {
        return Comentario;
    }

    public void setComentario(TableColumn<Comentarios, String> comentario) {
        Comentario = comentario;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TableColumn<Comentarios, LocalDate> getFecha() {
        return Fecha;
    }

    public void setFecha(TableColumn<Comentarios, LocalDate> fecha) {
        Fecha = fecha;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

    public TableColumn<Comentarios, Integer> getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(TableColumn<Comentarios, Integer> idComentario) {
        this.idComentario = idComentario;
    }

    public TableColumn<Comentarios, Integer> getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(TableColumn<Comentarios, Integer> idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<Comentarios> getTableComentarios() {
        return tableComentarios;
    }

    public void setTableComentarios(TableView<Comentarios> tableComentarios) {
        this.tableComentarios = tableComentarios;
    }
}
