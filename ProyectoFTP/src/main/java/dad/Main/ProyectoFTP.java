package dad.Main;

import dad.Controllers.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class ProyectoFTP extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        RootController root = new RootController();
        Stage stage = new Stage();
        stage.setTitle("Proyecto FTP");
        stage.setScene(new Scene(root.getRoot()));
        stage.show();
    }
}
