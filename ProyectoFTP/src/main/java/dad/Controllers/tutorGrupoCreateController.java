package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class tutorGrupoCreateController implements Initializable {

    @FXML
    private Button Actualizar;

    @FXML
    private Button Cancelar;

    @FXML
    private ChoiceBox<?> IdAlumno;

    @FXML
    private Button Limpiar;

    @FXML
    private TextField Nombre;

    @FXML
    private BorderPane root;



    private boolean confirmar = false;

    @FXML
    void onActualizarAction(ActionEvent event) {

    }

    @FXML
    void onLimpiarAction(ActionEvent event) {

    }

    @FXML
    void ónCancelarAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
