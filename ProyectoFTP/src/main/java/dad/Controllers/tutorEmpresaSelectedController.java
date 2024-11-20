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

public class tutorEmpresaSelectedController implements Initializable {


    @FXML
    private TextField Contacto;

    @FXML
    private TextField Nombre;

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private Button Limpiar;

    @FXML
    private BorderPane root;

    @FXML
    void onActualizarAction(ActionEvent event) {
        tutorEmpresaController tutor = new tutorEmpresaController();
        String nombre = Nombre.getText();
        String contacto = Contacto.getText();
        Integer id = Integer.parseInt(tutor.getIdTutor().toString());
        String sql = "UPDATE tutor_empresa SET Nombre = ?, Contacto = ? WHERE Id_Tutor  = ?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(0,id);
                stmt.setString(1,nombre);
                stmt.setString(2,contacto);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El tutor ha sido actualizado con éxito.");
            } else {
                System.err.println("No se encontró un tutor con ese ID.");
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar el tutor: " + e.getMessage());
        }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void limpiar(){
        Nombre.setText("");
        Contacto.setText("");
    }


    public void obtenertutorEmpresa(tutorEmpresa tutor){
        if (tutor != null){
            Nombre.textProperty().bindBidirectional(tutor.nombreProperty());
            Contacto.textProperty().bindBidirectional(tutor.contactoProperty());
        }

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

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TextField getContacto() {
        return Contacto;
    }

    public void setContacto(TextField contacto) {
        Contacto = contacto;
    }

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
    }

    public Button getActualizar() {
        return Actualizar;
    }

    public void setActualizar(Button actualizar) {
        Actualizar = actualizar;
    }
}
