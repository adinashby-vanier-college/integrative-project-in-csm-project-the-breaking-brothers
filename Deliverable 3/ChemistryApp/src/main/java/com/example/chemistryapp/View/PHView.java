package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.PHCalculator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class PHView {
    public VBox pHCalcInitialize() {
        PHCalculator phcalc = new PHCalculator();
        VBox root = new VBox(15); // Vertical Spacing of 15
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        // ComboBox to allow user to choose the type of conversion they want
        ComboBox<String> conversionChoice = new ComboBox<>();
        conversionChoice.getItems().addAll("pH -> pOH", "pOH -> pH", "H3O+ Concentration -> pH",
                "pH -> H3O+ Concentration", "OH- Concentration -> pOH", "pOH -> OH- Concentration");
        conversionChoice.setPromptText("--Choose Conversion Type--");
        conversionChoice.setStyle("-fx-font-size: 16px;" + "-fx-background-radius: 5;" + "-fx-border-radius: 5;");
        conversionChoice.setPrefWidth(400);
        conversionChoice.setPrefHeight(40);

        TextField userInput = new TextField();
        userInput.setPromptText("Please select conversion type first.");

        TextField result = new TextField();

        userInput.setMaxWidth(250);
        result.setMaxWidth(250);

        Button solve = styleButton("Solve");

        root.getChildren().addAll(conversionChoice, userInput, result, solve);


        // Based on the chosen conversion type, the window's functionalities will change
        conversionChoice.setOnAction(e ->
        {
            String selectedConversion = conversionChoice.getSelectionModel().getSelectedItem();

            if ("pH -> pOH".equals(selectedConversion)){
                userInput.setPromptText("Enter pH");

                solve.setOnAction(f -> {
                    String convertedResult = ""+ phcalc.convertTopOH(Double.parseDouble(userInput.getText()));
                    result.setText(convertedResult);
                });
            }

            else if ("pOH -> pH".equals(selectedConversion)) {
                userInput.setPromptText("Enter pOH");

                solve.setOnAction(f -> {
                    String convertedResult = ""+ phcalc.convertTopH(Double.parseDouble(userInput.getText()));
                    result.setText(convertedResult);
                });
            }

            else if ("H3O+ Concentration -> pH".equals(selectedConversion)) {
                userInput.setPromptText("Enter H3O+ Concentration");

                solve.setOnAction( f -> {
                    String pH = "" + phcalc.H30ConcentrationTopH(Double.parseDouble(userInput.getText()));
                    result.setText(pH);
                });
            }

            else if ("pH -> H3O+ Concentration".equals(selectedConversion)) {
                userInput.setPromptText("Enter pH");

                solve.setOnAction(f -> {
                    String concentration = phcalc.pHToH30Concentration(Double.parseDouble(userInput.getText())) + " mol/L";
                    result.setText(concentration);
                });
            }

            else if ("OH- Concentration -> pOH".equals(selectedConversion)) {
                userInput.setPromptText("Enter OH- Concentration");

                solve.setOnAction(f -> {
                    String pOH = "" + phcalc.OHConcentrationTopOH(Double.parseDouble(userInput.getText()));
                    result.setText(pOH);
                });
            }

            else if ("pOH -> OH- Concentration".equals(selectedConversion)) {
                userInput.setPromptText("Enter pOH");

                solve.setOnAction(f -> {
                    String concentration = phcalc.pOHToOHConcentration(Double.parseDouble(userInput.getText())) + " mol/L";
                    result.setText(concentration);
                });
            }
        });

        return root;
    }

    // Method to easily change the style of the Buttons
    private Button styleButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #386641; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-font-size: 14px; -fx-min-width: 364px; -fx-padding:5px; -fx-max-width: 364px; -fx-min-height: 45px; " +
                "-fx-border-radius: 10px;");
        return button;
    }
}
