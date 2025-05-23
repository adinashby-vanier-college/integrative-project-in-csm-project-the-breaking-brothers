package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.PHCalculator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class PHView {
    public VBox pHCalcInitialize() {
        PHCalculator phcalc = new PHCalculator();
        VBox root = new VBox(15); // Vertical Spacing of 15
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        VBox errorLog = new VBox(15);
        errorLog.setPadding(new Insets(10));
        errorLog.setAlignment(Pos.CENTER);


        // ComboBox to allow user to choose the type of conversion they want
        ComboBox<String> conversionChoice = new ComboBox<>();
        conversionChoice.getItems().addAll("pH -> pOH", "pOH -> pH", "H3O+ Concentration -> pH", "pH -> H3O+ Concentration", "OH- Concentration -> pOH", "pOH -> OH- Concentration");
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

        // Dynamically increasing size of objects
        VBox.setVgrow(userInput, Priority.ALWAYS);
        VBox.setVgrow(result, Priority.ALWAYS);
        VBox.setVgrow(conversionChoice, Priority.NEVER); // fixed height
        VBox.setVgrow(solve, Priority.NEVER);
        VBox.setVgrow(errorLog, Priority.SOMETIMES);

        root.getChildren().addAll(conversionChoice, userInput, result, solve);

        // Based on the chosen conversion type, the window's functionalities will change
        conversionChoice.setOnAction(e -> {
            String selectedConversion = conversionChoice.getSelectionModel().getSelectedItem();

            if ("pH -> pOH".equals(selectedConversion)) {
                userInput.setPromptText("Enter pH");

                solve.setOnAction(f -> {
                    errorLog.getChildren().clear();
                    try {
                        String convertedResult = "" + phcalc.convertTopOH(Double.parseDouble(userInput.getText()));
                        result.setText(convertedResult);
                    } catch (Exception exception) {
                        errorLog.getChildren().add(showError());
                    }
                });

            } else if ("pOH -> pH".equals(selectedConversion)) {

                userInput.setPromptText("Enter pOH");

                solve.setOnAction(f -> {
                    errorLog.getChildren().clear();
                    try {

                        String convertedResult = "" + phcalc.convertTopH(Double.parseDouble(userInput.getText()));
                        result.setText(convertedResult);
                    } catch (Exception exception) {
                        errorLog.getChildren().add(showError());
                    }

                });


            } else if ("H3O+ Concentration -> pH".equals(selectedConversion)) {
                userInput.setPromptText("Enter H3O+ Concentration");


                solve.setOnAction(f -> {
                    errorLog.getChildren().clear();
                    try {
                        String pH = "" + phcalc.H30ConcentrationTopH(Double.parseDouble(userInput.getText()));
                        result.setText(pH);
                    } catch (Exception exception) {
                        errorLog.getChildren().add(showError());
                    }
                });


            } else if ("pH -> H3O+ Concentration".equals(selectedConversion)) {
                userInput.setPromptText("Enter pH");


                solve.setOnAction(f -> {
                    errorLog.getChildren().clear();
                    try {
                        String concentration = phcalc.pHToH30Concentration(Double.parseDouble(userInput.getText())) + " mol/L";
                        result.setText(concentration);
                    } catch (Exception exception) {
                        errorLog.getChildren().add(showError());
                    }

                });


            } else if ("OH- Concentration -> pOH".equals(selectedConversion)) {
                userInput.setPromptText("Enter OH- Concentration");


                solve.setOnAction(f -> {
                    errorLog.getChildren().clear();
                    try {
                        String pOH = "" + phcalc.OHConcentrationTopOH(Double.parseDouble(userInput.getText()));
                        result.setText(pOH);
                    } catch (Exception exception) {
                        errorLog.getChildren().add(showError());
                    }

                });


            } else if ("pOH -> OH- Concentration".equals(selectedConversion)) {
                userInput.setPromptText("Enter pOH");


                solve.setOnAction(f -> {
                    errorLog.getChildren().clear();
                    try {
                        String concentration = phcalc.pOHToOHConcentration(Double.parseDouble(userInput.getText())) + " mol/L";
                        result.setText(concentration);
                    } catch (Exception exception) {
                        errorLog.getChildren().add(showError());
                    }

                });


            }


        });

        VBox.setVgrow(root, Priority.ALWAYS);
        return root;
    }

    // Method to easily change the style of the Buttons
    private Button styleButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #386641; -fx-text-fill: white; -fx-font-weight: bold; " + "-fx-font-size: 14px; -fx-min-width: 364px; -fx-padding:5px; -fx-max-width: 364px; -fx-min-height: 45px; " + "-fx-border-radius: 10px;");
        return button;
    }

    private TextField showError() {
        TextField error = new TextField("Invalid Inputs. Try Again.");
        error.setEditable(false);
        error.setStyle("-fx-background-color: #ffe6e6;" + // light red background
                "-fx-font-size: 16px;" + "-fx-border-color: red;" +             // red border
                "-fx-border-width: 2px;" + "-fx-text-fill: darkred;" +            // dark red text
                "-fx-background-radius: 5;" + "-fx-border-radius: 5;" + "-fx-padding: 5;");

        return error;
    }
}
