package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class VerificationController {
    static private String code;
    @FXML
    private Button Confirm;
    @FXML
    private TextField inputCode;
    @FXML
    private ImageView wrong_code_icon;
    @FXML
    private Label wrong_code;
    @FXML
    private void handleConfirm() throws IOException {
        if (code.equals(inputCode.getText())) {
            new DiniController().handleScenes("Choice.fxml", Confirm);
        }
        else {
            wrong_code_icon.setVisible(true);
            wrong_code.setVisible(true);

        }
    }
    static public void setCode(String c){
        code=c;
    }
}
