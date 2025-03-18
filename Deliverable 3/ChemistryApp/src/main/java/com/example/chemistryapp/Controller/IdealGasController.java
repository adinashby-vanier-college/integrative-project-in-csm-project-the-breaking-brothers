package com.example.chemistryapp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IdealGasController {
    @FXML
    private TextField pressureField, volumeField, amountField, temperatureField;

    @FXML
    private Label resultLabel, unitLabel;

    @FXML
    private Button calculateButton;

    @FXML
    private Button backButton;

    @FXML
    private void calculate() {
        try {
            Double P = parseInput(pressureField.getText());
            Double V = parseInput(volumeField.getText());
            Double n = parseInput(amountField.getText());
            Double T = parseInput(temperatureField.getText());

            int nullCount = countNulls(P, V, n, T);
            if (nullCount != 1) {
                resultLabel.setText("Please leave exactly one field empty.");
                unitLabel.setText("");
                return;
            }

            double result = IdealGasCalculator.calculateMissingValue(P, V, n, T);
            String missingParam = findMissingParameter(P, V, n, T);

            resultLabel.setText(missingParam + " = " + String.format("%.3f", result));
            unitLabel.setText(getUnit(missingParam));

        } catch (Exception e) {
            resultLabel.setText("Invalid input. Please enter numbers only.");
            unitLabel.setText("");
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

    private String findMissingParameter(Double P, Double V, Double n, Double T) {
        if (P == null) return "Pressure (P)";
        if (V == null) return "Volume (V)";
        if (n == null) return "Amount (n)";
        return "Temperature (T)";
    }

    private String getUnit(String param) {
        switch (param) {
            case "Pressure (P)": return "Pa (Pascal)";
            case "Volume (V)": return "m³ (Cubic Meter)";
            case "Amount (n)": return "mol (Moles)";
            case "Temperature (T)": return "K (Kelvin)";
            default: return "";
        }
    }

}