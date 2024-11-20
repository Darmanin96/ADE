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

public class tutor_GrupoSelectedController implements Initializable {


    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Grupo;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField idAlumno;

    @FXML
    private BorderPane root;

    @FXML
    void onActualizarAction(ActionEvent event) {
        tutor_GrupoController tutor_grupo = new tutor_GrupoController();
        String nombre = Nombre.getText();
        String grupo = Grupo.getText();
        Integer Id_Alumno = Integer.parseInt(idAlumno.getText());
        Integer Id_Grupo = Integer.parseInt(tutor_grupo.getIdTutor().getText());
        String sql = "UPDATE tutor_grupo SET Nombre = ?, Grupo = ?, Id_Alumno = ? WHERE Id_Grupo = ?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(0,Id_Grupo);
            stmt.setString(1, nombre);
            stmt.setString(2, grupo);
            stmt.setInt(3,Id_Alumno);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El tutor ha sido actualizado con éxito.");
            } else {
                System.err.println("No se encontró el tutor con ese ID.");
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar el tutor: " + e.getMessage());
        }
    }

    @FXML
    void onLimpiarAction(ActionEvent event) {
        Nombre.setText("");
        idAlumno.setText("");
        Grupo.setText("");
    }

    @FXML
    void ónCancelarAction(ActionEvent event) {
        cerrar();
    }


    public void obtenerTutorGrupo(tutorGrupo grupo) {
        if (grupo != null) {
            Nombre.textProperty().bindBidirectional(grupo.nombreProperty());
            Grupo.textProperty().bindBidirectional(grupo.grupoProperty());
        }
    }

    public void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public Button getLimpiar() {
        return Limpiar;
    }

    public void setLimpiar(Button limpiar) {
        Limpiar = limpiar;
    }

    public TextField getGrupo() {
        return Grupo;
    }

    public void setGrupo(TextField grupo) {
        Grupo = grupo;
    }

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
    }

    public TextField getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(TextField idAlumno) {
        this.idAlumno = idAlumno;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
