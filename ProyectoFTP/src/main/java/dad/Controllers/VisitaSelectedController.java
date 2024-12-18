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

public class VisitaSelectedController implements Initializable {

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private DatePicker Date;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Observaciones;

    @FXML
    private BorderPane root;

    private Visita visitaSeleccionada;

    @FXML
    void onActualizarAction(ActionEvent event) {
        if (!validarCampos()){
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
        }
        String fecha = Date.getValue().toString();
        String observaciones = Observaciones.getText();
        String sql = "UPDATE visitas SET Fecha_Visitas =?, Observaciones=? WHERE Id_Visita = ?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, fecha);
            stmt.setString(2, observaciones);
            stmt.setInt(3,visitaSeleccionada.getIdVisita() );
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El alumno ha sido actualizado con éxito.");
            } else {
                System.err.println("No se encontró un alumno con ese ID.");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el alumno: " + e.getMessage());
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
        boolean observacionesValido = !Observaciones.getText().isBlank() && Observaciones.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        return observacionesValido;
    }


    public void obtenerVisita(Visita visita) {
        this.visitaSeleccionada = visita;
        Observaciones.textProperty().bindBidirectional(visita.observacionesProperty());
        Date.valueProperty().bindBidirectional(visita.fechaProperty());

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
        Date.setValue(null);
        Observaciones.setText("");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validarCampo(Observaciones, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir la observación correctamente");

    }

    public Button getActualizar() {
        return Actualizar;
    }

    public void setActualizar(Button actualizar) {
        Actualizar = actualizar;
    }

    public Button getCancelar() {
        return Cancelar;
    }

    public void setCancelar(Button cancelar) {
        Cancelar = cancelar;
    }

    public DatePicker getDate() {
        return Date;
    }

    public void setDate(DatePicker date) {
        Date = date;
    }

    public Button getLimpiar() {
        return Limpiar;
    }

    public void setLimpiar(Button limpiar) {
        Limpiar = limpiar;
    }

    public TextField getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(TextField observaciones) {
        Observaciones = observaciones;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
