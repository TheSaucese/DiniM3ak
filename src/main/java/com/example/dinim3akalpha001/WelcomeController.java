package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Button Login,Signup;
    @FXML
    private void handleSignup() throws IOException {
        new DiniController().handleScenes("Signup.fxml",Signup);
    }
}
