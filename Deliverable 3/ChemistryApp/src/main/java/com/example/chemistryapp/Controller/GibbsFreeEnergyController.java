package com.example.chemistryapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GibbsFreeEnergyController {

    @FXML
    private TextField deltaGField, deltaHField, temperatureField, deltaSField;

    @FXML
    private Label resultLabel, spontaneityLabel;

    @FXML
    private Button calculateButton, backButton;

    // Calculate Gibbs Free Energy Equation
    @FXML
    private void calculateGibbs() {
        try {
            Double deltaG = parseInput(deltaGField.getText());
            Double deltaH = parseInput(deltaHField.getText());
            Double temperature = parseInput(temperatureField.getText());
            Double deltaS = parseInput(deltaSField.getText());

            int nullCount = countNulls(deltaG, deltaH, temperature, deltaS);
            if (nullCount != 1) {
                resultLabel.setText("Please leave exactly one field empty.");
                spontaneityLabel.setText("");
                return;
            }

            double result = GibbsFreeEnergyCalculator.calculateMissingValue(deltaG, deltaH, temperature, deltaS);
            String missingParam = findMissingParameter(deltaG, deltaH, temperature, deltaS);

            resultLabel.setText(missingParam + " = " + String.format("%.2f", result));
            spontaneityLabel.setText(GibbsFreeEnergyCalculator.isReactionSpontaneous(result));

        } catch (Exception e) {
            resultLabel.setText("Invalid input!");
            spontaneityLabel.setText("");
        }
    }

    private Double parseInput(String input) {
        return input.isEmpty() ? null : Double.parseDouble(input);
    }

    private int countNulls(Double... values) {
        int count = 0;
        for (Double value : values) {
            if (value == null) count++;
        }
        return count;
    }

    private String findMissingParameter(Double deltaG, Double deltaH, Double temperature, Double deltaS) {
        if (deltaG == null) return "ΔG (Gibbs Free Energy)";
        if (deltaH == null) return "ΔH (Enthalpy)";
        if (temperature == null) return "T (Temperature)";
        return "ΔS (Entropy)";
    }
}
