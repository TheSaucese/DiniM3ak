package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class VerificationController {
    static private String code;
    @FXML
    private Button Confirm;
    @FXML
    private TextField inputCode;
    @FXML
    private void handleConfirm() throws IOException {
        if (code.equals(inputCode.getText())) {
            new DiniController().handleScenes("Choice.fxml", Confirm);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Incorrect").show();

        }
    }
    static public void setCode(String c){
        code=c;
    }
}
