package com.example.dinim3akalpha001;

import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

import static com.example.dinim3akalpha001.MongoController.db;

/**
 * SignupController2 class is responsible for handling the registration process after the user enters their email and password.
 * It sets and gets the email, password, job, ID, and name of the user. It also checks for the validity of the phone number
 * entered by the user and the length of the full name entered by the user. If the phone number and full name are valid,
 * the user's information is added to the MongoDB collection "users" and the user is directed to the appropriate home page
 */
public class SignupController2 {
    @FXML
    private TextField fullName;
    @FXML
    private TextField phoneNumber;
    static private String uEmail;
    static private String uPass;
    static private String uJob;
    static private String uID;
    static private String uName;
    /**
     * A regular expression string that is used to check the format of phone numbers
     */
    String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
    /**
     * setuEmail is a setter method that sets the email of the user
     * @param uEmail the email of the user
     */
    public static void setuEmail(String uEmail) {
        SignupController2.uEmail = uEmail;
    }

    public static void setuPass(String uPass) {
        SignupController2.uPass = uPass;
    }

    public static void setuJob(String uJob) {
        SignupController2.uJob = uJob;
    }

    public static void setuID(String uID) { SignupController2.uID = uID;}

    public static void setuName(String uName) {
        SignupController2.uName = uName;
    }

    public static String getuEmail() {
        return uEmail;
    }

    public static String getuPass() {
        return uPass;
    }

    public static String getuJob() {
        return uJob;
    }

    public static String getuID() { return uID;}

    public static String getuName() {
        return uName;
    }

    /**
     * This method is used to finish the registration process for the user.
     * It checks if the phone number entered by the user matches the given regular expression,
     * and if the length of the full name entered by the user is greater than or equal to 3.
     * If these conditions are met, it creates a new user document, populates it with the user's information,
     * and inserts it into the "users" collection in the MongoDB database.
     * It then calls the handleScenes method of the DiniController class to navigate to the appropriate home page for the user.
     * @throws Exception If an exception is thrown while handling the scenes
     */
    @FXML
    private void finishRegistration() throws Exception {

        if(!phoneNumber.getText().matches(regexStr)){

        }
        else if(fullName.getText().length()<3) {

        }
        else {
            List<String> stringList = Arrays.asList("");
            setuID(new ObjectId().toString());
            Document user = new Document("_id", getuID());
            user.append("email",uEmail)
                    .append("password",uPass)
                            .append("phonenumber", phoneNumber.getText())
                                    .append("fullname", fullName.getText())
                                            .append("job", uJob)
                                                    .append("stars",0.0)
                                                            .append("numcards",0)
                                                                    .append("notification",stringList)
                                                                            .append("history",stringList);
            db.getCollection("users").insertOne(user);
            new DiniController().handleScenes(uJob.equals("Driver") ? "HomeDriver.fxml" : "HomeRider.fxml", fullName);
        }
    }
    }

