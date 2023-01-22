package com.example.dinim3akalpha001;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

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
    private TranslateTransition translateTransition;
    private FadeTransition fadeTransition;
    private String ID;
    String NumberRegex = "[0-9]{16}";
    String CVVRegex ="[0-9]{3}";
    String DateRegex = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$";
    String NameRegex = "[a-zA-Z]{3,30}";

    int memoryindex=0;
    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Arrow);
    }
    @FXML
    private void handleVehicle() throws IOException {
        new DiniController().handleScenes("Car.fxml",Vehicle);
    }
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",Vehicle);
    }
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",Vehicle);
    }
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
    @FXML
    private void addCard() {
        if (!removeLetters(InputCardNumber.getText()).matches(NumberRegex)) {

        } else if (InputCardName.getText().length() < 6) {

        } else {
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
            translateTransition.setByY(-42);
            translateTransition.play();
        }
    }
    @FXML
    private void FormatDate() {
        if (InputDate.getText().length() >= 4) {
            InputDate.setText(InputDate.getText().substring(0, 2) + "/" + InputDate.getText().substring(2));
        }
    }
    private static String removeDashesAndSpaces(String input) {
        return input.replaceAll("[-\\s]", "");
    }
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

    @FXML
    private void handleNumberErrors(KeyEvent event) {
        formatCardNumber(event.getCharacter());
        CardNumC.setVisible(removeLetters(InputCardNumber.getText()).matches(NumberRegex));
    }
    @FXML
    private void handleNameErrors(KeyEvent event) {
        CardNameC.setVisible(InputCardName.getText().matches(NameRegex));
    }
    @FXML
    private void handleDateErrors(KeyEvent event) {
        DateC.setVisible(InputDate.getText().matches(DateRegex));
    }
    @FXML
    private void handleCVVErrors(KeyEvent event) {
        CVVC.setVisible(InputCVV.getText().matches(CVVRegex));
    }
    private void TextFormatter(TextField n, int x) {
        n.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= x ? change : null));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFormatter(InputCVV,3);
        TextFormatter(InputDate,4);
        TextFormatter(InputCardName,30);
        TextFormatter(InputCardNumber,16);
    }
}
