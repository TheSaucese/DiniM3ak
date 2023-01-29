package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * The VerificationController class handles the verification process for the user.
 * It contains methods and variables that are used to confirm the code sent to the user's email.
 */
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

    /**
     * The handleConfirm() method is called when the user clicks the "Confirm" button.
     * It compares the code entered by the user with the code sent to the user's email.
     * If the codes match, the user is directed to the next page.
     * If the codes do not match, an error message is displayed on the screen.
     * @throws IOException if there is an error loading the next scene.
     */
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

    /**
     * This method sets the code value to the given input parameter.
     * @param c the new code value to be set.
     */
    static public void setCode(String c){
        code=c;
    }
}
