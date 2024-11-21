package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class ComentariosCreateController implements Initializable {

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

    private boolean confirmar = false;
    @FXML
    void onAñadirAction(ActionEvent event) {
            confirmar = true;
            cerrar();
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
        cerrar();
    }

    @FXML
    void onLImpiarAction(ActionEvent event) {
        Comentario.setText("");
        Fecha.setValue(null);
        IdEmpresa.setValue(null);
    }

    private void cerrar(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }



    public ComentariosCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ComentarioCreateView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Button getAñadir() {
        return Añadir;
    }

    public void setAñadir(Button añadir) {
        Añadir = añadir;
    }

    public Button getCancelar() {
        return Cancelar;
    }

    public void setCancelar(Button cancelar) {
        Cancelar = cancelar;
    }

    public TextField getComentario() {
        return Comentario;
    }

    public void setComentario(TextField comentario) {
        Comentario = comentario;
    }

    public DatePicker getFecha() {
        return Fecha;
    }

    public void setFecha(DatePicker fecha) {
        Fecha = fecha;
    }

    public ChoiceBox<Integer> getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(ChoiceBox<Integer> idEmpresa) {
        IdEmpresa = idEmpresa;
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

    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }
}
