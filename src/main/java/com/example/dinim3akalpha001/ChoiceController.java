package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ChoiceController {
    @FXML
    private Button Driving;

    @FXML
    private void handleDriving() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Driving);
    }
}
