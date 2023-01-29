package com.example.dinim3akalpha001;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 *  DiniApplication is the main class for the DINIM3AK application. It extends the javafx.application.Application class and overrides the start method.
 *  In the start method, it creates a new instance of MongoController, loads the Welcome.fxml file and sets it as the scene for the stage,
 *  adds a stylesheet to the scene, sets the stage title to "DINIM3AK3000", sets the stage as non-resizable, and shows the stage.
 */


public class DiniApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new MongoController();
        FXMLLoader fxmlLoader = new FXMLLoader(DiniApplication.class.getResource("HomeRider.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 812);
        scene.getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());
        stage.setTitle("DINIM3AK3000");
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     *  The main method launches the application.
     */
    public static void main(String[] args) {
        launch();
    }
}