package com.example.chemistryapp.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This  class creates the user guide user interface
 * @author Amir Zismanov
 */
public class UserGuideView {

    private StackPane root;

    /**
     * This method initializes the UI for the user guide. It creates a set of buttons which explain how the program works
     * @return the root stack pain containing all of the elements present in the user guide
     */
    public StackPane initializeUserGuide() {
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

        Button stoichiometry = new Button("How to use the stoichiometry calculator?");
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

        Button acidBaseCalculator = new Button("How to use the acid base calculator?");
        acidBaseCalculator.setOnAction(e -> showPopup(
                "How to use the Acid Base Calculator?",
                "1. Click on the **\"Acid Base Calculator\"** tab.\n" +
                        "2. \n" +
                        "3. Click the calculate button."
        ));

        Button gibbsFreeEnergyCalculator = new Button("How to use the Gibbs free energy calculator?");
        gibbsFreeEnergyCalculator.setOnAction(e -> showPopup(
                "How to use the Gibbs Free Energy Calculator?",
                "1. Click on the **\"Gibbs Free Energy Calculator\"** tab.\n" +
                        "2. Enter the values for 3 of the 4 fields (Gibbs Free Energy (ΔG), Enthalpy (ΔH), Temperature(T) and Entropy (ΔS)). \n" +
                        "3. Click the calculate button."
        ));

        Button reactionGraphViewer = new Button("How to use the reaction graph viewer?");
        reactionGraphViewer.setOnAction(e -> showPopup(
                "How to use the Reaction Graph Viewer?",
                "1. Click on the **\"Reaction Graph Viewer\"** tab.\n" +
                        "2. Enter the values for the 5 fields (Balanced Equation, Initial Concentration, Rate Constant, Total Time and Time Step). \n" +
                        "3. Click the plot graph button."
        ));
        Button password = new Button("How to delete your account?");
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

        VBox content = new VBox(title, buttonColumns);
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));
        content.getStylesheets().add(getClass().getResource("/CSSFiles/userGuide.css").toExternalForm());

        root = new StackPane(content);

        return root;
    }

    /**
     * This method displays a popup that appears when the user clicks on one of the buttons in the user guide
     * @param titleText A string representing the title of the popup
     * @param contentText A string representing the instructions written in the popup
     */
    private void showPopup(String titleText, String contentText) {
        Rectangle backdrop = new Rectangle();
        backdrop.widthProperty().bind(root.widthProperty());
        backdrop.heightProperty().bind(root.heightProperty());
        backdrop.setFill(new Color(0, 0, 0, 0.3));

        Label titleLabel = new Label(titleText);
        titleLabel.getStyleClass().add("popup-title");

        TextArea textArea = new TextArea(contentText.replace("**", ""));
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(400);
        textArea.setMaxHeight(300);
        textArea.getStyleClass().add("popup-text-area");

        Button closeButton = new Button("Close");
        closeButton.getStyleClass().add("popup-close-button");

        VBox contentBox = new VBox(15, titleLabel, textArea, closeButton);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));
        contentBox.getStyleClass().add("popup-content");

        Rectangle boxBackground = new Rectangle();
        boxBackground.setWidth(450);
        boxBackground.setHeight(400);
        boxBackground.setArcWidth(10);
        boxBackground.setArcHeight(10);
        boxBackground.setFill(Color.WHITE);
        boxBackground.getStyleClass().add("popup-box");

        StackPane popupPane = new StackPane(boxBackground, contentBox);
        popupPane.setMaxWidth(450);
        popupPane.setMaxHeight(400);
        popupPane.setPadding(new Insets(10));
        popupPane.getStyleClass().add("popup-container");

        closeButton.setOnAction(e -> root.getChildren().removeAll(backdrop, popupPane));
        backdrop.setOnMouseClicked(e -> root.getChildren().removeAll(backdrop, popupPane));

        StackPane.setAlignment(popupPane, Pos.CENTER);
        root.getChildren().addAll(backdrop, popupPane);
    }
}
