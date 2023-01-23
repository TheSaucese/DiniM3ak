package com.example.dinim3akalpha001;


import com.example.dinim3akalpha001.javascript.object.*;
import com.example.dinim3akalpha001.service.directions.*;
import com.example.dinim3akalpha001.service.geocoding.GeocodingService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.mongodb.client.model.Filters.eq;

public class HomeDriverController implements Initializable, MapComponentInitializedListener {

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    protected DirectionsRenderer directionsRenderer = null;

    @FXML
    protected GoogleMapView mapView;

    private GeocodingService geocodingService;

    @FXML
    private VBox vbox;
    @FXML
    private Text ClientName;
    @FXML
    private Text DestinationName;

    @FXML
    private void getCoords() {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInitializedListener(this);
        MongoCollection<Document> rides = db.getCollection("rides");
        FindIterable<Document> iterable = rides.find().limit(2);
        MongoCursor<Document> cursor = iterable.iterator();
        boolean seconditem = false;
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            //String locationText,String ClientText,String DestinationText
            vbox.getChildren().add(ClientTableView.createPane(doc.getString("source"),doc.getString("clientname"),doc.getString("destination"),seconditem));
            // Use the data from the document to create a JavaFX component
            // and add it to the FXML file/JavaFX scene
            seconditem = true;
        }
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