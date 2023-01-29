package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.bson.Document;

import java.io.IOException;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * The LoginController class is responsible for handling the login functionality of the application.
 * It connects to the MongoDB database and verifies the entered email and password with the stored data.
 * If the email and password match, it redirects the user to the appropriate home page (either for riders or drivers).
 * If the email and password do not match, it shows an error message to the user.
 * Additionally, it also handles the functionality for signing up and forgot password.
 * @author Example
 */

public class LoginController {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField pass_hidden;
    @FXML
    private ImageView wrong_email_icon;
    @FXML
    private Label wrong_email;

    /**
     * This method is called when the login button is clicked. It gets the email and password entered by the user,
     * connects to the MongoDB database and verifies the entered email and password with the stored data.
     * If the email and password match, it redirects the user to the appropriate home page (either for riders or drivers).
     * If the email and password do not match, it shows an error message to the user.
     *
     * @param event the event that triggered the method call (in this case, the login button being clicked)
     * @throws IOException if there is an error in loading the new scene
     */
    @FXML
    private void Login(ActionEvent event) throws IOException {
        String email = Email.getText();
        System.out.println(email);
        String password = pass_hidden.getText();
        MongoCollection<Document> collection = db.getCollection("users");
        Document user = collection.find(and(eq("email",email),eq("password",password))).first();
        if(user != null) {
            if (user.getString("job").equals("Driver")) {
                new DiniController().handleScenes("HomeDriver.fxml", Email);
            } else {
                new DiniController().handleScenes("HomeRider.fxml", Email);
            }
        }
        else{
        wrong_email_icon.setVisible(true);
        wrong_email.setVisible(true);
        }
        setuJob(user.getString("job"));
        setuEmail(email);
        setuPass(password);
        setuID(user.getString("_id"));
        setuName(user.getString("fullname"));
    }
    /**
     * This method is called when the signup button is clicked. It redirects the user to the Signup page.
     * @param event the event that triggered the method call (in this case, the signup button being clicked)
     * @throws IOException if there is an error in loading the new scene
     */
    @FXML
    private void Signup(ActionEvent event) throws IOException {
        new DiniController().handleScenes("Signup.fxml",Email);
    }

    /**
     * This method is called when the forgot password button is clicked. It redirects the user to the SignUp page.
     * @param event the event that triggered the method call (in this case, the forgot password
     */
    @FXML
    private void Forgot(ActionEvent event) throws IOException {
        new DiniController().handleScenes("Signup.fxml",Email);
    }

}









