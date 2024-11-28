package dad.Controllers;

import dad.Conexion.*;
import static dad.Conexion.Conectar.getConnection;
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
import java.sql.Date;
import java.time.*;
import java.util.*;

public class AsignacionController implements Initializable {

    @FXML
    private TableColumn<Asignacion, String> Apellidos;

    @FXML
    private Button Añadir;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<Asignacion, String> Empresa;

    @FXML
    private TableColumn<Asignacion, LocalDate> FechaFin;

    @FXML
    private TableColumn<Asignacion, LocalDate> FechaInicio;

    @FXML
    private TableColumn<Asignacion, Integer> IdTutorEmpresa;

    @FXML
    private TableColumn<Asignacion, Integer> IdTutorGrupo;

    @FXML
    private TableColumn<Asignacion, String> Nombre;

    @FXML
    private TableColumn<Asignacion, Integer> idAlumno;

    @FXML
    private TableColumn<Asignacion, Integer> idAsignacion;

    @FXML
    private TableColumn<Asignacion, Integer> idEmpresa;

    @FXML
    private BorderPane root;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private TableView<Asignacion> tableAsignacion;

    private final ObservableList<Asignacion> AsignacionLista = FXCollections.observableArrayList();

    private Stage asignacionCreateStage;

    @FXML
    void onAñadirAction(ActionEvent event) {
        if (asignacionCreateStage == null || asignacionCreateStage.isShowing()) {
            AsignacionCreateController asignacionCreateController = new AsignacionCreateController();
            asignacionCreateStage = new Stage();
            asignacionCreateStage.setTitle("Nueva Asignacion");
            asignacionCreateStage.initModality(Modality.APPLICATION_MODAL);
            asignacionCreateStage.setScene(new Scene(asignacionCreateController.getRoot()));
            asignacionCreateStage.setOnHidden(e -> {
                asignacionCreateStage = null;
                if (asignacionCreateController.isConfirmar()) {
                    Integer idAlumnoVerdad = asignacionCreateController.getIdAlumno().getValue();
                    Integer idEmpresa = asignacionCreateController.getIdEmpresa().getValue();
                    Integer tutorEmpresa = asignacionCreateController.getIdTutorEmpresa().getValue();
                    Integer tutorGrupo = asignacionCreateController.getIdTutorDocente().getValue();
                    LocalDate fechaInicio = asignacionCreateController.getFechaInicio().getValue();
                    LocalDate fechaFin = asignacionCreateController.getFechaFin().getValue();
                    String nombre = obtenerNombreAlumno(idAlumnoVerdad);
                    String apellidos = obtenerApellidosAlumno(idAlumnoVerdad);
                    String empresa = obtenerNombreEmpresa(idEmpresa);
                    int nuevoid = insertarAsignacion(idAlumnoVerdad, idEmpresa, tutorEmpresa, tutorGrupo, fechaInicio, fechaFin, nombre, apellidos, empresa);
                    if (nuevoid > 0) {
                        cargarTablaAsignacion();
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error");
                        alerta.setHeaderText("No se pudo añadir la asignación.");
                        alerta.setContentText("Hubo un problema al insertar la asignación en la base de datos.");
                        alerta.showAndWait();
                    }
                }
            });
            asignacionCreateStage.show();
        }else {
            asignacionCreateStage.requestFocus();
        }
    }

