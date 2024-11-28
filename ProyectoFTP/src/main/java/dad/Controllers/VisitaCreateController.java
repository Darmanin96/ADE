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

public class VisitaCreateController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private Button Limpiar;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<Integer> idAlumno;

    @FXML
    private ChoiceBox<Integer> idTutor;

    @FXML
    private TextField observaciones;

    @FXML
    private BorderPane root;

    private boolean confirmar= false;


    @FXML
    void onAñadirAction(ActionEvent event) {
        if (!validarCampos()){
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
            return;
        }
        confirmar= true;
        cerrar();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Introduzca correctamente los campos requeridos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiar(){
        observaciones.setText("");
        idAlumno.getSelectionModel().clearSelection();
        idTutor.getSelectionModel().clearSelection();

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
        boolean observacionesValidar = !observaciones.getText().isBlank() && observaciones.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$") ;
        return  observacionesValidar;
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        limpiar();
    }

    public void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarIdAlumno();
        cargarIdTutor();
        validarCampo(observaciones,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir las observaciones correctamnete");

    }





    private void cargarIdAlumno(){
        String sql = "SELECT Id_Alumno FROM alumno";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Integer> alumnos = FXCollections.observableArrayList();
            while (rs.next()) {
                alumnos.add(rs.getInt("Id_Alumno"));
            }
            idAlumno.setItems(alumnos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void cargarIdTutor(){
        String sql = "SELECT Id_Tutor FROM tutor_grupo";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Integer> alumnos = FXCollections.observableArrayList();
            while (rs.next()) {
                alumnos.add(rs.getInt("Id_Tutor"));
            }
            idTutor.setItems(alumnos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VisitaCreateController(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VisitaCreate.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public Button getLimpiar() {
        return Limpiar;
    }

    public void setLimpiar(Button limpiar) {
        Limpiar = limpiar;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }


    public ChoiceBox<Integer> getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(ChoiceBox<Integer> idAlumno) {
        this.idAlumno = idAlumno;
    }

    public ChoiceBox<Integer> getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(ChoiceBox<Integer> idTutor) {
        this.idTutor = idTutor;
    }

    public TextField getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(TextField observaciones) {
        this.observaciones = observaciones;
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
