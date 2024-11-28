package dad.Controllers;

import dad.Conexion.*;
import dad.Model.*;
import javafx.collections.FXCollections;
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
    private ChoiceBox<Curso> grupoCBox;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private BorderPane root;

    @FXML
    void onActualizarAction(ActionEvent event) {
        tutor_GrupoController tutor_grupo = new tutor_GrupoController();
        String nombre = Nombre.getText();
        String grupo = grupoCBox.getValue() != null ? grupoCBox.getValue().name() : null;
        Integer Id_Grupo = Integer.parseInt(tutor_grupo.getIdTutor().getText());
        String sql = "UPDATE tutor_grupo SET Nombre = ?, Grupo = ? WHERE Id_Grupo = ?";
        try (Connection con = Conectar.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(0,Id_Grupo);
            stmt.setString(1, nombre);
            stmt.setString(2, grupo);
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
        grupoCBox.getItems().clear();
    }

    @FXML
    void ónCancelarAction(ActionEvent event) {
        cerrar();
    }


    public void obtenerTutorGrupo(tutorGrupo grupo) {
        if (grupo != null) {
            Nombre.textProperty().bindBidirectional(grupo.nombreProperty());
            grupoCBox.setItems(FXCollections.observableArrayList(dad.Model.Curso.values()));
            try {
                grupoCBox.getSelectionModel().select(dad.Model.Curso.valueOf(String.valueOf(grupo.getGrupo())));
            } catch (IllegalArgumentException e) {
                System.err.println("Valor no válido para Curso: " + grupo.getGrupo());
            }
            grupoCBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    grupo.setGrupo(Curso.valueOf(newValue.name()));
                }
            });
            grupo.grupoProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        grupoCBox.getSelectionModel().select(dad.Model.Curso.valueOf(String.valueOf(newValue)));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Valor no válido para Curso: " + newValue);
                    }
                }
            });
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

    public TextField getNombre() {
        return Nombre;
    }

    public void setNombre(TextField nombre) {
        Nombre = nombre;
    }

    public ChoiceBox<Curso> getGrupoCBox() {
        return grupoCBox;
    }

    public void setGrupoCBox(ChoiceBox<Curso> grupoCBox) {
        grupoCBox = grupoCBox;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
