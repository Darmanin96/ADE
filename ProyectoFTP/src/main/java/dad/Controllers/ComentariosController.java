package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class ComentariosController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private TableColumn<?, ?> Comentario;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<?, ?> Fecha;

    @FXML
    private AnchorPane detallePanel;

    @FXML
    private TableColumn<?, ?> idAlumno;

    @FXML
    private TableColumn<?, ?> idComentario;

    @FXML
    private TableColumn<?, ?> idTutor;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<?> tableComentarios;

    @FXML
    void onAñadirAction(ActionEvent event) {

    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



}
