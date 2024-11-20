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
    private TextField Plazas;

    @FXML
    private TextField Telefono;

    @FXML
    private BorderPane root;

    private Empresa empresa;


    @FXML
    void onActualizarAction(ActionEvent event) {
        String nombre = Nombre.getText();
        String correo = Correo.getText();
        String direccion = Direccion.getText();
        String especialidad = Especialidad.getText();
        String telefono = Telefono.getText();
        String plazas = Plazas.getText().trim();
        int plazasReal = Integer.valueOf(plazas);
        Boolean participa = Participa.isSelected();
        String sql = "UPDATE empresas SET Nombre=?, Direccion=?, Especialidad=?, Telefono=?, Correo=?, Participa=?, Plazas=? WHERE Id_Empresa=?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(8,empresa.getId_Empresa());
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setString(3, telefono);
            stmt.setString(4, correo);
            stmt.setBoolean(5, participa);
            stmt.setInt(6, plazasReal);
            stmt.setString(7, especialidad);
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

    private void limpiar(){
        Nombre.setText("");
        Correo.setText("");
        Direccion.setText("");
        Especialidad.setText("");
        Nombre.setText("");
        Plazas.setText("");
        Telefono.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void obtenerEmpresa(Empresa empresa) {
        if (empresa != null) {
            this.empresa = empresa;
            Nombre.textProperty().bindBidirectional(empresa.nombreProperty());
            Direccion.textProperty().bindBidirectional(empresa.direccionProperty());
            Correo.textProperty().bindBidirectional(empresa.emailProperty());
            Especialidad.textProperty().bindBidirectional(empresa.especialidadProperty());
            Telefono.textProperty().bindBidirectional(empresa.telefonoProperty());
            Participa.selectedProperty().bindBidirectional(empresa.participaProperty());
            Plazas.textProperty().bindBidirectional(empresa.plazasProperty());
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

    public TextField getPlazas() {
        return Plazas;
    }

    public void setPlazas(TextField plazas) {
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


}
