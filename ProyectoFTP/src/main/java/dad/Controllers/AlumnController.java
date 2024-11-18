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

public class AlumnController implements Initializable {

    @FXML
    private TableColumn<Alumno, String> Apellidos;

    @FXML
    private TableColumn<Alumno, String> Contacto;

    @FXML
    private TableColumn<Alumno, String> Nombre;

    @FXML
    private TableColumn<Alumno, Boolean> Pendientes;

    @FXML
    private TableColumn<Alumno, String> Programa;

    @FXML
    private TableColumn<Alumno, Integer> id_Alumn;

    @FXML
    private Button añadir;

    @FXML
    private Button eliminar;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Alumno> tableAlumn;

    @FXML
    private TableColumn<Alumno, String> tutorEmpresa;

    @FXML
    private TableColumn<Alumno, String> tutorGrupo;

    @FXML
    private AnchorPane detallePanel;

    private ObservableList<Alumno> alumnos = FXCollections.observableArrayList();

    @FXML
    void onAñadirAction(ActionEvent event) {
        AlumnoCreateController alumnoCreateController = new AlumnoCreateController();
        Stage stage = new Stage();
        stage.setTitle("Nuevo alumno");
        stage.setScene(new Scene(alumnoCreateController.getRoot()));
        stage.showAndWait();

        if (alumnoCreateController.isConfirmar()) {
            String nombre = alumnoCreateController.getNombre().getText();
            String apellido = alumnoCreateController.getApellidos().getText();
            String tutorEmpresa = alumnoCreateController.getTutorEmpresa().getText();
            String tutorGrupo = alumnoCreateController.getTutorGrupo().getText();
            Boolean pendiente = alumnoCreateController.getPendientes().isSelected();
            String programa = alumnoCreateController
                    .getCurso().getValue().toString();
            String contacto = alumnoCreateController.getContactos().getText();
            int nuevoId = insertarAlumno(nombre, apellido, tutorEmpresa, tutorGrupo, pendiente, programa, contacto);

            if (nuevoId > 0) {
                cargarTablaAlumno();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("No se pudo añadir el alumno.");
                alerta.setContentText("Hubo un problema al insertar el alumno en la base de datos.");
                alerta.showAndWait();
            }
        }
    }


    private int insertarAlumno(String nombre, String apellido, String tutorEmpresa, String tutorGrupo, Boolean pendiente, String programa, String contacto) {
        String sql = "INSERT INTO alumno (Nombre, Apellidos, Tutor_Empresa, Tutor_Grupo, Pendiente, Programa, Contacto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la consulta
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, tutorEmpresa);
            stmt.setString(4, tutorGrupo);
            stmt.setBoolean(5, pendiente);
            stmt.setString(6, programa);
            stmt.setString(7, contacto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



    @FXML
    void onEliminarAction(ActionEvent event) {
            Alumno selected = tableAlumn.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Eliminar alumno");
                alert.setHeaderText("Se eliminará el alumno " + selected.getNombre());
                alert.setContentText("¿Estas seguro?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    eliminarAlumno(selected.getId_Alumno());
                    tableAlumn.getItems().remove(selected);
                }
            }
    }
    private void eliminarAlumno(int alumnoId) {
        String sql = "DELETE FROM alumno WHERE Id_Alumno = ?";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alumnoId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Alumno eliminado correctamente.");
            } else {
                System.out.println("No se encontró el alumno.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            id_Alumn.setCellValueFactory(new PropertyValueFactory<>("id_Alumno"));
            Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            Apellidos.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
            tutorEmpresa.setCellValueFactory(new PropertyValueFactory<>("Tutor_Empresa"));
            tutorGrupo.setCellValueFactory(new PropertyValueFactory<>("Tutor_Grupo"));
            Pendientes.setCellValueFactory(new PropertyValueFactory<>("Pendiente"));
            Programa.setCellValueFactory(new PropertyValueFactory<>("Programa"));
            Contacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));
            cargarTablaAlumno();
            tableAlumn.setItems(alumnos);
            Nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
            Apellidos.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
            tutorEmpresa.setCellValueFactory(cellData -> cellData.getValue().tutor_empresaProperty());
            tutorGrupo.setCellValueFactory(cellData -> cellData.getValue().tutor_grupoProperty());
            Pendientes.setCellValueFactory(cellData -> cellData.getValue().pendientesProperty());
            Programa.setCellValueFactory(cellData -> cellData.getValue().programaProperty());
            Contacto.setCellValueFactory(cellData -> cellData.getValue().contactoProperty());
            tableAlumn.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null) {
                cargar(newValue);
            }
        });

    }


    public void cargar(Alumno alumnoSeleccionado){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnSelectedView.fxml"));
            Pane pane = loader.load();
            AlumnSelectedController alumnSelectedController = loader.getController();
            alumnSelectedController.obtenerAlumno(alumnoSeleccionado);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void cargarTablaAlumno() {
        String sql = "SELECT * FROM alumno";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                alumnos.add(new Alumno(
                        rs.getInt("Id_Alumno"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("Tutor_Empresa"),
                        rs.getString("Tutor_Grupo"),
                        rs.getBoolean("Pendiente"),
                        rs.getString("Programa"),
                        rs.getString("Contacto")
                ));
            }
            tableAlumn.setItems(alumnos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TableColumn<Alumno, String> getApellidos() {
        return Apellidos;
    }



    public void setApellidos(TableColumn<Alumno, String> apellidos) {
        Apellidos = apellidos;
    }

    public TableColumn<Alumno, String> getContacto() {
        return Contacto;
    }

    public void setContacto(TableColumn<Alumno, String> contacto) {
        Contacto = contacto;
    }

    public TableColumn<Alumno, String> getNombre() {
        return Nombre;
    }

    public void setNombre(TableColumn<Alumno, String> nombre) {
        Nombre = nombre;
    }

    public TableColumn<Alumno, Boolean> getPendientes() {
        return Pendientes;
    }

    public void setPendientes(TableColumn<Alumno, Boolean> pendientes) {
        Pendientes = pendientes;
    }

    public TableColumn<Alumno, String> getPrograma() {
        return Programa;
    }

    public void setPrograma(TableColumn<Alumno, String> programa) {
        Programa = programa;
    }

    public TableColumn<Alumno, Integer> getId_Alumn() {
        return id_Alumn;
    }

    public void setId_Alumn(TableColumn<Alumno, Integer> id_Alumn) {
        this.id_Alumn = id_Alumn;
    }

    public Button getAñadir() {
        return añadir;
    }

    public void setAñadir(Button añadir) {
        this.añadir = añadir;
    }

    public Button getEliminar() {
        return eliminar;
    }

    public void setEliminar(Button eliminar) {
        this.eliminar = eliminar;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<Alumno> getTableAlumn() {
        return tableAlumn;
    }

    public void setTableAlumn(TableView<Alumno> tableAlumn) {
        this.tableAlumn = tableAlumn;
    }

    public TableColumn<Alumno, String> getTutorEmpresa() {
        return tutorEmpresa;
    }

    public void setTutorEmpresa(TableColumn<Alumno, String> tutorEmpresa) {
        this.tutorEmpresa = tutorEmpresa;
    }

    public TableColumn<Alumno, String> getTutorGrupo() {
        return tutorGrupo;
    }

    public void setTutorGrupo(TableColumn<Alumno, String> tutorGrupo) {
        this.tutorGrupo = tutorGrupo;
    }

    public ObservableList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ObservableList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }
}

