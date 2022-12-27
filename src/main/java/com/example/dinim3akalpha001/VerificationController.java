package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VerificationController {
    @FXML
    private Button Confirm;

    @FXML
    private void handleConfirm() throws IOException {
        new DiniController().handleScenes("Choice.fxml",Confirm);
    }
}
