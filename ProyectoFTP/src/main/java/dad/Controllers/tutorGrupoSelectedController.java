package dad.Controllers;

import dad.Conexion.*;
import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.*;
import java.sql.*;
import java.util.*;

public class tutorGrupoSelectedController implements Initializable {

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private ChoiceBox<Curso> Grupo;

    @FXML
    private ChoiceBox<Integer> IdAlumno;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private BorderPane root;

    private tutorGrupo currentTutorGrupo; // Para almacenar el tutorGrupo seleccionado

    private boolean confirmar = false;

    @FXML
    void onActualizarAction(ActionEvent event) {
        String nombre = Nombre.getText();
        Curso grupo = Grupo.getSelectionModel().getSelectedItem(); // Es mejor mantenerlo como Curso en lugar de String
        Integer idAlumno = IdAlumno.getValue();

        // Verificamos que todos los campos necesarios estén seleccionados
        if (nombre.isEmpty() || grupo == null || idAlumno == null) {
            System.err.println("Por favor, complete todos los campos.");
            return;
        }

        String sql = "UPDATE tutor_grupo SET Nombre=?, Grupo=?, Id_Alumno=? WHERE Id_Tutor=?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, grupo.toString());  // Guardamos el nombre del curso como String
            stmt.setInt(3, idAlumno);  // Usamos IdAlumno seleccionado
            stmt.setInt(4, currentTutorGrupo.getId_tutor());  // Usamos Id_Tutor para actualizar

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El tutor ha sido actualizado con éxito.");
            } else {
                System.err.println("No se encontró un tutor con ese ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el tutor: " + e.getMessage());
        }
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        Nombre.clear();
        Grupo.getSelectionModel().clearSelection();
        IdAlumno.getSelectionModel().clearSelection();
    }

    @FXML
    void ónCancelarAction(ActionEvent event) {
        confirmar = false;
        closeWindow();
    }

    private void limpiar() {
        Nombre.clear();
        Grupo.getSelectionModel().clearSelection();
        IdAlumno.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Grupo.getItems().setAll(Curso.values());
        cargarIdAlumnos();
    }

    private void cargarIdAlumnos() {
        // Llenar el ChoiceBox de IdAlumno con los IDs de los alumnos disponibles
        List<Integer> alumnos = obtenerAlumnos();
        IdAlumno.getItems().setAll(alumnos);
    }

    private List<Integer> obtenerAlumnos() {
        // Aquí deberías hacer una consulta a la base de datos para obtener los Ids de los alumnos
        // Esto es un ejemplo de cómo podrías obtener una lista de IDs de alumnos
        List<Integer> alumnos = new ArrayList<>();
        String sql = "SELECT Id_Alumno FROM alumno";
        try (Connection con = Conectar.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alumnos.add(rs.getInt("Id_Alumno"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los alumnos: " + e.getMessage());
        }
        return alumnos;
    }

    public void obtenerTutor(tutorGrupo tutor) {
        if (tutor != null) {
            this.currentTutorGrupo = tutor;
            Nombre.textProperty().bindBidirectional(tutor.nombreProperty());
            IdAlumno.setValue(tutor.getId_alumno());  // Asignar el IdAlumno correctamente
            Grupo.setValue(tutor.getGrupo()); // Aseguramos que se seleccione el grupo adecuado
        }
    }

    private void closeWindow() {
        root.getScene().getWindow().hide();
    }

    public boolean isConfirmar() {
        return confirmar;
    }
}
