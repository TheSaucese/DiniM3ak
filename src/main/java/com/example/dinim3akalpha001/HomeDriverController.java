package com.example.dinim3akalpha001;


import com.example.dinim3akalpha001.javascript.object.*;
import com.example.dinim3akalpha001.service.directions.*;
import com.example.dinim3akalpha001.service.geocoding.GeocodingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private void getCoords() {

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