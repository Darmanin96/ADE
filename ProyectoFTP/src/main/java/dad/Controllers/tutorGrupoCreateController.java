package dad.Controllers;

import dad.Conexion.*;
import dad.Model.Curso;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class tutorGrupoCreateController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private TextField nombre;

    @FXML
    private ChoiceBox<Integer> IdAlumno;

    @FXML
    private ChoiceBox<Curso> grupoComboBox;

    @FXML
    private Button actualizar;

    @FXML
    private Button cancelar;

    private boolean confirmar = false;

    public tutorGrupoCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tutor_GrupoCreate.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onActualizarAction() {
        if (!validarCampos()) {
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
            return;
        }

        confirmar = true;
        cerrar();
    }

    @FXML
    void onCancelarAction() {
        confirmar = false;
        cerrar();
    }

    @FXML
    void onLimpiarAction() {
        limpiarCampos();
    }

    private void cerrar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
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
        boolean nombreValido = !nombre.getText().isBlank() && nombre.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        //boolean idAlumnoValido = IdAlumno.getValue() != null;
        boolean grupoValido = grupoComboBox.getValue() != null;
        return nombreValido && /*idAlumnoValido &&*/ grupoValido;
    }

    private void limpiarCampos() {
        nombre.setText("");
        //IdAlumno.getSelectionModel().clearSelection();
        grupoComboBox.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Introduzca correctamente los campos requeridos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public BorderPane getRoot() {
        return root;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public TextField getNombre() {
        return nombre;
    }

    public ChoiceBox<Integer> getIdAlumno() {
        return IdAlumno;
    }

    public Curso getGrupoSeleccionado() {
        return grupoComboBox.getValue(); // Devuelve el curso seleccionado en el ChoiceBox
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //cargarIdAlumno();
        cargarCurso();
        validarCampo(nombre,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$","Introducir el Nombre correctamente");
    }
/*
    private void cargarIdAlumno() {
        String sql = "SELECT Id_Alumno FROM alumno";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Integer> alumnos = FXCollections.observableArrayList();
            while (rs.next()) {
                alumnos.add(rs.getInt("Id_Alumno"));
            }
            IdAlumno.setItems(alumnos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    private void cargarCurso() {
        // Agregar los valores del enum Curso al ChoiceBox
        grupoComboBox.getItems().setAll(Curso.values());

        // Usar un conversor para mostrar los cursos de forma legible
        grupoComboBox.setConverter(new StringConverter<Curso>() {
            @Override
            public String toString(Curso curso) {
                if (curso == null) {
                    return null;
                }
                // Reemplazar guiones bajos con espacios para una visualización más legible
                return curso.name().replace('_', ' ');
            }

            @Override
            public Curso fromString(String string) {
                return Curso.valueOf(string.replace(' ', '_')); // Convierte el texto a un valor del enum
            }
        });
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public void setNombre(TextField nombre) {
        this.nombre = nombre;
    }

    public void setIdAlumno(ChoiceBox<Integer> idAlumno) {
        IdAlumno = idAlumno;
    }

    public ChoiceBox<Curso> getGrupoComboBox() {
        return grupoComboBox;
    }

    public void setGrupoComboBox(ChoiceBox<Curso> grupoComboBox) {
        this.grupoComboBox = grupoComboBox;
    }

    public Button getActualizar() {
        return actualizar;
    }

    public void setActualizar(Button actualizar) {
        this.actualizar = actualizar;
    }

    public Button getCancelar() {
        return cancelar;
    }

    public void setCancelar(Button cancelar) {
        this.cancelar = cancelar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
