package com.example.dinim3akalpha001;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.example.dinim3akalpha001.PaymentController.addDashes;

/**
 * CreditCardView is a class that extends Pane and creates a visual representation of a credit card.
 * It takes a name and a number as arguments and displays them on the credit card.
 @author Youssef & Sami
 */
public class CreditCardView extends Pane {
    /**
     * Constructs a new CreditCardView with the given name and number.
     * @param name the name to be displayed on the credit card
     * @param number the number to be displayed on the credit card
     */
    CreditCardView(String name, String number) {
        setPrefHeight(158.16);
        setPrefWidth(272.54);

        /**
         * The rectangle representing the credit card
         */
        Rectangle rectangle = new Rectangle(272.54, 158.16);
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        rectangle.setStrokeType(StrokeType.INSIDE);

        LinearGradient fillGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#624f82FF")), new Stop(1, Color.web("#624f82FF")), new Stop(0.5, Color.web("#ff8ca4FF")));
        rectangle.setFill(fillGradient);

        LinearGradient strokeGradient = new LinearGradient(0.27014218009478674, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#624f82")), new Stop(0.006711409395973154, Color.web("#624f82")), new Stop(0.5067114093959731, Color.web("#ff8ca4")), new Stop(1, Color.web("#ff8ca4")));
        rectangle.setStroke(strokeGradient);

        Text mainText1 = new Text(name);
        mainText1.setFill(Color.web("#ffffff99"));
        mainText1.setLayoutX(22);
        mainText1.setLayoutY(109);
        mainText1.setStrokeType(StrokeType.OUTSIDE);
        mainText1.setFont(new Font(14));

        Text mainText3 = new Text(addDashes(number));
        mainText3.setFill(Color.web("#ffffff99"));
        mainText3.setLayoutX(22);
        mainText3.setLayoutY(128);
        mainText3.setStrokeType(StrokeType.OUTSIDE);
        mainText3.setFont(new Font(14));

        Text cpny1 = new Text("CPNY");
        cpny1.setFill(Color.web("#ffffff99"));
        cpny1.setLayoutX(198);
        cpny1.setLayoutY(35);
        cpny1.setStrokeType(StrokeType.OUTSIDE);
        cpny1.setFont(new Font(26));

        getChildren().addAll(rectangle, mainText1, mainText3, cpny1);
    }
}
