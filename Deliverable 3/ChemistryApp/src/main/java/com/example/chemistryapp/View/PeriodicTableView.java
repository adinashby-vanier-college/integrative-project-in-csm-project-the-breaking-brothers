package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.PeriodicTableController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
//        mainGrid.setStyle("-fx-background-color: #f0f0f0;");
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

    public Button createElementButton(String symbol, String name, int number, String color) {
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

        btn.setOnAction(e -> showElementDetails(number, name, symbol));

        return btn;
    }

    public void showElementDetails(int atomicNumber, String elementName, String elementSymbol) {
        PeriodicTableController elements = new PeriodicTableController();
        Object[][] elementArray = elements.getElementDetails(atomicNumber);

        String atomicNumberStr = getValueFromArray(elementArray, "Atomic Number");
        String atomicWeight = getValueFromArray(elementArray, "Atomic Weight");
        String density = getValueFromArray(elementArray, "Density");
        String meltingPoint = getValueFromArray(elementArray, "Melting Point");
        String boilingPoint = getValueFromArray(elementArray, "Boiling Point");
        String group = getValueFromArray(elementArray, "Group");
        String period = getValueFromArray(elementArray, "Period");
        String electronConfig = getValueFromArray(elementArray, "Electron Configuration");
        String description = getValueFromArray(elementArray, "Description");

        Stage detailStage = new Stage();
        detailStage.setTitle(elementName);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20));

        VBox headerBox = new VBox(10);
        headerBox.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(elementName + " (" + elementSymbol + ")");
        nameLabel.setFont(new Font("Arial", 24));
        nameLabel.setStyle("-fx-font-weight: bold;");

        headerBox.getChildren().add(nameLabel);
        borderPane.setTop(headerBox);

        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(15);
        infoGrid.setVgap(10);
        infoGrid.setPadding(new Insets(20));

        addInfoRow(infoGrid, 0, "Atomic Number:", atomicNumberStr);
        addInfoRow(infoGrid, 1, "Atomic Weight:", atomicWeight);
        addInfoRow(infoGrid, 2, "Density:", density);
        addInfoRow(infoGrid, 3, "Melting Point:", meltingPoint);
        addInfoRow(infoGrid, 4, "Boiling Point:", boilingPoint);
        addInfoRow(infoGrid, 5, "Group:", group);
        addInfoRow(infoGrid, 6, "Period:", period);
        addInfoRow(infoGrid, 7, "Electron Configuration:", electronConfig);
        System.out.println(density);

        VBox technicalBox = new VBox(10);
        technicalBox.setPadding(new Insets(20, 0, 0, 0));

        Label technicalLabel = new Label("Technical Data");
        technicalLabel.setFont(new Font("Arial", 16));
        technicalLabel.setStyle("-fx-font-weight: bold;");

        TextFlow technicalFlow = new TextFlow();
        Text technicalText = new Text(description);
        technicalText.setWrappingWidth(600);
        technicalFlow.getChildren().add(technicalText);

        ScrollPane scrollPane = new ScrollPane(technicalFlow);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(200);
        scrollPane.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #ddd;");

        technicalBox.getChildren().addAll(technicalLabel, scrollPane);

        VBox contentBox = new VBox(20);
        contentBox.getChildren().addAll(infoGrid, technicalBox);

        borderPane.setCenter(contentBox);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> detailStage.close());
        closeButton.setPrefWidth(100);

        VBox bottomBox = new VBox(closeButton);
        bottomBox.setPadding(new Insets(20, 0, 0, 0));
        bottomBox.setAlignment(Pos.CENTER);

        borderPane.setBottom(bottomBox);

        Scene scene = new Scene(borderPane, 700, 600);
        detailStage.setScene(scene);

        StackPane root = new StackPane();

        detailStage.show();
    }

    private String getValueFromArray(Object[][] array, String key) {
        for (Object[] row : array) {
            if (row[0].equals(key)) {
                return row[1] != null ? row[1].toString() : "N/A";
            }
        }
        return "N/A";
    }

    public void addInfoRow(GridPane grid, int row, String label, String value) {
        Label infoLabel = new Label(label);
        infoLabel.setStyle("-fx-font-weight: bold;");
        grid.add(infoLabel, 0, row);

        Label infoValue = new Label(value != null ? value : "N/A");
        grid.add(infoValue, 1, row);
    }

}
