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

public class SplashController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ProgressBar bar;
    @FXML
    private Text Initializing;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }

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
