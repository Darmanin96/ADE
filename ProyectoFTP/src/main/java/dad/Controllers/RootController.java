package dad.Controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class RootController implements Initializable {



    @FXML
    private Tab Comentarios;

    @FXML
    private Button Save;

    @FXML
    private Tab Tutor_empresa;

    @FXML
    private Tab Tutor_grupo;

    @FXML
    private Tab Visitas;

    @FXML
    private Tab alumno;

    @FXML
    private Tab empresas;

    @FXML
    private TextField nombreBaseDatos;

    @FXML
    private BorderPane root;

    @FXML
    void onSaveAction(ActionEvent event) {

    }

    @FXML
    void onDarkModeAction(ActionEvent event) {
        Scene scene = root.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/css/dark-mode.css").toExternalForm());
    }

    @FXML
    void onLightModeAction(ActionEvent event) {
        Scene scene = root.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/css/light-mode.css").toExternalForm());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public RootController(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Tab getComentarios() {
        return Comentarios;
    }

    public void setComentarios(Tab comentarios) {
        Comentarios = comentarios;
    }

    public Button getSave() {
        return Save;
    }

    public void setSave(Button save) {
        Save = save;
    }

    public Tab getTutor_empresa() {
        return Tutor_empresa;
    }

    public void setTutor_empresa(Tab tutor_empresa) {
        Tutor_empresa = tutor_empresa;
    }

    public Tab getTutor_grupo() {
        return Tutor_grupo;
    }

    public void setTutor_grupo(Tab tutor_grupo) {
        Tutor_grupo = tutor_grupo;
    }

    public Tab getVisitas() {
        return Visitas;
    }

    public void setVisitas(Tab visitas) {
        Visitas = visitas;
    }

    public Tab getAlumno() {
        return alumno;
    }

    public void setAlumno(Tab alumno) {
        this.alumno = alumno;
    }

    public Tab getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Tab empresas) {
        this.empresas = empresas;
    }

    public TextField getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    public void setNombreBaseDatos(TextField nombreBaseDatos) {
        this.nombreBaseDatos = nombreBaseDatos;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
