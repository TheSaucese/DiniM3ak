package com.example.dinim3akalpha001;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The SplashController class is responsible for handling the initial loading screen when the application is launched.
 * It displays a progress bar and a text indicating the percentage of initialization.
 */

public class SplashController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ProgressBar bar;
    @FXML
    private Text Initializing;

    /**
     * The initialize method is called when the controller is created. It is responsible for starting the SplashScreen thread.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }

    /**
     * The runningPBar method is used to simulate the loading process by updating
     * the progress bar and the initialization text.
     */
    public void runningPBar() throws InterruptedException {
        double i=0;

        while( i<=1.0000000000000008)
        {
            try{
                Thread.sleep(50);
                bar.setProgress(i);
                double r=i*100;
                Initializing.setText("Initializing "+ (int)r +"%");
                i+=0.01;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        Thread.sleep(500);
    }

    /**
     * The SplashScreen class is a nested class that extends the Thread class.
     * It is used to run the Splash Screen in a separate thread.
     */
    class SplashScreen extends Thread{
        @Override
        public void run() {
            try {
                runningPBar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new DiniController().handleScenes("Welcome.fxml",rootPane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

}
