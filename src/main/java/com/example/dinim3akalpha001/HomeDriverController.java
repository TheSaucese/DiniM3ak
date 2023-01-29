package com.example.dinim3akalpha001;


import com.example.dinim3akalpha001.javascript.object.*;
import com.example.dinim3akalpha001.service.directions.DirectionsRenderer;
import com.example.dinim3akalpha001.service.directions.DirectionsService;
import com.example.dinim3akalpha001.service.geocoding.GeocodingService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.ClientTableView.ReturnDesc;
import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.getuID;

/**
 * The HomeDriverController class is responsible for handling the home driver scene.
 * It includes methods for displaying a map, displaying client information,
 * handling 'post a ride' button and more.
 * It implements Initializable and MapComponentInitializedListener interfaces.
 * It uses GoogleMapView, TranslateTransition, Timeline, GeocodingService, MongoCollection and MongoCursor classes.
 * It uses FXML annotations to handle the UI elements.
 * It has several methods which handle the UI elements actions, like closeNotes,handleSmores and HandlePostRide.
 * It has a method IterateClients which is used to iterate over the clients data in MongoDB.
 *
 * @author Dinim3akalpha001
 * @version 1.0
 */
public class HomeDriverController implements Initializable, MapComponentInitializedListener {

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    protected DirectionsRenderer directionsRenderer = null;

    @FXML
    protected GoogleMapView mapView;

    private TranslateTransition translate;
    private Timeline timeline = new Timeline();

    private GeocodingService geocodingService;

    @FXML
    private Rectangle ClientRectangle;

    @FXML
    private VBox vbox;
    @FXML
    private Text ClientName,DestinationName;

    @FXML
    private Pane NotePane;

    @FXML
    private Button PostaRide,viewmore;

    @FXML
    private TextArea textarea;

    @FXML
    private void closeNotes() {
        ReturnDesc.set("");
        NotePane.setVisible(false);
    }

    /**
     * handleSmores is a method to display more ride requests , in case of 'Show less' it shows only
     * 2 ride requests and in case 'show more' it displays more ride requests , in parallel it hides
     * googleMap rectangle and also the button of 'Post a ride'
     */
    @FXML
    private void handleSmores() {
        if (translate==null||!translate.getStatus().equals(Animation.Status.RUNNING)) {
            ClientRectangle.setHeight(viewmore.getText().equals("Show Less") ? 219 : 608);
            translate = new TranslateTransition(Duration.seconds(0.5), mapView);
            translate.setByY(viewmore.getText().equals("Show Less") ? -430 : 430);
            translate.play();
            translate = new TranslateTransition(Duration.seconds(0.5), PostaRide);
            translate.setByY(viewmore.getText().equals("Show Less") ? -430 : 430);
            translate.play();
            viewmore.setText(viewmore.getText().equals("Show Less") ? "Show More" : "Show Less");
            vbox.getChildren().clear();
            MongoCollection<Document> requests = db.getCollection("requests");
            FindIterable<Document> iterable = requests.find();
            if (viewmore.getText().equals("Show More")){
                iterable.limit(2);}
            IterateClients(iterable);
        }
    }

    /**
     Iterates through the clients in the MongoDB collection and adds them to the UI table.
     @param iterable The MongoDB collection to iterate through.
     */
    private void IterateClients(FindIterable<Document> iterable) {
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            if(!doc.getString("userid").equals(getuID())) {
                vbox.getChildren().add(new ClientTableView(doc.getString("source"), doc.getString("user"), doc.getString("destination"), !cursor.hasNext(), doc.getString("description"), doc.getString("price"), doc.getString("time"), doc.getObjectId("_id"), vbox, doc.getString("userid")));
            }
            }
    }

    @FXML
    private void HandlePostRide() throws IOException {
        new DiniController().handleScenes("AddRide.fxml",mapView);
    }

    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes("Car.fxml",mapView);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",mapView);
    }
    @FXML
    private void handleProfile() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",mapView);
    }
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",mapView);
    }

    /**
     * Initializes the controller class. This method is called automatically when the FXML file is loaded.
     * It sets up the map view, retrieves a limited number of rides from the "rides" collection in MongoDB,
     * and calls the IterateClients() method to iterate through the retrieved documents. It also adds a listener
     * to the ReturnDesc property, which makes the NotePane visible and sets the text of the textarea to the newValue
     * of the property when the property changes.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInitializedListener(this);
        MongoCollection<Document> requests = db.getCollection("requests");
        FindIterable<Document> iterable = requests.find().limit(2);
        IterateClients(iterable);
        ReturnDesc.addListener((observable, oldValue, newValue) -> {
            NotePane.setVisible(true);
            textarea.setText(newValue);
        });
    }

    /**
     * This method initializes the Google Map on the GUI. It sets the map's center location, zoom level, and map type.
     * It also creates a new instance of the GeocodingService and DirectionsService. The directionsPane variable is also initialized.
     */
    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        geocodingService = new GeocodingService();
        options.center(new LatLong(47.606189, -122.335842))
                .mapMaker(true)
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }

}