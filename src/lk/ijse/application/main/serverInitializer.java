package lk.ijse.application.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.application.controller.ServerClass;

import java.io.IOException;

public class serverInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ServerClass.newPort = 5000;
        ServerClass.select = true;

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/serverForm.fxml"))));
        primaryStage.show();
    }
}
