package com.example.dinim3akalpha001;

import com.mongodb.SubjectProvider;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.example.dinim3akalpha001.SignupController2.getuID;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.getuJob;

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
    private Button Delete;
    private Pane n;
    private Rectangle oldrectangle;
    private Rectangle newrectangle;
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
    @FXML
    private void handleDelete() {
        n.setVisible(false);
        Delete.setDisable(true);
    }
    @FXML
    private void testAdd() {
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        MongoCollection<Document> rides = db.getCollection("cards");
        FindIterable<Document> iterable = rides.find(eq("userid",getuID()));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            AnchorPane.getChildren().add(new CreditCardView(doc.getString("name"),doc.getString("number")));
        }
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
        new DiniController().handleScenes(getuJob().equals("Driver")?"Car.fxml":"Placeholder.fxl",circle1);
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",circle1);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentAdd.fxml",circle1);
    }
}
