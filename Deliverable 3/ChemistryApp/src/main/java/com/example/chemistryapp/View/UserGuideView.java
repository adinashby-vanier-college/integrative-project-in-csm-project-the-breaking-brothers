package com.example.chemistryapp.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserGuideView {
    public VBox initializeUserGuide() {
        Label title = new Label("User Guide");
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");


        Button molecule = new Button("How to build a molecule?");
        molecule.setOnAction(e -> showPopup(
                "How to build a molecule?",
                "1. Click on the **\"Molecule Viewer\"** tab.\n" +
                        "2. Enter the molecular formula in the provided input field.\n" +
                        "3. Choose whether to load the molecule in **2D** (structural formula) or **3D** (interactive model).\n" +
                        "4. Click **\"Load Molecule\"** to display it."
        ));
        Button periodicTable = new Button("How to use the periodic table?");
        periodicTable.setOnAction(e -> showPopup(
                "How to use the periodic table?",
                "1. Click on the **\"Periodic Table\"** tab.\n" +
                        "2. To view more information about an element click on it and an additional tab will appear."
        ));
        Button stoichiometry = new Button("How to use the periodic table?");
        stoichiometry.setOnAction(e -> showPopup(
                "How to use the stoichiometry calculator?",
                "1. Click on the **\"Stoichiometry\"** tab.\n" +
                        "2. ."
        ));
        Button pHpOHCalculator = new Button("How to use the pH and pOH calculator?");
        pHpOHCalculator.setOnAction(e -> showPopup(
                "How to use the periodic table?",
                "1. Click on the **\"pH/pOH\"** tab.\n" +
                        "2. Select whether to convert from pH to pOH or vice versa.\n" +
                        "3. Enter desired amount and click on the solve button."
        ));
        Button idealGasCalculator = new Button("How to use the ideal gas calculator?");
        idealGasCalculator.setOnAction(e -> showPopup(
                "How to use the ideal gas calculator?",
                "1. Click on the **\"Ideal Gas Calculator\"** tab.\n" +
                        "2. Enter values for 3 of the 4 fields (Pressure, Volume, Mole amount and Temperature).\n" +
                        "3. Click the calculate button."
        ));
        Button realGasCalculator = new Button("How to use the real gas calculator?");
        realGasCalculator.setOnAction(e -> showPopup(
                "How to use the ideal gas calculator?",
                "1. Click on the **\"Real Gas Calculator\"** tab.\n" +
                        "2. Enter values for 5 of the 6 fields (Pressure, Volume, Mole amount, Temperature and Parameter a and b).\n" +
                        "3. Click the calculate button."
        ));
        Button acidBaseCalculator = new Button("How to use the Acid Base Calculator?");
        acidBaseCalculator.setOnAction(e -> showPopup(
                "How to use the Acid Base Calculator?",
                "1. Click on the **\"Acid Base Calculator\"** tab.\n" +
                        "2. \n" +
                        "3. Click the calculate button."
        ));
        Button gibbsFreeEnergyCalculator = new Button("How to use the Gibbs Free Energy Calculator?");
        gibbsFreeEnergyCalculator.setOnAction(e -> showPopup(
                "How to use the Gibbs Free Energy Calculator?",
                "1. Click on the **\"Gibbs Free Energy Calculator\"** tab.\n" +
                        "2. Enter the values for 3 of the 4 fields (Gibbs Free Energy (ΔG), Enthalpy (ΔH), Temperature(T) and Entropy (ΔS)). \n" +
                        "3. Click the calculate button."
        ));
        Button reactionGraphViewer = new Button("How to use the Reaction Graph Viewer?");
        reactionGraphViewer.setOnAction(e -> showPopup(
                "How to use the Reaction Graph Viewer?",
                "1. Click on the **\"Reaction Graph Viewer\"** tab.\n" +
                        "2. Enter the values for the 5 fields (Balanced Equation, Initial Concentration, Rate Constant, Total Time and Time Step). \n" +
                        "3. Click the plot graph button."
        ));
        Button password = new Button("How to reset your password or delete your account?");
        password.setOnAction(e -> showPopup(
                "How to change your password?",
                "1. Click on the **\"Settings\"** tab.\n" +
                        "2. To delete your account click on the **\"Delete Account\"** button and delete your account."));

        VBox leftButtons = new VBox(molecule, periodicTable, stoichiometry, pHpOHCalculator, idealGasCalculator);
        VBox rightButtons = new VBox(realGasCalculator, acidBaseCalculator, gibbsFreeEnergyCalculator, reactionGraphViewer, password);

        leftButtons.setSpacing(10);
        rightButtons.setSpacing(10);
        leftButtons.setAlignment(Pos.CENTER);
        rightButtons.setAlignment(Pos.CENTER);

        HBox buttonColumns = new HBox(leftButtons, rightButtons);
        buttonColumns.setSpacing(20);
        buttonColumns.setAlignment(Pos.CENTER);

        VBox root = new VBox(title, buttonColumns);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStylesheets().add(getClass().getResource("/CSSFiles/userGuide.css").toExternalForm());

        return root;
    }

    private void showPopup(String title, String content) {
        Stage popupStage = new Stage();

        Label titleLabel = new Label(title);
        titleLabel.getStylesheets().add("popup-title");


        TextArea textArea = new TextArea(content.replace("**", ""));
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(400);
        textArea.setMaxHeight(300);
        textArea.getStyleClass().add("popup-text-area");

        VBox popupLayout = new VBox(textArea);
        popupLayout.getStyleClass().add("popup-container");
        popupLayout.setPadding(new Insets(10));


        Scene popupScene = new Scene(popupLayout);
        popupStage.setScene(popupScene);
        popupStage.setTitle(title);
        popupStage.show();

        popupStage.getScene().getRoot().getStyleClass().add("popup-stage");

        popupScene.getStylesheets().add(getClass().getResource("/CSSFiles/userGuide.css").toExternalForm());
    }
}
