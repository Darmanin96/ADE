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

public class EmpresaSelectedController implements Initializable {

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Correo;

    @FXML
    private TextField Direccion;

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

    private Empresa empresa;


    @FXML
    void onActualizarAction(ActionEvent event) {
        if (!validarCampos()){
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
        }
        String nombre = Nombre.getText();
        String correo = Correo.getText();
        String direccion = Direccion.getText();
        String especialidad = Especialidad.getText();
        String telefono = Telefono.getText();
        Integer plazasReal = Plazas.getValue();
        Boolean participa = Participa.isSelected();
        if (nombre.isEmpty() || correo.isEmpty() || direccion.isEmpty() || especialidad.isEmpty() || telefono.isEmpty() || plazasReal == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText("Hay campos vacíos");
            alert.setContentText("Por favor, rellena todos los campos.");
            alert.showAndWait();
            return;
        }

        String sql = "UPDATE empresas SET Nombre=?, Direccion=?, Especialidad=?, Telefono=?, Correo=?, Participa=?, Plazas=? WHERE Id_Empresa=?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setString(3, especialidad);
            stmt.setString(4, telefono);
            stmt.setString(5, correo);
            stmt.setBoolean(6, participa);
            stmt.setInt(7, plazasReal);
            stmt.setInt(8, empresa.getId_Empresa());

            // Ejecutar la actualización
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La empresa ha sido actualizada con éxito.");
            } else {
                System.err.println("No se encontró una empresa con ese ID.");
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar la empresa: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Introduzca correctamente los campos requeridos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    private boolean validarCampos() {
        boolean nombreValido = !Nombre.getText().isBlank() && Nombre.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        boolean direccionValido = !Direccion.getText().isBlank() && Direccion.getText().matches("^[a-zA-Z0-9\\s,.-]+$");
        boolean telefonoValido = !Telefono.getText().isBlank() && Telefono.getText().matches("^[0-9]+$");
        boolean correoValido = !Correo.getText().isBlank() && Correo.getText().matches("^[a-zA-Z0-9\\s,.-]+$");
        boolean especialidadValido = !Especialidad.getText().isBlank() && Especialidad.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        return  nombreValido && direccionValido && telefonoValido && correoValido && especialidadValido;
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

    private void limpiar() {
        // Limpiar campos de texto
        Nombre.setText("");
        Correo.setText("");
        Direccion.setText("");
        Especialidad.setText("");
        Telefono.setText("");
        Participa.setSelected(false);
        if (!Participa.isSelected()) {
            Plazas.getValueFactory().setValue(null);
        } else {
            Plazas.getValueFactory().setValue(1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validarCampo(Nombre,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir el Nombre correctamente");
        validarCampo(Direccion,"^[a-zA-Z0-9\\s,.-]+$","Introducir la Direccion correctamente");
        validarCampo(Correo,"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$","Introducir el Correo correctamente");
        validarCampo(Telefono,"^\\+?\\d{9,15}$","Introducir el Telefono correctamente");
        validarCampo(Especialidad,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir la especialidad correctamente");
        SpinnerValueFactory<Integer> defaultValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        Plazas.setValueFactory(defaultValueFactory);
        Participa.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                SpinnerValueFactory<Integer> valueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, Plazas.getValue() == null ? 1 : Plazas.getValue());
                Plazas.setValueFactory(valueFactory);
                Plazas.setDisable(false);
            } else {
                SpinnerValueFactory<Integer> valueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
                Plazas.setValueFactory(valueFactory);
                Plazas.setDisable(true);
            }
        });
    }


    public void obtenerEmpresa(Empresa empresa) {
        if (empresa != null) {
            this.empresa = empresa;
            Nombre.textProperty().bindBidirectional(empresa.nombreProperty());
            Direccion.textProperty().bindBidirectional(empresa.direccionProperty());
            Correo.textProperty().bindBidirectional(empresa.correoProperty());
            Especialidad.textProperty().bindBidirectional(empresa.especialidadProperty());
            Telefono.textProperty().bindBidirectional(empresa.telefonoProperty());
            Participa.selectedProperty().bindBidirectional(empresa.participaProperty());
            SpinnerValueFactory<Integer> valueFactory;
            if (empresa.isParticipa()) {
                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, Math.max(empresa.getPlazas(), 1));
            } else {
                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
            }
            Plazas.setValueFactory(valueFactory);
            Plazas.setDisable(!empresa.isParticipa());
        }
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

    public TextField getCorreo() {
        return Correo;
    }

    public void setCorreo(TextField correo) {
        Correo = correo;
    }

    public TextField getDireccion() {
        return Direccion;
    }

    public void setDireccion(TextField direccion) {
        Direccion = direccion;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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


}
