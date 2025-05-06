package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.PeriodicTableController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is responsible for creating the visual elements necessary to display the periodic table
 * @author Amir Zismanov
 */

public class PeriodicTableView {

    private StackPane root;

    /**
     * This method creates the periodic table, which is a set of buttons in a stack pane
     * @return StackPane which makes the periodic table
     */
    public StackPane InitializePeriodicTable () {
        PeriodicTableController elements = new PeriodicTableController();

        Label title = new Label();
        title.setText("Periodic Table of Elements");
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(10));
        mainGrid.setHgap(2);
        mainGrid.setVgap(2);
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

        VBox tableContent = new VBox(10, title, mainGrid, lanthanides, actinides);
        tableContent.setAlignment(Pos.CENTER);
        tableContent.setPadding(new Insets(20));

        root = new StackPane(tableContent);
        return root;
    }

    /**
     * This method creates each individual element (which is a button)
     * @param symbol symbol of the element
     * @param name name of the element
     * @param number atomic number of the element
     * @param color colour of the element's button depending on its placement in the periodic table
     * @return a button, which acts as an element of the periodic table
     */
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

    /**
     * This method creates a popup to display the element details when the user clicks on an element
     * @param atomicNumber atomic number of the element
     * @param elementName the name of the element
     * @param elementSymbol symbol of the element
     */
    public void showElementDetails(int atomicNumber, String elementName, String elementSymbol) {
        PeriodicTableController elements = new PeriodicTableController();
        Object[][] detailArray = elements.getElementDetails(atomicNumber);

        Label header = new Label(elementName + " (" + elementSymbol + ")");
        header.setStyle("-fx-font-weight: bold; -fx-font-size: 24px");

        VBox topBox = new VBox(header);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));

        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(15);
        infoGrid.setVgap(10);
        infoGrid.setPadding(new Insets(20));

        addInfoRow(infoGrid, 0, "Atomic Number:", getValueFromArray(detailArray, "Atomic Number"));
        addInfoRow(infoGrid, 1, "Atomic Weight:", getValueFromArray(detailArray, "Atomic Weight"));
        addInfoRow(infoGrid, 2, "Density:", getValueFromArray(detailArray, "Density"));
        addInfoRow(infoGrid, 3, "Melting Point:", getValueFromArray(detailArray, "Melting Point"));
        addInfoRow(infoGrid, 4, "Boiling Point:", getValueFromArray(detailArray, "Boiling Point"));
        addInfoRow(infoGrid, 5, "Group:", getValueFromArray(detailArray, "Group"));
        addInfoRow(infoGrid, 6, "Period:", getValueFromArray(detailArray, "Period"));
        addInfoRow(infoGrid, 7, "Electron Config:", getValueFromArray(detailArray, "Electron Configuration"));

        TextFlow techFlow = new TextFlow(new Text(getValueFromArray(detailArray, "Description")));
        techFlow.setPadding(new Insets(8));
        techFlow.setMaxWidth(600);
        techFlow.setStyle("-fx-font-size: 14px");

        ScrollPane techScroll = new ScrollPane(techFlow);
        techScroll.setFitToWidth(true);
        techScroll.setPrefHeight(200);
        techScroll.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-insets: 0; " +
                        "-fx-background-radius: 6; " +
                        "-fx-border-color: #1a3e1a; " +
                        "-fx-border-radius: 6; " +
                        "-fx-border-width: 1;"
        );

        Label technicalData = new Label("Technical Data");
        technicalData.setStyle("-fx-font-weight: bold; -fx-font-size: 18px");

        VBox techBox = new VBox(10, technicalData, techScroll);
        techBox.setPadding(new Insets(10));

        VBox centerBox = new VBox(20, infoGrid, techBox);

        Button close = new Button("Close");
        close.setOnAction(e -> {
            // remove any overlay nodes marked with userData="DETAIL_OVERLAY"
            root.getChildren().removeIf(node ->
                    "DETAIL_OVERLAY".equals(node.getUserData())
            );
        });
        VBox bottomBox = new VBox(close);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(10));

        BorderPane detailPane = new BorderPane();
        detailPane.setTop(topBox);
        detailPane.setCenter(centerBox);
        detailPane.setBottom(bottomBox);
        detailPane.setMaxWidth(700);
        detailPane.setMaxHeight(600);
        detailPane.setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-color: #666; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-background-radius: 10px;"
        );
        detailPane.setUserData("DETAIL_OVERLAY");

        Rectangle backdrop = new Rectangle();
        backdrop.widthProperty().bind(root.widthProperty());
        backdrop.heightProperty().bind(root.heightProperty());
        backdrop.setFill(Color.rgb(0, 0, 0, 0.4));
        backdrop.setUserData("DETAIL_OVERLAY");
        backdrop.setOnMouseClicked(e ->
                root.getChildren().removeIf(node ->
                        "DETAIL_OVERLAY".equals(node.getUserData())
                )
        );

        root.getChildren().addAll(backdrop, detailPane);
        StackPane.setAlignment(detailPane, Pos.CENTER);
    }


    /**
     * This method gets the value (String) from the array that contains all of the information about each element such as density, boiling point, group, etc
     * @param array containing all of the element data
     * @param key the property that has to be retrieved
     * @return A String value
     */
    private String getValueFromArray(Object[][] array, String key) {
        for (Object[] row : array) {
            if (row[0].equals(key)) {
                return row[1] != null ? row[1].toString() : "N/A";
            }
        }
        return "N/A";
    }

    /**
     * This method adds the required information (in a 2 column format) into a grid pane
     * @param grid grid pane used to show all element information
     * @param row of the info in the grid
     * @param label of the info
     * @param value string of the value to put for the label
     */
    public void addInfoRow(GridPane grid, int row, String label, String value) {
        Label infoLabel = new Label(label);
        infoLabel.setStyle("-fx-font-weight: bold;");
        grid.add(infoLabel, 0, row);

        Label infoValue = new Label(value != null ? value : "N/A");
        grid.add(infoValue, 1, row);
    }

}
