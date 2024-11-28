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

public class tutor_EmpresaCreateController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Direccion;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private BorderPane root;

    @FXML
    private ChoiceBox<Integer> idEmpresas;

    private boolean confirmar = false;


    @FXML
    void onAñadirAction(ActionEvent event) {
        if (!validarCampos()) {
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
            return;
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

    public void cargarTablaIdEmpresa(){
        String sql = "SELECT Id_Empresa FROM empresas";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Integer> idEmpresa = FXCollections.observableArrayList();
            while (rs.next()) {
                idEmpresa.add(rs.getInt("Id_Empresa"));
            }
            idEmpresas.setItems(idEmpresa);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private boolean validarCampos(){
        boolean nombreValido = !Nombre.getText().isBlank() && Nombre.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        boolean DireccionValido = (
                !Direccion.getText().isBlank() && Direccion.getText().matches("^\\+?[0-9]{1,4}?[\\s.-]?\\(?[0-9]{1,4}\\)?[\\s.-]?[0-9]{1,4}[\\s.-]?[0-9]{1,9}$")) ||
                (!Direccion.getText().isBlank() && Direccion.getText().matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));

        return nombreValido && DireccionValido;
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    private void limpiar() {
        Nombre.setText("");
        Direccion.setText("");
    }

    private void cerrar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTablaIdEmpresa();
        validarCampo(Nombre,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el Nombre correctamente.");
        validarCampo(Direccion,
                "^\\+?[0-9]{1,4}?[\\s.-]?\\(?[0-9]{1,4}\\)?[\\s.-]?[0-9]{1,4}[\\s.-]?[0-9]{1,9}$|^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$",
                "Introduzca un contacto válido (correo electrónico o número de teléfono).");
    }




    public tutor_EmpresaCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tutor_EmpresaCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters y setters
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

    public TextField getDireccion() {
        return Direccion;
    }

    public void setDireccion(TextField direccion) {
        Direccion = direccion;
    }

    public Button getLimpiar() {
        return Limpiar;
    }

    public void setLimpiar(Button limpiar) {
        Limpiar = limpiar;
    }

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
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

    public ChoiceBox<Integer> getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(ChoiceBox<Integer> idEmpresas) {
        this.idEmpresas = idEmpresas;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
