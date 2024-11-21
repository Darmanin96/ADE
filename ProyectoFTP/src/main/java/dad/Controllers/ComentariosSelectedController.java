package dad.Controllers;

import dad.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.net.*;
import java.util.*;

public class ComentariosSelectedController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Cancelar;

    @FXML
    private TextField Comentario;

    @FXML
    private DatePicker Fecha;

    @FXML
    private ChoiceBox<Integer> IdEmpresa;

    @FXML
    private Button Limpiar;

    @FXML
    private BorderPane root;

    private Comentarios comentariosSelected;

    @FXML
    void onAñadirAction(ActionEvent event) {

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
    void onLImpiarAction(ActionEvent event) {
        Comentario.setText("");
        Fecha.setValue(null);
        IdEmpresa.setValue(null);
    }

    public void obtenerComentario(Comentarios comentarios){
        if (comentarios != null) {
            this.comentariosSelected = comentarios;
            Comentario.textProperty().bindBidirectional(comentariosSelected.comentarioProperty());
            Fecha.valueProperty().bindBidirectional(comentarios.fechaComentarioProperty());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
