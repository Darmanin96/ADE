package dad.Controllers;

import dad.Conexion.*;
import dad.Model.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.net.*;
import java.sql.*;
import java.util.*;

public class AlumnSelectedController implements Initializable {

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

    private Alumno alumnoSeleccionado;

    @FXML
    private AnchorPane detallePanel;


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
        boolean apellidosValido = !Apellidos.getText().isBlank() && Apellidos.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        boolean tutorEmpresaValido = !tutorEmpresa.getText().isBlank() && tutorEmpresa.getText().matches("^[a-zA-Z ]+$");
        boolean tutorGrupoValido = !tutorGrupo.getText().isBlank() && tutorGrupo.getText().matches("^[a-zA-Z ]+$");

        return nombreValido && apellidosValido && tutorEmpresaValido && tutorGrupoValido;
    }

    // Acción de actualizar
    @FXML
    void onActionActualizar(ActionEvent event) {
        if (!validarCampos()) {
            mostrarAlerta("Campos inválidos", "Por favor corrige los errores antes de continuar.");
            return;
        }
        actualizar();
    }

    private void actualizar() {
        String nombre = Nombre.getText();
        String apellidos = Apellidos.getText();
        String tutorEmp = tutorEmpresa.getText();
        String tutorGrp = tutorGrupo.getText();
        boolean pendientes = Pendientes.isSelected();
        String contacto = Contactos.getText();
        String programa = Curso.getValue() != null ? Curso.getValue().name() : null;
        String sql = "UPDATE alumno " +
                "SET Nombre = ?, Apellidos = ?, Tutor_empresa = ?, Tutor_grupo = ?, Pendiente = ?, Contacto = ?, Programa = ? " +
                "WHERE Id_Alumno = ?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, tutorEmp);
            stmt.setString(4, tutorGrp);
            stmt.setBoolean(5, pendientes);
            stmt.setString(6, contacto);
            stmt.setString(7, programa);
            stmt.setInt(8, alumnoSeleccionado.getId_Alumno());
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

    // Acción de cancelar
    @FXML
    void onActionCancelar(ActionEvent event) {
        cerrar();
    }

    // Acción de limpiar
    @FXML
    void onActionLimpiar(ActionEvent event) {
        limpiar();
    }

    private void cerrar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
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


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Introduzca correctamente los campos requeridos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Inicialización de la clase
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      validarCampo(Nombre,"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el Nombre correctamente.");
        validarCampo(Apellidos, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca los apellidos correctamente.");
        validarCampo(tutorGrupo, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el tutor grupo correctamente.");
        validarCampo(tutorEmpresa, "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", "Introduzca el tutor empresa correctamente.");
    }

    public void obtenerAlumno(Alumno alumno) {
        if (alumno != null) {
            this.alumnoSeleccionado = alumno;
            Nombre.textProperty().bindBidirectional(alumno.nombreProperty());
            Apellidos.textProperty().bindBidirectional(alumno.apellidoProperty());
            tutorEmpresa.textProperty().bindBidirectional(alumno.tutor_empresaProperty());
            tutorGrupo.textProperty().bindBidirectional(alumno.tutor_grupoProperty());
            Pendientes.selectedProperty().bindBidirectional(alumno.pendientesProperty());
            Contactos.textProperty().bindBidirectional(alumno.contactoProperty());
            Curso.setItems(FXCollections.observableArrayList(dad.Model.Curso.values()));
            try {
                Curso.getSelectionModel().select(dad.Model.Curso.valueOf(alumno.getPrograma()));
            } catch (IllegalArgumentException e) {
                System.err.println("Valor no válido para Curso: " + alumno.getPrograma());
            }
            Curso.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    alumno.setPrograma(newValue.name());
                }
            });
            alumno.programaProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        Curso.getSelectionModel().select(dad.Model.Curso.valueOf(newValue));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Valor no válido para Curso: " + newValue);
                    }
                }
            });
        }
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

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
}
