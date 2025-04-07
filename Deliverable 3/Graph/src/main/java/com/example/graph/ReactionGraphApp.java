package com.example.graph;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class ReactionGraphApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField equationInput = new TextField("2H2 + O2 -> 2H2O");
        TextField concentrationInput = new TextField("1.0");
        TextField rateConstantInput = new TextField("0.1");
        TextField totalTimeInput = new TextField("100");
        TextField timeStepInput = new TextField("1");
        Button plotButton = new Button("Plot Graph");

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

            parseEquation(equation, reactants, products);

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

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Reaction Rate Graph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void parseEquation(String equation, Map<String, Integer> reactants, Map<String, Integer> products) {
        String[] sides = equation.split("->");
        String[] left = sides[0].trim().split("\\+");
        String[] right = sides[1].trim().split("\\+");

        for (String part : left) {
            part = part.trim();
            int coeff = 1;
            String name = part;
            if (Character.isDigit(part.charAt(0))) {
                int spaceIndex = part.indexOf(" ");
                if (spaceIndex > 0) {
                    coeff = Integer.parseInt(part.substring(0, spaceIndex));
                    name = part.substring(spaceIndex + 1);
                } else {
                    coeff = Integer.parseInt(part.replaceAll("\\D", ""));
                    name = part.replaceAll("\\d", "").trim();
                }
            }
            reactants.put(name, coeff);
        }

        for (String part : right) {
            part = part.trim();
            int coeff = 1;
            String name = part;
            if (Character.isDigit(part.charAt(0))) {
                int spaceIndex = part.indexOf(" ");
                if (spaceIndex > 0) {
                    coeff = Integer.parseInt(part.substring(0, spaceIndex));
                    name = part.substring(spaceIndex + 1);
                } else {
                    coeff = Integer.parseInt(part.replaceAll("\\D", ""));
                    name = part.replaceAll("\\d", "").trim();
                }
            }
            products.put(name, coeff);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
