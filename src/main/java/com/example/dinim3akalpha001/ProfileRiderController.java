package com.example.dinim3akalpha001;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static com.example.dinim3akalpha001.DiniController.showTooltip;
import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.SignupController2.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
/**
 * The ProfileRiderController class is used to handle the actions and events
 * related to the profile page for a rider. It includes methods for uploading
 * a profile picture, handling vehicle and payment options, and saving changes
 * to the user's profile.
 */

public class ProfileRiderController implements Initializable {
    @FXML
    private Circle Photo;
    @FXML
    private TextField Username;
    static GridFSBucket gridBucket = GridFSBuckets.create(db);
    private TranslateTransition translateTransition;
    private FadeTransition fadeTransition;
    private String oldUsername;
    @FXML
    private Pane ChangesSaved,SaveChanges;
    @FXML
    private Button Vehicle;
    private boolean hasTransitioned=false;
    /**
     * The Upload method is used to open a file chooser and allow the user to select
     * a profile picture to upload. It then calls the mongoupload and saveToFileSystem
     * methods to handle the uploading and saving of the image.
     * @throws IOException if there is an error with the file input/output.
     */
    @FXML
    private void Upload() throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            mongoupload(file.getPath(),file.getName());
            saveToFileSystem(file.getName());

        }
    }
    @FXML
    private void handleVehicle() throws IOException {
        Vehicle.setTooltip(new Tooltip("Switch to Driver to access this feature."));
        showTooltip((Stage) Vehicle.getScene().getWindow(),Vehicle,"Switch to Driver to access this feature.",null);
    }

    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",Username);
    }
    @FXML
    private void handleMore() throws IOException {
        //new DiniController().handleScenes("PaymentAdd.fxml",Username);
        //UNFINISHED :(
    }
    /**
     * This method is used to change the name of a user.
     * It sets the SaveChanges button to be visible, and plays
     * a fade and translate transition on the button. The variable
     * hasTransitioned is used to determine the direction of the
     * translate transition.
     */
    @FXML
    private void changename() {
        SaveChanges.setVisible(true);
        fadeTransition = new FadeTransition(Duration.seconds(0.0001), SaveChanges);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        translateTransition = new TranslateTransition(Duration.seconds(0.5), SaveChanges);
        translateTransition.setByY(hasTransitioned?0:-60);
        translateTransition.play();
        hasTransitioned=true;
    }

    /**
     * The method SaveChanges() is used to save changes made to the user's name.
     */
    @FXML
    private void SaveChanges() {
        fadeTransition = new FadeTransition(Duration.seconds(0.25), SaveChanges);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
        fadeTransition = new FadeTransition(Duration.seconds(0.0001), ChangesSaved);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
        translateTransition = new TranslateTransition(Duration.seconds(0.5), ChangesSaved);
        translateTransition.setByY(-60);
        translateTransition.play();
        oldUsername=db.getCollection("users").find(eq("email", getuEmail())).first().getString("fullname");
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("fullname",Username.getText()));
        SaveChanges.setVisible(false);
    }

    /**
     * This method is used to cancel the changes made to the user's name.
     * It animates the SaveChanges button by fading it out and moving it up,
     * then sets the text of the username field to the old value stored in the database.
     */
    @FXML
    private void CancelChanges() {
        fadeTransition = new FadeTransition(Duration.seconds(1), SaveChanges);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
        translateTransition = new TranslateTransition(Duration.seconds(0.5), SaveChanges);
        translateTransition.setByY(60);
        translateTransition.play();
        hasTransitioned=false;
        Username.setText(db.getCollection("users").find(eq("email", getuEmail())).first().getString("fullname"));
    }

    /**
     * The CloseUndo method is used to close the changes saved notification and hide it from the user's view.
     * This method sets the visibility of the SaveChanges element to false and applies both a fade and a translate transition to the ChangesSaved element.
     * The fade transition makes the ChangesSaved element become invisible over the course of 1 second, and the translate transition moves the element up by 60 units.
     */
    @FXML
    private void CloseUndo() {
        SaveChanges.setVisible(false);
        fadeTransition = new FadeTransition(Duration.seconds(1), ChangesSaved);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
        translateTransition = new TranslateTransition(Duration.seconds(0.5), ChangesSaved);
        translateTransition.setByY(60);
        translateTransition.play();
    }

    /**
     * The Undo method is used to undo the changes made to the user's name and update the name in the database to the previous name.
     * The changes made to the UI are also undone by setting the text of the Username field to the previous name and calling the CloseUndo method.
     */
    @FXML
    private void Undo() {
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("fullname",oldUsername));
        Username.setText(oldUsername);
        CloseUndo();
    }
    /**
     * Handle the menu button action.
     * This method is called when the user clicks on the menu button.
     * It navigates to the appropriate home page based on the user's job.
     * @throws IOException if there is an error loading the FXML file
     */
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",Username);
    }
    /**
     * Handle the switch button action.
     * This method is called when the user clicks on the switch button.
     * It changes the user's job in the database and navigates to the profile page for drivers.
     * @throws IOException if there is an error loading the FXML file
     */
    @FXML
    private void handleSwitch() throws IOException {
        setuJob("Driver");
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("job","Driver"));
        new DiniController().handleScenes("ProfileDriver.fxml",Username);
    }
    /**
     * Handle the notifications button action.
     * This method is called when the user clicks on the notifications button.
     * It navigates to the notifications page.
     * @throws IOException if there is an error loading the FXML file
     */
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",Username);
    }

    /**
     * The initialize method is called by the JavaFX framework when the FXML file is loaded.
     * It sets up the TextFormatter for the Username text field to limit the number of characters to 15 and
     * retrieves the user's fullname from the database and sets it as the text for the Username text field.
     * It also checks if the user has an image stored in the database, and if so, it calls the saveToFileSystem method to retrieve it.
     * @param location the location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources the resources used to localize the root object, or null if the root object was not localized.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        UnaryOperator<TextFormatter.Change> modifyChange = c -> {
            if (c.isContentChange()) {
                int newLength = c.getControlNewText().length();
                if (newLength > 15) {
                    String tail = c.getControlNewText().substring(newLength - 15, newLength);
                    c.setText(tail);
                    int oldLength = c.getControlText().length();
                    c.setRange(0, oldLength);
                }
            }
            return c;
        };
        Document user = db.getCollection("users").find(eq("email",getuEmail())).first();
        Username.setTextFormatter(new TextFormatter(modifyChange));
        Username.setText(user.getString("fullname"));
        if (db.getCollection("fs.files").find(eq("_id",user.getObjectId("image"))).first()!=null){
            try {
                saveToFileSystem(db.getCollection("fs.files").find(eq("_id",user.getObjectId("image"))).first().getString("filename"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * This method is used to upload a file to MongoDB using the GridFS bucket.
     * @param filePath The file path of the file to be uploaded.
     * @param fileName The name to be used for the file in MongoDB.
     * @return The ObjectId of the uploaded file, or null if an error occurred.
     */
    public ObjectId mongoupload(String filePath, String fileName) {
        ObjectId fileId = null;
        try {
            InputStream inStream = new FileInputStream(new File(filePath));
            GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024).metadata(new Document("type", "image").append("content_type", "image/png"));
            fileId = gridBucket.uploadFromStream(fileName, inStream, uploadOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("image",fileId));
        return fileId;
    }

    /**
     * The method saveToFileSystem is used to save a file to the file system using the GridFS API.
     * @param fileName the name of the file to be saved
     * @throws IOException if there is an error creating the file or writing to it
     */
    private void saveToFileSystem(String fileName) throws IOException {
        GridFSDownloadOptions downloadOptions = new GridFSDownloadOptions().revision(0);
        File file = new File("c:/DiniM3ak/"+fileName);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        file.createNewFile();
        try (FileOutputStream streamToDownloadTo = new FileOutputStream(file)) {
            gridBucket.downloadToStream(fileName, streamToDownloadTo, downloadOptions);
            streamToDownloadTo.flush();
        }
        Image image = new Image(file.toURI().toString());
        Photo.setFill(new ImagePattern(image));
    }

}
