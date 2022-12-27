package com.example.dinim3akalpha001;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DiniController {
    protected void handleScenes(String fxml, Node node) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DiniApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 375, 812);
        scene.getStylesheets().add(DiniApplication.class.getResource("style.css").toExternalForm());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
}
