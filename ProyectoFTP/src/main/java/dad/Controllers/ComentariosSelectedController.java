package dad.Controllers;

import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.net.*;
import java.time.*;
import java.util.*;

public class ComentariosSelectedController implements Initializable {

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

    private Comentarios comentariosSelected;

    @FXML
    void onAñadirAction(ActionEvent event) {
        if (!validarCampos()){
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
        }
        LocalDate fecha = Fecha.getValue();
        String comentario = Comentario.getText();
        String sql = "UPDATE ";

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
        boolean comentariosValido = !Comentario.getText().isBlank() && Comentario.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        return comentariosValido;
    }


    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onLImpiarAction(ActionEvent event) {
        Comentario.setText("");
        Fecha.setValue(null);
        IdEmpresa.setValue(null);
    }

    public void obtenerComentario(Comentarios comentarios) {
        if (comentarios != null) {
            this.comentariosSelected = comentarios;
            Comentario.textProperty().bindBidirectional(comentariosSelected.comentarioProperty());
            Fecha.valueProperty().bindBidirectional(comentarios.fechaComentarioProperty());
            IdEmpresa.setValue(comentarios.getIdEmpresa());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            validarCampo(Comentario,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el Comentario correctamente.");
    }
}
