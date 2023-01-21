package com.example.dinim3akalpha001;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ChangesSavedView extends Pane {
    private Rectangle rectangle;
    private Text text;
    private Button undoButton;

    public ChangesSavedView() {
        rectangle = new Rectangle(375, 60);
        rectangle.setFill(Color.web("#a3c7d6"));
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);

        text = new Text("Changes Saved...");
        text.setFill(Color.WHITE);
        text.setLayoutX(14);
        text.setLayoutY(30);
        text.setStyle("-fx-font-size: 18;");

        undoButton = new Button("UNDO");
        undoButton.setLayoutX(284);
        undoButton.setLayoutY(4);
        undoButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #8B0000; -fx-font-size: 18;");

        getChildren().addAll(rectangle, text, undoButton);
        setStyle("-fx-background-color: transparent;");
        setLayoutY(769);
        setId("ChangesSaved");
    }
}
