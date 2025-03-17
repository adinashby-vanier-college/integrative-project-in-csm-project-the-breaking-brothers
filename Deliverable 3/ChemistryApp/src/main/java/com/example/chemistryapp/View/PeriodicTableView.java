package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.PeriodicTableController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class PeriodicTableView {
    public VBox InitializePeriodicTable () {
        PeriodicTableController elements = new PeriodicTableController();

        Label title = new Label();
        title.setText("Periodic Table of Elements");
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(10));
        mainGrid.setHgap(2);
        mainGrid.setVgap(2);
        mainGrid.setStyle("-fx-background-color: #f0f0f0;");
        mainGrid.setAlignment(Pos.CENTER);


        for (Object[] element : elements.elementInfo()) {
            Button btn = createElementButton(
                    (String) element[2],
                    (String) element[3],
                    (int) element[4],
                    elements.colorsInfo()[(int) element[5]]
            );
            mainGrid.add(btn, (int) element[1], (int) element[0]);
        }

        TilePane lanthanides = new TilePane();
        lanthanides.setPadding(new Insets(5));
        lanthanides.setHgap(2);
        lanthanides.setAlignment(Pos.CENTER);

        for (Object[] element : elements.lanthanidesInfo()) {
            lanthanides.getChildren().add(createElementButton(
                    (String) element[0],
                    (String) element[1],
                    (int) element[2],
                    elements.colorsInfo()[(int) element[3]]
            ));
        }

        TilePane actinides = new TilePane();
        actinides.setPadding(new Insets(5));
        actinides.setHgap(2);
        actinides.setAlignment(Pos.CENTER);

        for (Object[] element : elements.actinidesInfo()) {
            actinides.getChildren().add(createElementButton(
                    (String) element[0],
                    (String) element[1],
                    (int) element[2],
                    elements.colorsInfo()[(int) element[3]]
            ));
        }

        VBox root = new VBox(10);
        root.getChildren().addAll(title,mainGrid, lanthanides, actinides);
        root.setAlignment(Pos.CENTER);

        return root;
    }

    private Button createElementButton(String symbol, String name, int number, String color) {
        Button btn = new Button();
        btn.setPrefSize(70, 70);
        btn.setStyle("-fx-background-color: " + color + ";"
                + "-fx-border-color: #666;"
                + "-fx-border-width: 0px;"
                + "-fx-font-size: 12px;");

        VBox content = new VBox(2);
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(
                new javafx.scene.control.Label(String.valueOf(number)),
                new javafx.scene.control.Label(symbol),
                new javafx.scene.control.Label(name)
        );
        btn.setGraphic(content);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: derive(" + color + ", 20%);"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + color + ";"));

        return btn;
    }

}
