package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PaymentController {
    @FXML
    private Button Arrow;
    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Arrow);
    }
}
