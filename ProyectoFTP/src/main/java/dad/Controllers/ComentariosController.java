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
    private TableColumn<Comentarios, String> nombreEmpresa;

    @FXML
    private TableColumn<Comentarios, Integer> idEmpresa;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Comentarios> tableComentarios;

    private ObservableList<Comentarios> comentariosList = FXCollections.observableArrayList();

    private Stage comentariosCreateStage;


    @FXML
    void onAñadirAction(ActionEvent event) {
        if (comentariosCreateStage == null) {
            ComentariosCreateController comentariosCreateController = new ComentariosCreateController();
            comentariosCreateStage = new Stage();
            comentariosCreateStage.setTitle("Crear Comentario");
            comentariosCreateStage.initModality(Modality.APPLICATION_MODAL);
            comentariosCreateStage.setScene(new Scene(comentariosCreateController.getRoot()));
            comentariosCreateStage.setOnHidden(e -> {
                comentariosCreateStage = null;
                if (comentariosCreateController.isConfirmar()) {
                    String comentario = comentariosCreateController.getComentario().getText();
                    LocalDate fecha = comentariosCreateController.getFecha().getValue();
                    Integer idEmpresaVerdad = comentariosCreateController.getIdEmpresa().getValue();
                    String EmpresaNombre = obtenerNombreEmpresa(idEmpresaVerdad);
                    int nuevoId = insertarComentarios(comentario,fecha,idEmpresaVerdad,EmpresaNombre);
                    if (nuevoId > 0) {
                        cargarTablaComentarios();
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error");
                        alerta.setHeaderText("No se pudo añadir el alumno.");
                        alerta.setContentText("Hubo un problema al insertar el alumno en la base de datos.");
                        alerta.showAndWait();
                    }
                }
            });
            comentariosCreateStage.show();
        }else {
            comentariosCreateStage.requestFocus();
        }
    }

    private String obtenerNombreEmpresa(Integer idEmpresa){
        String sql = "SELECT Nombre FROM empresas WHERE Id_Empresa = ?";
        String nombreEmpresa = "";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nombreEmpresa = rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreEmpresa;
    }

    private int insertarComentarios(String comentarios, LocalDate fecha, Integer idEmpresa, String nombreEmpresa) {
        String sql = "INSERT INTO comentarios_empresa(Comentario, Fecha_Comentario, Id_Empresa, nombreEmpresa) VALUES(?, ?, ?, ?)";

        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, comentarios);
            stmt.setString(2, fecha.toString());
            stmt.setInt(3, idEmpresa);
            stmt.setString(4, nombreEmpresa); // Incluye el valor de nombreEmpresa
            int i = stmt.executeUpdate();
            if (i > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarError("Error al insertar comentario", "Hubo un problema al insertar el comentario: " + e.getMessage());
        }
        return -1;
    }


    @FXML
    void onEliminarAction(ActionEvent event) {
        Comentarios comentarioSeleccionado = tableComentarios.getSelectionModel().getSelectedItem();
        if (comentarioSeleccionado != null) {
            eliminarComentario(comentarioSeleccionado.getIdComentario());
            cargarTablaComentarios();
        } else {
            mostrarError("No se seleccionó comentario", "Por favor, selecciona un comentario para eliminar.");
        }
    }

    private void eliminarComentario(int comentarioId) {
        String sql = "DELETE FROM comentarios_empresa WHERE Id_Comentario = ?";

        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, comentarioId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Comentario eliminado con éxito.");
            } else {
                mostrarError("Error", "No se encontró el comentario para eliminar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarError("Error al eliminar comentario", "Hubo un problema al eliminar el comentario: " + e.getMessage());
        }
    }

    private void mostrarError(String title, String content) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(title);
        alerta.setContentText(content);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idComentario.setCellValueFactory(new PropertyValueFactory<>("idComentario"));
        Comentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        Fecha.setCellValueFactory(new PropertyValueFactory<>("fechaComentario"));
        idEmpresa.setCellValueFactory(new PropertyValueFactory<>("idEmpresa"));
        nombreEmpresa.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
        cargarTablaComentarios();
        tableComentarios.setItems(comentariosList);
        tableComentarios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null) {
                cargar(newValue);
            }
        });
    }

    public void cargar(Comentarios comentarioSeleccionado) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ComentarioSelectedView.fxml"));
            Pane pane = loader.load();
            ComentariosSelectedController comentariosSelectedController = loader.getController();
            comentariosSelectedController.obtenerComentario(comentarioSeleccionado);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al cargar detalle", "Hubo un problema al cargar los detalles del comentario.");
        }
    }

    public void cargarTablaComentarios() {
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
                        rs.getInt("Id_Empresa"),
                        rs.getString("nombreEmpresa")
                ));
            }
            tableComentarios.setItems(comentariosList);
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarError("Error al cargar comentarios", "Hubo un problema al cargar los comentarios desde la base de datos.");
        }
    }
}
