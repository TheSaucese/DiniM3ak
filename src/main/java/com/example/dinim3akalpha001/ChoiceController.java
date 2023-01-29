package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 The ChoiceController class is responsible for handling the user's choice between being a driver or a rider.
 It is connected to the Choice.fxml file and interacts with the DiniController and SignupController2 classes.
 */
public class ChoiceController {
    /**
     * This is the button for the driving option.
     */
    @FXML
    private Button Driving;

    /**
     * This method is responsible for handling the user's choice to be a driver.
     * It calls the handleScenes method from DiniController class to change the scene to Signup2.fxml
     * and sets the user's job as "Driver" in SignupController2 class.
     *
     * @throws IOException if the specified fxml file is not found
     */
    @FXML
    private void handleDriving() throws IOException {
        new DiniController().handleScenes("Signup2.fxml",Driving);
        SignupController2.setuJob("Driver");
    }
    /**
     * This method is responsible for handling the user's choice to be a rider.
     * It calls the handleScenes method from DiniController class to change the scene to Signup2.fxml
     * and sets the user's job as "Rider" in SignupController2 class.
     *
     * @throws IOException if the specified fxml file is not found
     */
    @FXML
    private void handleRiding() throws IOException {
        new DiniController().handleScenes("Signup2.fxml",Driving);
        SignupController2.setuJob("Rider");
    }
}
