package com.example.dinim3akalpha001;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.DiniController.showTooltip;
import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.getuEmail;
import static com.example.dinim3akalpha001.SignupController2.getuJob;
import static com.mongodb.client.model.Filters.*;
/**
 * The NotiController class handles the functionality of the Notification feature .
 * It allows the user to navigate to different scenes, view notifications and handle them.
*/

public class NotiController implements Initializable {
    @FXML
    private Button Vehicle;
    @FXML
    private ImageView imgrider;
    @FXML
    private VBox vboxnoti;
    /**
     * The handleVehicle method is used to handle the functionality of the Vehicle button.
     * If the user's job is "Driver", it navigates to the Car.fxml scene.
     * If not, it displays a tooltip with a message "Switch to Driver to access this feature."
     */
    @FXML
    private void handleVehicle() throws IOException {
        if (getuJob().equals("Driver")) {
            new DiniController().handleScenes("Car.fxml", Vehicle);
        }
        else {
            Vehicle.setTooltip(new Tooltip("Switch to Driver to access this feature."));
            showTooltip((Stage) Vehicle.getScene().getWindow(),Vehicle,"Switch to Driver to access this feature.",null);
        }
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",Vehicle);
    }
    @FXML
    private void handleProfile() throws IOException {
        if (getuJob().equals("Driver")) {
            new DiniController().handleScenes("ProfileDriver.fxml",Vehicle);
        }
        else {
            new DiniController().handleScenes("ProfileRider.fxml",Vehicle);
        }
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",Vehicle);
    }
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",Vehicle);
    }
    private void IterateNoti(ArrayList<String> notification) {
        for (String i : notification) {
            if (db.getCollection("users").find(eq("_id",i)).first()!=null) {
                vboxnoti.getChildren().add(new NotiView(db.getCollection("users").find(eq("_id", i)).first().getString("fullname"), i, vboxnoti));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(getuJob()=="Driver") {
            imgrider.setImage(new Image("com/Images/dinim3akalpha001/noti.png"));
        }
        else if(getuJob()=="Rider") {
            imgrider.setImage(new Image("com/Images/dinim3akalpha001/notirider.png"));
        }
        Document user = db.getCollection("users").find(eq("email",getuEmail())).first();
        IterateNoti((ArrayList) user.get("notification"));
    }
}
