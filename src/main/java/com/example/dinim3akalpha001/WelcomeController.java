package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * The WelcomeController class is used to handle the button events of the Welcome page.
 * It has two buttons, login and signup, which when clicked, navigate to the respective pages.
 */
public class WelcomeController {
    @FXML
    private Button Login,Signup;
    /**
     * This method is used to handle the signup button event.
     * When the signup button is clicked, it navigates to the Signup page.
     * @throws IOException if the Signup.fxml file is not found.
     */
    @FXML
    private void handleSignup() throws IOException {
        new DiniController().handleScenes("Signup.fxml",Signup);
    }
    /**
     * This method is used to handle the login button event.
     * When the login button is clicked, it navigates to the Login page.
     * @throws IOException if the Login.fxml file is not found.
     */
    @FXML
    private void handleLogin() throws IOException {
        new DiniController().handleScenes("Login.fxml",Login);
    }
}
