module com.example.dinim3akalpha001 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.mail;


    opens com.example.dinim3akalpha001 to javafx.fxml;
    exports com.example.dinim3akalpha001;
}