package com.example.dinim3akalpha001;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.dinim3akalpha001.DiniController.showTooltip;
import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
/**
 * PaymentController is a class that handles the payment functionality for the application.
 * It is responsible for handling user input for card information, validating the input,
 * and updating the information in the database.
 */


public class PaymentController implements Initializable {
    @FXML
    private ImageView CardNameC,CardNumC,DateC,CVVC;
    @FXML
    private Button Arrow,Vehicle;
    @FXML
    private TextField InputCardNumber,InputDate,InputCardName,InputCVV;
    @FXML
    private Pane ChangesSaved;
    @FXML
    private Label MainTextCard;
    @FXML
    private ImageView paymentimage;
    private TranslateTransition translateTransition;
    private FadeTransition fadeTransition;
    private String ID;
    String NumberRegex = "[0-9]{16}";
    String CVVRegex ="[0-9]{3}";
    String DateRegex = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$";
    String NameRegex = "[a-zA-Z]{3,30}";

    int memoryindex=0;
    /**
     * Handles the arrow button action.
     * @throws IOException if there is a problem loading the next scene.
     */
    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Arrow);
    }
    /**
     * Handles the vehicle button action.
     * @throws IOException if there is a problem loading the next scene.
     */
    @FXML
    private void handleVehicle() throws IOException {
        if (getuJob().equals("Driver")) {
            new DiniController().handleScenes("Car.fxml", Arrow);
        }
        else {
            Vehicle.setTooltip(new Tooltip("Switch to Driver to access this feature."));
            showTooltip((Stage) Vehicle.getScene().getWindow(),Vehicle,"Switch to Driver to access this feature.",null);
        }
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",Vehicle);
    }
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",Vehicle);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",Vehicle);
    }

    /**
     * This method removes all non-numeric characters from the input string.
     * @param input The input string to remove non-numeric characters from.
     * @return The input string with all non-numeric characters removed.
     */
    public static String removeLetters(String input) {
        // Define the regular expression pattern
        Pattern pattern = Pattern.compile("[^0-9]");

        // Create a matcher object
        Matcher matcher = pattern.matcher(input);

        // Replace all non-numeric characters with an empty string
        return matcher.replaceAll("");
    }
    @FXML
    private void CloseUndo() {
        fadeTransition = new FadeTransition(Duration.seconds(1), ChangesSaved);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }
    @FXML
    private void Undo() {
        db.getCollection("cards").deleteOne(eq("_id",ID));
        CloseUndo();
    }

    /**
     * This method is used to handle the adding of a card to the user's account. It first checks if the input card number matches the expected format using a regular expression. If the input card number is invalid, it displays a message indicating a fake card. If the input card name is too short, it displays a message indicating a small card name. If the user already has 4 cards added to their account, it displays a message indicating that the user cannot add any more cards. If all the inputs are valid, it creates a new card document, adds the card details to the database, and updates the user's number of cards. It also clears the input fields and displays a message indicating that the changes have been saved.
     *  InputCardNumber the input field for the card number
     *  InputCardName the input field for the card name
     *  InputDate the input field for the card expiration date
     *  InputCVV the input field for the card CVV
     *  ChangesSaved a label used to indicate that the changes have been saved
     *  db the MongoDB database used to store the card and user information
     *  getuID the method to get the current user's ID
     *  getuEmail the method to get the current user's email
     *  removeLetters a method to remove any letters from the input card number
     *  NumberRegex a regular expression used to check if the input card number matches the expected format
     */
    @FXML
    private void addCard() {
        if (!removeLetters(InputCardNumber.getText()).matches(NumberRegex)) {
            // Fake card
        } else if (InputCardName.getText().length() < 6) {
            // Smol cardname
        }
        else if (db.getCollection("users").find(eq("email",getuEmail())).first().getInteger("numcards")==4) {
            //Cannot add more
        }else {
            ID = new ObjectId().toString();
            Document card = new Document("_id",ID);
            card.append("number", removeLetters(InputCardNumber.getText()))
                    .append("name", InputCardName.getText())
                    .append("date", InputDate.getText())
                    .append("cvv", InputCVV.getText())
                    .append("userid",getuID());
            db.getCollection("cards").insertOne(card);
            Document user = db.getCollection("users").find(eq("email",getuEmail())).first();
            db.getCollection("users").updateOne(eq("email", getuEmail()), set("numcards",user.getInteger("numcards")+1));
            InputCVV.setText("");
            InputCardName.setText("");
            InputDate.setText("");
            InputCardNumber.setText("");
            translateTransition = new TranslateTransition(Duration.seconds(0.5), ChangesSaved);
            translateTransition.setByY(-60);
            translateTransition.play();
        }
    }
    /**
     * Formats the date input in the InputDate text field by adding a '/' after the first two characters.
     */
    @FXML
    private void FormatDate() {
        if (InputDate.getText().length() >= 4) {
            InputDate.setText(InputDate.getText().substring(0, 2) + "/" + InputDate.getText().substring(2));
        }
    }
    /**
     * Removes any dashes and spaces from the input string.
     * @param input The input string to remove dashes and spaces from.
     * @return The input string with all dashes and spaces removed.
     */
    private static String removeDashesAndSpaces(String input) {
        return input.replaceAll("[-\\s]", "");
    }

    /**
     * This method formats the card number by adding dashes every 4 characters.
     * @param input a string representation of the card number
     * @return the formatted card number with dashes every 4 characters
     */
    public static String addDashes(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));
            if ((i + 1) % 4 == 0 && i != input.length() - 1) {
                sb.append(" - ");
            }
        }
        return sb.toString();
    }

    /**
     * This method formats the card number by adding dashes and X for masking.
     * @param cardNumber a string representation of the card number
     */
    private void formatCardNumber(String cardNumber) {
        String text = removeDashesAndSpaces(MainTextCard.getText());
        StringBuilder sb = new StringBuilder(text);
            if (cardNumber.equals("\b")) {
                if (memoryindex!=-1) {
                    sb.replace(memoryindex, memoryindex + 1, "X");
                    memoryindex--;
                }
            }
            else {
                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) == 'X') {
                        memoryindex = i;
                        sb.replace(memoryindex, memoryindex+1, cardNumber);
                        break;
                    }
                }
            }
        MainTextCard.setText(addDashes(sb.toString()));
    }

    /**
     * handleNumberErrors checks if the input card number matches a specific regex pattern.
     * @param event
     */
    @FXML
    private void handleNumberErrors(KeyEvent event) {
        formatCardNumber(event.getCharacter());
        CardNumC.setVisible(removeLetters(InputCardNumber.getText()).matches(NumberRegex));
    }

    /**
     * handleNameErrors checks if the input card name matches a specific regex pattern.
     * @param event
     */
    @FXML
    private void handleNameErrors(KeyEvent event) {
        CardNameC.setVisible(InputCardName.getText().matches(NameRegex));
    }

    /**
     * handleDateErrors checks if the input card expiration date matches a specific regex pattern.
     * @param event
     */
    @FXML
    private void handleDateErrors(KeyEvent event) {
        DateC.setVisible(InputDate.getText().matches(DateRegex));
    }

    /**
     * handleCVVErrors checks if the input card CVV matches a specific regex pattern.
     * @param event
     */
    @FXML
    private void handleCVVErrors(KeyEvent event) {
        CVVC.setVisible(InputCVV.getText().matches(CVVRegex));
    }

    /**
     * The private method TextFormatter sets a text formatter for a given text field with a given maximum character limit.
     */
    private void TextFormatter(TextField n, int x) {
        n.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= x ? change : null));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paymentimage.setImage(new Image(getuJob()=="Driver"?"com/Images/dinim3akalpha001/menupayment.png":"com/Images/dinim3akalpha001/menupaymentrider.png"));
        TextFormatter(InputCVV,3);
        TextFormatter(InputDate,4);
        TextFormatter(InputCardName,30);
        TextFormatter(InputCardNumber,16);
    }
}
