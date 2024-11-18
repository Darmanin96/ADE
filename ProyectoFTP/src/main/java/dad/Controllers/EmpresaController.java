package dad.Controllers;

import dad.Conexion.*;
import dad.Model.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class EmpresaController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private TableColumn<Empresa, String> Correo;

    @FXML
    private TableColumn<Empresa, String> Direccion;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<Empresa, String> Especialidad;

    @FXML
    private TableColumn<Empresa, Integer> Id_Empresa;

    @FXML
    private TableColumn<Empresa, String> Nombre;

    @FXML
    private TableColumn<Empresa, Boolean> Participa;

    @FXML
    private TableColumn<Empresa, Integer> Plazas;

    @FXML
    private TableColumn<Empresa, String> Telefono;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Empresa> tableEmpresa;

    private ObservableList<Empresa> empresas = FXCollections.observableArrayList();

    @FXML
    void onAñadirAction(ActionEvent event) {
        EmpresaCreateController empresaCreateController = new EmpresaCreateController();
        Stage stage = new Stage();
        stage.setTitle("Nueva Empresa");
        stage.setScene(new Scene(empresaCreateController.getRoot()));
        stage.showAndWait();
        if (empresaCreateController.isConfirmar()){
            String Nombre = empresaCreateController.getNombre().getText();
            String Direccion = empresaCreateController.getDireccion().getText();
            String Especialidad = empresaCreateController.getEspecialidad().getText();
            Boolean participa = empresaCreateController.isConfirmar();
            String plazas = empresaCreateController.getPlazas().getText();
            Integer plazasDeverdad = Integer.parseInt(plazas);
            String telefono = empresaCreateController.getTelefono().getText();
            String correo = empresaCreateController.getCorreo().getText();
            String especialidad = empresaCreateController.getEspecialidad().getText();
            Integer especialidadDeverdad = Integer.parseInt(especialidad);
            Empresa nuevaEmpresa = new Empresa(0,Nombre,Direccion,telefono,correo,participa,plazasDeverdad,especialidadDeverdad);
            empresas.add(nuevaEmpresa);
        }
    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        Direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        Participa.setCellValueFactory(new PropertyValueFactory<>("Participa"));
        Plazas.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        Telefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        Especialidad.setCellValueFactory(new PropertyValueFactory<>("Especialidad"));
        Correo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
        cargarTablaEmpresas();
        tableEmpresa.setItems(empresas);
        Nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        Direccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        Participa.setCellValueFactory(cellData -> cellData.getValue().participaProperty());
        Plazas.setCellValueFactory(cellData -> cellData.getValue().especialidadProperty().asObject());
        Telefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        Correo.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tableEmpresa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null) {
                cargar(newValue);
            }
        });
    }

    public void cargar(Empresa empresaSeleccionada) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EmpresaSelectedView.fxml"));
            Pane pane = loader.load();
            EmpresaSelectedController empresaSelectedController = new EmpresaSelectedController();
            empresaSelectedController.obtenerEmpresa(empresaSeleccionada);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarTablaEmpresas() {
        String sql =  "SELECT * FROM alumno";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                empresas.add(new Empresa(
                        rs.getInt("Id_Alumno"),
                        rs.getString("Nombre"),
                        rs.getString("Dirección"),
                        rs.getString("Teléfono"),
                        rs.getString("Email"),
                        rs.getBoolean("Participa"),
                        rs.getInt("Plazas"),
                        rs.getInt("Especialidad")
                ));
            }
            tableEmpresa.setItems(empresas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Button getAñadir() {
        return Añadir;
    }

    public void setAñadir(Button añadir) {
        Añadir = añadir;
    }

    public TableColumn<Empresa, String> getCorreo() {
        return Correo;
    }

    public void setCorreo(TableColumn<Empresa, String> correo) {
        Correo = correo;
    }

    public TableColumn<Empresa, String> getDireccion() {
        return Direccion;
    }

    public void setDireccion(TableColumn<Empresa, String> direccion) {
        Direccion = direccion;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TableColumn<Empresa, String> getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(TableColumn<Empresa, String> especialidad) {
        Especialidad = especialidad;
    }

    public TableColumn<Empresa, Integer> getId_Empresa() {
        return Id_Empresa;
    }

    public void setId_Empresa(TableColumn<Empresa, Integer> id_Empresa) {
        Id_Empresa = id_Empresa;
    }

    public TableColumn<Empresa, String> getNombre() {
        return Nombre;
    }

    public void setNombre(TableColumn<Empresa, String> nombre) {
        Nombre = nombre;
    }

    public TableColumn<Empresa, Boolean> getParticipa() {
        return Participa;
    }

    public void setParticipa(TableColumn<Empresa, Boolean> participa) {
        Participa = participa;
    }

    public TableColumn<Empresa, Integer> getPlazas() {
        return Plazas;
    }

    public void setPlazas(TableColumn<Empresa, Integer> plazas) {
        Plazas = plazas;
    }

    public TableColumn<Empresa, String> getTelefono() {
        return Telefono;
    }

    public void setTelefono(TableColumn<Empresa, String> telefono) {
        Telefono = telefono;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<Empresa> getTableEmpresa() {
        return tableEmpresa;
    }

    public void setTableEmpresa(TableView<Empresa> tableEmpresa) {
        this.tableEmpresa = tableEmpresa;
    }
}
