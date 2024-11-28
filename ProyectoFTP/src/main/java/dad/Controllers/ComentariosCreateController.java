package dad.Controllers;

import dad.Conexion.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class ComentariosCreateController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Comentario;

    @FXML
    private DatePicker Fecha;

    @FXML
    private ChoiceBox<Integer> IdEmpresa;

    @FXML
    private Button Limpiar;

    @FXML
    private BorderPane root;

    private boolean confirmar = false;
    @FXML
    void onAñadirAction(ActionEvent event) {
        if (!validarCampos()){
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
        }
            confirmar = true;
            cerrar();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Introduzca correctamente los campos requeridos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }



    private void validarCampo(TextField textField, String regex, String errorMessage) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(regex)) {
                textField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Tooltip tooltip = new Tooltip(errorMessage);
                Tooltip.install(textField, tooltip);
            } else {
                textField.setStyle(null);
                Tooltip.uninstall(textField, null);
            }
        });
    }

    private boolean validarCampos(){
        boolean comentarioValido = !Comentario.getText().isBlank() && Comentario.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        return comentarioValido;
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    @FXML
    void onLImpiarAction(ActionEvent event) {
        Comentario.setText("");
        Fecha.setValue(null);
        IdEmpresa.setValue(null);
    }

    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }


    public void cargarIdEmpresa(){
        String sql = "SELECT Id_Empresa FROM empresas WHERE Participa=1";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ObservableList<Integer> empresas = FXCollections.observableArrayList();
            while (rs.next()) {
                empresas.add(rs.getInt("Id_Empresa"));
            }
            IdEmpresa.setItems(empresas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ComentariosCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ComentarioCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarIdEmpresa();
        validarCampo(Comentario,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir el Comentario correctamente");
    }

    public Button getAñadir() {
        return Añadir;
    }

    public void setAñadir(Button añadir) {
        Añadir = añadir;
    }

    public Button getCancelar() {
        return Cancelar;
    }

    public void setCancelar(Button cancelar) {
        Cancelar = cancelar;
    }

    public TextField getComentario() {
        return Comentario;
    }

    public void setComentario(TextField comentario) {
        Comentario = comentario;
    }

    public DatePicker getFecha() {
        return Fecha;
    }

    public void setFecha(DatePicker fecha) {
        Fecha = fecha;
    }

    public ChoiceBox<Integer> getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(ChoiceBox<Integer> idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public Button getLimpiar() {
        return Limpiar;
    }

    public void setLimpiar(Button limpiar) {
        Limpiar = limpiar;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
