package com.example.chemistryapp.View;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class PHView {
    public VBox pHCalcInitialize() {
        VBox root = new VBox();

        // ComboBox to allow user to choose the type of conversion they want
        ComboBox<String> conversionChoice = new ComboBox<>();
        conversionChoice.getItems().addAll("pH -> pOH", "pOH -> pH");
        conversionChoice.setPromptText("--Choose Conversion Type--");

        TextField userInput = new TextField();
        userInput.setPromptText("Please select conversion type first.");

        TextField result = new TextField();

        Button solve = new Button("Solve");

        root.getChildren().addAll(conversionChoice, userInput, result, solve);


        // Based on the chosen conversion type, the window's functionalities will change
        conversionChoice.setOnAction(e ->
        {
            String selectedConversion = conversionChoice.getSelectionModel().getSelectedItem();

            if ("pH -> pOH".equals(selectedConversion)){
                userInput.setPromptText("Enter pH");

                // solve.setOnAction(e -> formulaForpHConversion);
            }

            else if ("pOH -> pH".equals(selectedConversion)) {
                userInput.setPromptText("Enter pOH");

                // solve.setOnAction(e -> formulaForpHConversion);
            }
        });

        return root;
    }
}
