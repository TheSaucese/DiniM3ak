package com.example.dinim3akalpha001;

import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.dinim3akalpha001.MongoController.db;
import static com.example.dinim3akalpha001.ProfileController.gridBucket;
import static com.example.dinim3akalpha001.SignupController2.getuEmail;
import static com.example.dinim3akalpha001.SignupController2.getuJob;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * The CarController class is responsible for handling the user's car information.
 * It includes methods for applying car name and number, validating car number and name,
 * uploading car image and saving it to the file system.
 */

public class CarController implements Initializable {
    @FXML
    private Button Car;
    @FXML
    private Button Arrow;
    @FXML
    private TextField InputName;
    @FXML
    private TextField InputNumber;
    @FXML
    private ImageView NumberCheck;
    @FXML
    private ImageView NameCheck;


    /**
     * The Apply method is called when the user clicks the Car button.
     * It updates the car name and number in the database for the current user.
     */
    @FXML
    private void Apply() {
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("carname",InputName.getText()));
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("carnumber",InputNumber.getText()));
        InputName.setText("");
        InputNumber.setText("");
    }

    /**
     * The useRegex method is used to check if a given input matches a specific regular expression.
     * @param input the input to check
     * @param regex the regular expression to match against
     * @return true if the input matches the regular expression, false otherwise
     */
    public static boolean useRegex(final String input,String regex) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }
    /**
     * The listenNumber method is called when the user enters a car number in the InputNumber text field.
     * It checks if the car number is valid and displays a checkmark if it is.
     */
    @FXML
    protected void listenNumber() {
        NumberCheck.setVisible(useRegex(InputNumber.getText(), "\\d\\d\\d\\d\\d[a-zA-Z]\\d\\d"));
    }
    /**
     * The listenName method is called when the user enters a car name in the InputName text field.
     * It checks if the car name is valid and displays a checkmark if it is.
     */

    @FXML
    protected void listenName() {
        NameCheck.setVisible(useRegex(InputName.getText(), "[A-Za-z0-9]{2,20}"));
    }

    /**
     * The Upload method is called when the user clicks the upload button.
     * It opens a file chooser to select an image, then uploads the image to the database and saves it to the file system.
     */
    @FXML
    protected void Upload() throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            mongoupload(file.getPath(),file.getName());
            saveToFileSystem(file.getName());
        }
    }

    /**
     * The mongoupload method is used to upload a file to a MongoDB database using the GridFS API.
     * @param filePath  the path of the file to be uploaded
     * @param fileName  the name of the file to be stored in the database
     * @return returns the ObjectId of the uploaded file, which can be used to retrieve the file later.
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
        db.getCollection("users").updateOne(eq("email", getuEmail()), set("carImage",fileId));
        return fileId;
    }

    /**
     * The saveToFileSystem method is used to save the file from the database to the local file system.
     * @param fileName  the name of the file to be retrieved from the database and saved to the file system
     * @throws IOException If an exception is encountered, it will be thrown as a RuntimeException.
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
        ImageView img = new ImageView(image);
        img.fitWidthProperty().bind(Car.widthProperty());
        img.fitHeightProperty().bind(Car.heightProperty());
        Car.setGraphic(img);
    }

    /**
     * The handleArrow method is used to handle button clicks
     * in the user interface and navigate to ProfileDriver screen.
     */
    @FXML
    private void handleArrow() throws IOException {
        new DiniController().handleScenes("ProfileDriver.fxml",Arrow);
    }
    /**
     * The handleNoti method is used to handle button clicks
     * in the user interface and navigate to Notification screen.
     */
    @FXML
    private void handleNoti() throws IOException {
        new DiniController().handleScenes("Noti.fxml",Arrow);
    }
    /**
     * The handlePayment method is used to handle button clicks
     * in the user interface and navigate to PaymentSee screen.
     */
    @FXML
    private void handlePayment() throws IOException {
        new DiniController().handleScenes("PaymentSee.fxml",Arrow);
    }
    /**
     * The handleMenu method is used to handle button clicks
     * in the user interface and navigate to either tp HomeDriver or HomeRider screen.
     */
    @FXML
    private void handleMenu() throws IOException {
        new DiniController().handleScenes(getuJob().equals("Driver")?"HomeDriver.fxml":"HomeRider.fxml",Arrow);
    }


    /**
     * The initialize method is used to initialize the state of the UI elements and retrieve the user's car information from the database.
     * @param location : the location of the FXML file
     * @param resources : the resources used in the UI
     * @exception if an exception is encountered, it will be thrown as a RuntimeException
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Document user = db.getCollection("users").find(eq("email",getuEmail())).first();
        InputNumber.setText(user.getString("carnumber"));
        InputName.setText(user.getString("carname"));
        try {
            saveToFileSystem(db.getCollection("fs.files").find(eq("_id",user.getObjectId("carImage"))).first().getString("filename"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
