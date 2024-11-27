package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class EmpresaCreateController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Correo;

    @FXML
    private TextField Especialidad;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private CheckBox Participa;

    @FXML
    private Spinner<Integer> Plazas;

    @FXML
    private TextField Telefono;

    @FXML
    private BorderPane root;

    @FXML
    private TextField Direccion;

    private SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;

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
        boolean telefonoValido = !Telefono.getText().isBlank() && Telefono.getText().matches("^\\+?\\d{9,15}$");
        boolean correoValido = !Correo.getText().isBlank() && Correo.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        boolean especialidadValida = !Especialidad.getText().isBlank() && Especialidad.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$");
        boolean direcionValida = !Direccion.getText().isBlank() && Direccion.getText().matches("^[a-zA-Z0-9\\s,.-]+$");
        return nombreValido && correoValido && telefonoValido && especialidadValida && direcionValida;
    }

    private void cerrar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    private void limpiar() {
        Nombre.setText("");
        Correo.setText("");
        Especialidad.setText("");
        Participa.setSelected(false);
        Telefono.setText("");
        Direccion.setText("");
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        Plazas.setValueFactory(valueFactory);
        Plazas.setEditable(true);
        validarCampo(Nombre, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir el Nombre correctamente");
        validarCampo(Correo, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$","Introducir el Correo correctamente");
        validarCampo(Telefono, "^\\+?\\d{9,15}$","Introducir el Telefono correctamente");
        validarCampo(Direccion ,"^[a-zA-Z0-9\\s,.-]+$","Introducir la Dirección correctamente");

        Participa.selectedProperty().addListener((observable, oldValue, newValue) -> {
            Plazas.setDisable(!newValue);
            valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(newValue ? 1 : 0, 100, newValue ? 1 : 0);
            Plazas.setValueFactory(valueFactory);
        });
        Plazas.setDisable(!Participa.isSelected());
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Introduzca correctamente los campos requeridos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public EmpresaCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EmpresaCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TextField getDireccion() {
        return Direccion;
    }

    public void setDireccion(TextField direccion) {
        Direccion = direccion;
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

    public TextField getCorreo() {
        return Correo;
    }

    public void setCorreo(TextField correo) {
        Correo = correo;
    }

    public TextField getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(TextField especialidad) {
        Especialidad = especialidad;
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

    public CheckBox getParticipa() {
        return Participa;
    }

    public void setParticipa(CheckBox participa) {
        Participa = participa;
    }

    public Spinner<Integer> getPlazas() {
        return Plazas;
    }

    public void setPlazas(Spinner<Integer> plazas) {
        Plazas = plazas;
    }

    public TextField getTelefono() {
        return Telefono;
    }

    public void setTelefono(TextField telefono) {
        Telefono = telefono;
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
