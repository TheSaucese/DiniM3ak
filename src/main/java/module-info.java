open module com.exampe.dinim3akalpha001 {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.swing;

    requires jdk.jsobject;
    requires java.logging;
    requires java.desktop;
    requires org.slf4j;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires java.mail;
    requires stripe.java;

    exports com.example.dinim3akalpha001;
    exports com.example.dinim3akalpha001.javascript;
    exports com.example.dinim3akalpha001.javascript.event;
    exports com.example.dinim3akalpha001.javascript.object;
    exports com.example.dinim3akalpha001.service.directions;
    exports com.example.dinim3akalpha001.service.elevation;
    exports com.example.dinim3akalpha001.service.geocoding;
    exports com.example.dinim3akalpha001.shapes;
    exports com.example.dinim3akalpha001.util;
    exports com.example.dinim3akalpha001.zoom;
}