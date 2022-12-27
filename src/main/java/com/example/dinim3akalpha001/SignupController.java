package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private TextField pass_text;
    @FXML
    private CheckBox ShowPass;
    @FXML
    private TextField pass_hidden;
    @FXML
    private Button Signup;
    @FXML
    private void handleSignup() throws IOException {
        new DiniController().handleScenes("Verification.fxml",Signup);
    }
    @FXML
    protected void toggleVisiblePassword() {
        if (ShowPass.isSelected()) {
            pass_text.setVisible(true);
            pass_text.setText(pass_hidden.getText());
            pass_hidden.setVisible(false);
            return;
        }
        pass_hidden.setText(pass_text.getText());
        pass_hidden.setVisible(true);
        pass_text.setVisible(false);
    }
}
