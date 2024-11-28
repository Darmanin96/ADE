package dad.Controllers;

import dad.Model.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class AlumnoCreateController implements Initializable {

    @FXML
    private TextField Apellidos;

    @FXML
    private TextField Contactos;

    @FXML
    private ChoiceBox<dad.Model.Curso> Curso;

    @FXML
    private TextField Nombre;

    @FXML
    private CheckBox Pendientes;

    @FXML
    private BorderPane root;

    @FXML
    private TextField tutorEmpresa;

    @FXML
    private TextField tutorGrupo;

    @FXML
    private Button limpiar;

    private boolean confirmar = false;

    @FXML
    void onAñadirAAction(ActionEvent event) {
        if (!validarCampos()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error en los datos");
            alerta.setHeaderText("Hay errores en los campos");
            alerta.setContentText("Por favor, corrige los errores antes de continuar.");
            alerta.showAndWait();
            return;
        }

        confirmar = true;
        cerrar();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Introduzca correctamente el campo contacto");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }

    private void limpiar() {
        Nombre.setText("");
        Apellidos.setText("");
        Contactos.setText("");
        Curso.getItems().clear();
        Pendientes.setSelected(false);
        tutorEmpresa.setText("");
        tutorGrupo.setText("");
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Curso.setItems(FXCollections.observableArrayList(dad.Model.Curso.values()));
        validarCampo(Nombre, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el Nombre correctamente.");
        validarCampo(Apellidos, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca los apellidos correctamente.");
        validarCampo(tutorGrupo, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el tutor grupo correctamente.");
        validarCampo(tutorEmpresa, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el tutor empresa correctamente.");
        validarCampo(Contactos, "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$" , "Introduzca el contacto correctamente.");

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

    private boolean validarCampos() {
        boolean nombreValido = !Nombre.getText().isBlank() && Nombre.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        boolean apellidosValidos = !Apellidos.getText().isBlank() && Apellidos.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        boolean tutorGrupoValido = !tutorGrupo.getText().isBlank() && tutorGrupo.getText().matches("\"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$\"");
        boolean tutorEmpresaValido = !tutorEmpresa.getText().isBlank() && tutorEmpresa.getText().matches("\"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$\"");
        boolean contactoValido = (
                !Contactos.getText().isBlank() && Contactos.getText().matches("^\\+?[0-9]{1,4}?[\\s.-]?\\(?[0-9]{1,4}\\)?[\\s.-]?[0-9]{1,4}[\\s.-]?[0-9]{1,9}$")) ||
                (!Contactos.getText().isBlank() && Contactos.getText().matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));
        return  nombreValido && apellidosValidos && tutorGrupoValido && tutorEmpresaValido && contactoValido;
    }

    public AlumnoCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnoCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters y setters
    public TextField getApellidos() {
        return Apellidos;
    }

    public void setApellidos(TextField apellidos) {
        Apellidos = apellidos;
    }

    public TextField getContactos() {
        return Contactos;
    }

    public void setContactos(TextField contactos) {
        Contactos = contactos;
    }

    public ChoiceBox<dad.Model.Curso> getCurso() {
        return Curso;
    }

    public void setCurso(ChoiceBox<dad.Model.Curso> curso) {
        Curso = curso;
    }

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
    }

    public CheckBox getPendientes() {
        return Pendientes;
    }

    public void setPendientes(CheckBox pendientes) {
        Pendientes = pendientes;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TextField getTutorEmpresa() {
        return tutorEmpresa;
    }

    public void setTutorEmpresa(TextField tutorEmpresa) {
        this.tutorEmpresa = tutorEmpresa;
    }

    public TextField getTutorGrupo() {
        return tutorGrupo;
    }

    public void setTutorGrupo(TextField tutorGrupo) {
        this.tutorGrupo = tutorGrupo;
    }

    public Button getLimpiar() {
        return limpiar;
    }

    public void setLimpiar(Button limpiar) {
        this.limpiar = limpiar;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
