package dad.Controllers;

import dad.Conexion.*;
import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.net.*;
import java.sql.*;
import java.util.*;

public class tutorEmpresaSelectedController implements Initializable {


    @FXML
    private TextField Contacto;

    @FXML
    private TextField Nombre;

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private Button Limpiar;

    @FXML
    private BorderPane root;


    private tutorEmpresa tutorEmpresa;

    @FXML
    void onActualizarAction(ActionEvent event) {
        if (!validarCampos()){
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
        }
        String nombre = Nombre.getText();
        String contacto = Contacto.getText();
        String sql = "UPDATE tutor_empresa SET Nombre = ?, Contacto = ? WHERE Id_Tutor  = ?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(3,tutorEmpresa.getId());
                stmt.setString(1,nombre);
                stmt.setString(2,contacto);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El tutor ha sido actualizado con éxito.");
            } else {
                System.err.println("No se encontró un tutor con ese ID.");
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar el tutor: " + e.getMessage());
        }
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
        boolean nombreValido = !Nombre.getText().isBlank() && Nombre.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        return nombreValido;
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validarCampo(Nombre, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir el Nombre correctamente");

    }

    private void limpiar(){
        Nombre.setText("");
        Contacto.setText("");
    }


    public void obtenertutorEmpresa(tutorEmpresa tutor){
        if (tutor != null){
            this.tutorEmpresa = tutor;
            Nombre.textProperty().bindBidirectional(tutor.nombreProperty());
            Contacto.textProperty().bindBidirectional(tutor.contactoProperty());
        }

    }



    public Button getCancelar() {
        return Cancelar;
    }

    public void setCancelar(Button cancelar) {
        Cancelar = cancelar;
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

    public TextField getContacto() {
        return Contacto;
    }

    public void setContacto(TextField contacto) {
        Contacto = contacto;
    }

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
    }

    public Button getActualizar() {
        return Actualizar;
    }

    public void setActualizar(Button actualizar) {
        Actualizar = actualizar;
    }
}
