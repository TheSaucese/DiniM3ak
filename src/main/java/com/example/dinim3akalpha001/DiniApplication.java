package com.example.dinim3akalpha001;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DiniApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DiniApplication.class.getResource("ProfileDriver.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 812);
        scene.getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());
        stage.setTitle("DINIM3AK3000");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setX(25);
        stage.setY(25);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}