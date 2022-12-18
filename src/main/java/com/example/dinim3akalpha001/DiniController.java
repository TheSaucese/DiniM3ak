package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DiniController {
    @FXML
    private TextField pass_text;
    @FXML
    private CheckBox ShowPass;
    @FXML
    private TextField pass_hidden;
    @FXML
    protected void togglevisiblePassword() {
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
