package dad.Controllers;

import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.net.*;
import java.util.*;

public class tutorGrupoController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<tutorGrupo, String> Grupo;

    @FXML
    private TableColumn<tutorGrupo, String> Nombre;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private TableColumn<tutorGrupo, Integer> idAlumno;

    @FXML
    private TableColumn<tutorGrupo,Integer> idTutor;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<tutorGrupo> tableGrupo;

    @FXML
    void onAñadirAction(ActionEvent event) {
        tutorGrupoCreateController tutorGrupoCreateController = new tutorGrupoCreateController();
        Stage stage = new Stage();
        stage.setTitle("NUeva Visita");
        stage.setScene(new Scene(tutorGrupoCreateController.getRoot()));
        stage.showAndWait();
        if (tutorGrupoCreateController.isConfirmar()){
            String nombre = Nombre.getText();

        }
    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
