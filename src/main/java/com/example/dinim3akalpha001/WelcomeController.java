package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Button Login,Signup;
    @FXML
    private void handleSignup() throws IOException {
        new DiniController().handleScenes("Signup.fxml",Signup);
    }
    @FXML
    private void handleLogin() throws IOException {
        new DiniController().handleScenes("Login.fxml",Login);
    }
}
