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

public class HomeRiderController implements Initializable, MapComponentInitializedListener {
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
            MongoCollection<Document> rides = db.getCollection("rides");
            FindIterable<Document> iterable = rides.find();
            if (viewmore.getText().equals("Show More")){
                iterable.limit(2);}
            IterateClients(iterable);
        }
    }

    private void IterateClients(FindIterable<Document> iterable) {
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            vbox.getChildren().add(new ClientTableView(doc.getString("source"),doc.getString("clientname"),doc.getString("destination"),!cursor.hasNext(),doc.getString("ride description")));
        }
    }

    @FXML
    private void HandlePostRide() throws IOException {
        new DiniController().handleScenes("AddRide.fxml",mapView);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",mapView);
    }
    @FXML
    private void handleProfile() throws IOException {
        new DiniController().handleScenes("ProfileRider.fxml",mapView);
    }
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",mapView);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInitializedListener(this);
        MongoCollection<Document> rides = db.getCollection("rides");
        FindIterable<Document> iterable = rides.find().limit(2);
        IterateClients(iterable);
        ReturnDesc.addListener((observable, oldValue, newValue) -> {
            NotePane.setVisible(true);
            textarea.setText(newValue);
        });
    }

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
