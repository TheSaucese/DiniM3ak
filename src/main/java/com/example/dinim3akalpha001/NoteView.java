package com.example.dinim3akalpha001;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * This class represents the NoteView pane that displays notes to the user.
 * It creates a Rectangle, TextArea, and a Button and adds them to the Pane.
 * The TextArea displays the notes passed to the constructor as a parameter.
 * The Button is used to close the notes.
 */
public class NoteView extends Pane {
    NoteView(String notes) {
        setId("NotePane");
        setPrefHeight(262);
        setPrefWidth(375);

        Rectangle shadow = new Rectangle();
        shadow.setId("Shadow");
        shadow.setFill(Color.web("#14132498"));
        shadow.setHeight(812);
        shadow.setLayoutY(-276);
        shadow.setStroke(Color.BLACK);
        shadow.setStrokeType(StrokeType.INSIDE);
        shadow.setWidth(375);

        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        rectangle.setHeight(80);
        rectangle.setLayoutX(323);
        rectangle.setLayoutY(4);
        rectangle.setRotate(130);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setWidth(4);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setLayoutX(12);
        textArea.setLayoutY(17);
        textArea.setPrefHeight(225);
        textArea.setPrefWidth(345);
        textArea.setStyle("-fx-background-color: transparent; -fx-border-color: White; -fx-border-radius: 20px; -fx-border-width: 4px; -fx-text-fill: White;");
        textArea.setText(notes);

        Button button = new Button();
        button.setId("CloseNotes");
        button.setLayoutX(328);
        button.setLayoutY(29);
        button.setMaxHeight(15);
        button.setMaxWidth(15);
        button.setMinHeight(15);
        button.setMinWidth(15);
        button.setMnemonicParsing(false);
        button.setPrefHeight(15);
        button.setPrefWidth(15);

        getChildren().addAll(shadow,rectangle,textArea,button);
    }
}
