package com.example.chemistryapp.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserGuideView {
    public VBox initializeUserGuide() {
        Label title = new Label("User Guide");
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");


        Button molecule = new Button("How to build a molecule?");
        Button equation = new Button("How to build an equation?");
        Button heatTransfer = new Button("How to calculate heat transfer?");
        Button acidityAndBasicity = new Button("How to calculate pH and poH?");
        Button molecule3D = new Button("How to visualize a 3D molecule?");
        Button password = new Button("How to change your password?");

        VBox buttonsVbox = new VBox();
        buttonsVbox.getChildren().addAll(molecule, equation, heatTransfer, acidityAndBasicity, molecule3D, password);
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.setPadding(new Insets(20));
        buttonsVbox.setSpacing(10);

        VBox root = new VBox(title, buttonsVbox);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add("userGuide.css");

        return root;
    }
}
