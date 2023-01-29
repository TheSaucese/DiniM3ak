package com.example.dinim3akalpha001;

import com.mongodb.SubjectProvider;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.example.dinim3akalpha001.DiniController.showTooltip;
import static com.example.dinim3akalpha001.SignupController2.getuID;
import static com.mongodb.client.model.Filters.eq;

import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.getuJob;

/**
 * This class handles the user interface for the payment see feature.
 * It implements the Initializable interface and uses FXML to create the UI.
 * The class contains methods and fields related to the scrolling animation,
 * deletion of payment, and displaying payment images.
 */

public class PaymentSeeController implements Initializable {
    @FXML
    private javafx.scene.control.ScrollPane ScrollPane;
    @FXML
    private javafx.scene.layout.HBox AnchorPane;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;
    @FXML
    private Button Delete,Arrow12;
    @FXML
    private ImageView paymentimage;
    private Pane n;
    private Rectangle oldrectangle;
    private Rectangle newrectangle;

    /**
     * This method animates the scrolling of the payment images and updates
     * the color of the navigation circles to indicate the current image.
     */
    @FXML
    protected void ScrollAnimate() {
        switch(AnchorPane.getChildren().size()) {
            case 4 :
                if(ScrollPane.getHvalue()>=0&&ScrollPane.getHvalue()<0.14) {
                    circle1.setFill(Color.web("#9F73AB"));
                    circle2.setFill(Color.WHITE);
                    circle3.setFill(Color.WHITE);
                    circle4.setFill(Color.WHITE);
                }
                else if(ScrollPane.getHvalue()>=0.14&&ScrollPane.getHvalue()<0.48) {
                    circle2.setFill(Color.web("#9F73AB"));
                    circle4.setFill(Color.WHITE);
                    circle3.setFill(Color.WHITE);
                    circle1.setFill(Color.WHITE);
                }
                else if(ScrollPane.getHvalue()>=0.48&&ScrollPane.getHvalue()<=0.82) {
                    circle3.setFill(Color.web("#9F73AB"));
                    circle4.setFill(Color.WHITE);
                    circle2.setFill(Color.WHITE);
                    circle1.setFill(Color.WHITE);
                }
                else {
                    circle4.setFill(Color.web("#9F73AB"));
                    circle3.setFill(Color.WHITE);
                    circle2.setFill(Color.WHITE);
                    circle1.setFill(Color.WHITE);
                }
                break;
            case 3 :
                if(ScrollPane.getHvalue()>=0&&ScrollPane.getHvalue()<0.21) {
                    circle1.setFill(Color.web("#9F73AB"));
                    circle2.setFill(Color.WHITE);
                    circle3.setFill(Color.WHITE);
                }
                else if(ScrollPane.getHvalue()>0.21&&ScrollPane.getHvalue()<0.71) {
                    circle2.setFill(Color.web("#9F73AB"));
                    circle3.setFill(Color.WHITE);
                    circle1.setFill(Color.WHITE);
                }
                else {
                    circle3.setFill(Color.web("#9F73AB"));
                    circle2.setFill(Color.WHITE);
                    circle1.setFill(Color.WHITE);
                }
                break;
            case 2 :
                if(ScrollPane.getHvalue()>=0.8) {
                    circle1.setFill(Color.WHITE);
                    circle2.setFill(Color.web("#9F73AB"));
                }
                else if (ScrollPane.getHvalue()<=0.2) {
                    circle2.setFill(Color.WHITE);
                    circle1.setFill(Color.web("#9F73AB"));
                }
                break;
        }
    }
    /**
     * This method is called when the user clicks on a specific element on the user interface.
     * It sets the stroke width of the rectangle to 4, indicating that it has been selected, and disables the delete button.
     * If another rectangle had previously been selected, its stroke width is set back to 0.
     * @param e the MouseEvent that triggers the method
     */
    @FXML
    private void DeleteOption(MouseEvent e) {
        Delete.setDisable(!Delete.isDisabled());
        n= (Pane) e.getSource();
        newrectangle = (Rectangle) n.getChildren().get(0);
        newrectangle.setStrokeWidth(4);
        if(oldrectangle!=null) {
            oldrectangle.setStrokeWidth(newrectangle.getStrokeWidth()==4?0:4);
        }
        oldrectangle=newrectangle;
    }
    /**
     * This method is called when the user clicks on the delete button.
     * It sets the visibility of the selected rectangle to false and disables the delete button.
     */
    @FXML
    private void handleDelete() {
        n.setVisible(false);
        Delete.setDisable(true);
    }
    @FXML
    private void testAdd() {
    }
    /**
     * Initializes the PaymentController class with the necessary resources.
     * @param location The location of the FXML file that this controller is associated with.
     * @param resources The resources used to initialize the controller.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // sets the payment image based on the user's job
        paymentimage.setImage(new Image(getuJob()=="Driver"?"com/Images/dinim3akalpha001/menupayment.png":"com/Images/dinim3akalpha001/menupaymentrider.png"));
        // gets the collection of cards from the MongoDB database
        MongoCollection<Document> rides = db.getCollection("cards");
        FindIterable<Document> iterable = rides.find(eq("userid",getuID()));
        MongoCursor<Document> cursor = iterable.iterator();
        // iterates through the cards and adds them to the AnchorPane
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            AnchorPane.getChildren().add(new CreditCardView(doc.getString("name"),doc.getString("number")));
        }
        // sets the opacity of the circles based on the number of card
        circle1.setOpacity(AnchorPane.getChildren().size()>=1?1:0.3);
        circle2.setOpacity(AnchorPane.getChildren().size()>=2?1:0.3);
        circle3.setOpacity(AnchorPane.getChildren().size()>=3?1:0.3);
        circle4.setOpacity(AnchorPane.getChildren().size()>=4?1:0.3);
    }

    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"ProfileDriver.fxml":"ProfileRider.fxml",circle1);
    }
    @FXML
    private void handleVehicle() throws IOException {
        if (getuJob().equals("Driver")) {
            new DiniController().handleScenes("Car.fxml", circle1);
        }
        else {
            Arrow12.setTooltip(new Tooltip("Switch to Driver to access this feature."));
            showTooltip((Stage) Arrow12.getScene().getWindow(),Arrow12,"Switch to Driver to access this feature.",null);
        }
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",circle1);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentAdd.fxml",circle1);
    }
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",circle1);
    }
}