    private String obtenerNombreAlumno(Integer idAlumno) {
        String sql = "SELECT Nombre FROM alumno WHERE Id_Alumno = ?";
        String nombre = "";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAlumno);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombre;
    }

    private String obtenerApellidosAlumno(Integer idAlumno) {
        String sql = "SELECT Apellidos FROM alumno WHERE Id_Alumno = ?";
        String apellidos = "";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAlumno);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                apellidos = rs.getString("Apellidos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apellidos;
    }

    // Método para obtener el nombre de la empresa
    private String obtenerNombreEmpresa(Integer idEmpresa) {
        String sql = "SELECT Nombre FROM empresas WHERE Id_Empresa = ?";
        String empresa = "";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                empresa = rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }


    private int insertarAsignacion(Integer idAlumno, Integer idEmpresa, Integer tutorEmpresa, Integer tutorGrupo,
                                   LocalDate fechaInicio, LocalDate fechaFin, String nombre, String apellidos, String empresa) {
        String sql = "INSERT INTO alumnos_empresas_rel(Id_Alumno, Nombre, Apellidos, Id_Empresa, Empresa, Id_Tutor_Empresa, Id_Tutor_Docente, Fecha_Inicio, Fecha_Fin) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAlumno);
            stmt.setString(2, nombre);
            stmt.setString(3, apellidos);
            stmt.setInt(4, idEmpresa);
            stmt.setString(5, empresa);
            stmt.setInt(6, tutorEmpresa);
            stmt.setInt(7, tutorGrupo);
            stmt.setString(8, fechaInicio.toString());
            stmt.setString(9, fechaFin.toString());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    @FXML
    void onEliminarAction(ActionEvent event) {
        Asignacion selected = tableAsignacion.getSelectionModel().getSelectedItem();
        if (selected != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Asignacion");
            alert.setContentText("Se eliminará la asignacion con " + selected.getNombre());
            alert.setContentText("¿Estás seguro?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get()==ButtonType.OK){
                eliminarAsignacion(selected.getIdAsignacion());
                tableAsignacion.getItems().remove(selected);
            }
        }
    }


    private void eliminarAsignacion(int asignacionId){
        String sql = "DELETE FROM alumnos_empresas_rel WHERE \n" +
                "Id_Asignacion";
        try (Connection connection = Conectar.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, asignacionId);
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
        idAsignacion.setCellValueFactory(new PropertyValueFactory<>("idAsignacion"));
        idEmpresa.setCellValueFactory(new PropertyValueFactory<>("idEmpresa"));
        idAlumno.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        Apellidos.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        FechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        FechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        IdTutorEmpresa.setCellValueFactory(new PropertyValueFactory<>("idTutorEmpresa"));
        IdTutorGrupo.setCellValueFactory(new PropertyValueFactory<>("idTutorGrupo"));
        Empresa.setCellValueFactory(new PropertyValueFactory<>("Empresa"));
        cargarTablaAsignacion();
        tableAsignacion.setItems(AsignacionLista);
        FechaFin.setCellValueFactory(cellData -> cellData.getValue().fechaFinProperty());
        FechaInicio.setCellValueFactory(cellData -> cellData.getValue().fechaInicioProperty());
        tableAsignacion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detallePanel.setVisible(newValue != null);
            if (newValue != null) {
                cargar(newValue);
            }
        });
    }


    public void cargar(Asignacion asignacion) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AsignacionSelectedView.fxml"));
            Pane pane = loader.load();
            AsignacionSelectedController controller = loader.getController();
            controller.obtenerAsignacion(asignacion);
            detallePanel.getChildren().clear();
            detallePanel.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarTablaAsignacion() {
        AsignacionLista.clear();
        String sql = "SELECT * FROM alumnos_empresas_rel";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AsignacionLista.add(new Asignacion(
                        rs.getInt("Id_Asignacion"),
                        rs.getInt("Id_Alumno"),
                        rs.getInt("Id_Empresa"),
                        rs.getInt("Id_Tutor_Docente"),
                        rs.getInt("Id_Tutor_Empresa"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("Empresa"),
                        rs.getDate("Fecha_Inicio").toLocalDate(),
                        rs.getDate("Fecha_Fin").toLocalDate()
                ));
            }
            tableAsignacion.setItems(AsignacionLista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TableColumn<Asignacion, String> getApellidos() {
        return Apellidos;
    }

    public void setApellidos(TableColumn<Asignacion, String> apellidos) {
        Apellidos = apellidos;
    }

    public Button getAñadir() {
        return Añadir;
    }

    public void setAñadir(Button añadir) {
        Añadir = añadir;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TableColumn<Asignacion, String> getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(TableColumn<Asignacion, String> empresa) {
        Empresa = empresa;
    }

    public TableColumn<Asignacion, LocalDate> getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(TableColumn<Asignacion, LocalDate> fechaFin) {
        FechaFin = fechaFin;
    }

    public TableColumn<Asignacion, LocalDate> getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(TableColumn<Asignacion, LocalDate> fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public AnchorPane getDetallePanel() {
        return detallePanel;
    }

    public void setDetallePanel(AnchorPane detallePanel) {
        this.detallePanel = detallePanel;
    }

    public TableColumn<Asignacion, Integer> getIdTutorEmpresa() {
        return IdTutorEmpresa;
    }

    public void setIdTutorEmpresa(TableColumn<Asignacion, Integer> idTutorEmpresa) {
        IdTutorEmpresa = idTutorEmpresa;
    }

    public TableColumn<Asignacion, Integer> getIdTutorGrupo() {
        return IdTutorGrupo;
    }

    public void setIdTutorGrupo(TableColumn<Asignacion, Integer> idTutorGrupo) {
        IdTutorGrupo = idTutorGrupo;
    }

    public TableColumn<Asignacion, String> getNombre() {
        return Nombre;
    }

    public void setNombre(TableColumn<Asignacion, String> nombre) {
        Nombre = nombre;
    }

    public TableColumn<Asignacion, Integer> getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(TableColumn<Asignacion, Integer> idAlumno) {
        this.idAlumno = idAlumno;
    }

    public TableColumn<Asignacion, Integer> getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(TableColumn<Asignacion, Integer> idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public TableColumn<Asignacion, Integer> getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(TableColumn<Asignacion, Integer> idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<Asignacion> getTableAsignacion() {
        return tableAsignacion;
    }

    public void setTableAsignacion(TableView<Asignacion> tableAsignacion) {
        this.tableAsignacion = tableAsignacion;
    }

    public ObservableList<Asignacion> getAsignacionLista() {
        return AsignacionLista;
    }
}
