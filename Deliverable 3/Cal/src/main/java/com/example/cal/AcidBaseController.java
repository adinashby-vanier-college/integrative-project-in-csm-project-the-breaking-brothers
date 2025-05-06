package com.example.cal;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AcidBaseController {

    @FXML
    private TextField acidField, baseField, acidMolesField, baseMolesField;

    @FXML
    private TextField massField, molarMassField, molesOutputField;

    @FXML
    private Label resultLabel, waterLabel;

    @FXML
    private Button neutralizeButton, convertButton, backButton;

    // Handle Neutralization Calculation
    @FXML
    private void calculateNeutralization() {
        try {
            String acid = acidField.getText().trim();
            String base = baseField.getText().trim();
            double acidMoles = Double.parseDouble(acidMolesField.getText());
            double baseMoles = Double.parseDouble(baseMolesField.getText());

            String salt = AcidBaseCalculator.getSalt(acid, base);
            double waterProduced = AcidBaseCalculator.calculateWaterProduced(acidMoles, baseMoles);

            resultLabel.setText("Resulting Salt: " + salt);
            waterLabel.setText("Water Produced: " + waterProduced + " mol");
        } catch (Exception e) {
            resultLabel.setText("Invalid input! Enter valid numbers.");
            waterLabel.setText("");
        }
    }

    // Handle Mass to Moles Conversion
    @FXML
    private void convertMassToMoles() {
        try {
            double mass = Double.parseDouble(massField.getText());
            double molarMass = Double.parseDouble(molarMassField.getText());

            double moles = AcidBaseCalculator.convertMassToMoles(mass, molarMass);
            molesOutputField.setText(String.format("%.3f mol", moles));
        } catch (Exception e) {
            molesOutputField.setText("Invalid input!");
        }
    }

    @FXML
    private void goBackToMenu() {
        try {
            Main.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}