package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.ReactionGraphController;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReactionGraphViewer {
    public VBox initializeReactionGraph() {
        TextField equationInput = new TextField("2H2 + O2 -> 2H2O");
        TextField concentrationInput = new TextField("1.0");
        TextField rateConstantInput = new TextField("0.1");
        TextField totalTimeInput = new TextField("100");
        TextField timeStepInput = new TextField("1");
        Button plotButton = styleButton("Plot graph");


        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Concentration (mol/L)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Concentration vs. Time");

        plotButton.setOnAction(e -> {
            lineChart.getData().clear();

            String equation = equationInput.getText();
            double initialConcentration = Double.parseDouble(concentrationInput.getText());
            double k = Double.parseDouble(rateConstantInput.getText());
            int totalTime = Integer.parseInt(totalTimeInput.getText());
            int dt = Integer.parseInt(timeStepInput.getText());

            Map<String, Integer> reactants = new LinkedHashMap<>();
            Map<String, Integer> products = new LinkedHashMap<>();

            ReactionGraphController.parseEquation(equation, reactants, products);

            Map<String, XYChart.Series<Number, Number>> seriesMap = new HashMap<>();

            for (String species : reactants.keySet()) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(species);
                seriesMap.put(species, series);
            }
            for (String species : products.keySet()) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(species);
                seriesMap.put(species, series);
            }

            for (int t = 0; t <= totalTime; t += dt) {
                double decayFactor = Math.exp(-k * t);
                for (Map.Entry<String, Integer> entry : reactants.entrySet()) {
                    String species = entry.getKey();
                    double concentration = initialConcentration * decayFactor;
                    seriesMap.get(species).getData().add(new XYChart.Data<>(t, concentration));
                }
                for (Map.Entry<String, Integer> entry : products.entrySet()) {
                    String species = entry.getKey();
                    // Assume product forms based on 1 - decay factor
                    double concentration = initialConcentration * (1 - decayFactor);
                    seriesMap.get(species).getData().add(new XYChart.Data<>(t, concentration));
                }
            }

            lineChart.getData().addAll(seriesMap.values());
        });

        VBox root = new VBox(10,
                new Label("Balanced Equation:"), equationInput,
                new Label("Initial Concentration (mol/L):"), concentrationInput,
                new Label("Rate Constant (k):"), rateConstantInput,
                new Label("Total Time (s):"), totalTimeInput,
                new Label("Time Step (s):"), timeStepInput,
                plotButton, lineChart);
        return root;


    }
    private Button styleButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #386641; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-font-size: 14px; -fx-min-width: 364px; -fx-max-width: 364px; -fx-min-height: 45px; " +
                "-fx-border-radius: 10px; -fx-alignment: center; -fx-padding: 10px;");
        return button;
    }
}
