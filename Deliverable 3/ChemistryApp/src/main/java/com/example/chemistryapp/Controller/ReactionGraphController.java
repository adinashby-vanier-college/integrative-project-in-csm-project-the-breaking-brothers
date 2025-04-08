package com.example.chemistryapp.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class ReactionGraphController {
    public static void parseEquation(String equation, Map<String, Integer> reactants, Map<String, Integer> products) {
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
}

