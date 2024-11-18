package dad.Controllers;

import dad.Model.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.*;
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
    private GridPane root;

    @FXML
    private TextField tutorEmpresa;

    @FXML
    private TextField tutorGrupo;


    @FXML
    private AnchorPane detallePanel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void obtenerAlumno(Alumno alumno) {
        if (alumno != null) {
            // Vinculación de propiedades normales
            Nombre.textProperty().bindBidirectional(alumno.nombreProperty());
            Apellidos.textProperty().bindBidirectional(alumno.apellidoProperty());
            tutorEmpresa.textProperty().bindBidirectional(alumno.tutor_empresaProperty());
            tutorGrupo.textProperty().bindBidirectional(alumno.tutor_grupoProperty());
            Pendientes.selectedProperty().bindBidirectional(alumno.pendientesProperty());
            Contactos.textProperty().bindBidirectional(alumno.contactoProperty());

            // Poblar el ChoiceBox con los valores del enum Curso (o lista correspondiente)
            Curso.setItems(FXCollections.observableArrayList(dad.Model.Curso.values())); // Usamos valores del enum directamente

            // Seleccionar el valor inicial basado en la propiedad del alumno
            // Comprobamos si el valor del programa está en el enum y lo seleccionamos
            try {
                Curso.getSelectionModel().select(dad.Model.Curso.valueOf(alumno.getPrograma())); // Usamos valueOf para seleccionar el valor
            } catch (IllegalArgumentException e) {
                // Manejo de error si el valor no está en el enum
                System.err.println("Valor no válido para Curso: " + alumno.getPrograma());
            }

            // Listener para cambios en el ChoiceBox
            Curso.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    alumno.setPrograma(newValue.name()); // Actualiza el modelo con el nombre del curso
                }
            });

            // Listener para cambios en el modelo
            alumno.programaProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        Curso.getSelectionModel().select(dad.Model.Curso.valueOf(newValue)); // Actualiza el ChoiceBox con el valor del modelo
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

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
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
