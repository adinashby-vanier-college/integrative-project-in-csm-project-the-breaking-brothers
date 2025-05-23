package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.ImageLoaderController;
import com.example.chemistryapp.Controller.StoichiometryController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class StoichiometryView {
    public BorderPane initializeStoichiometry() {
        ImageView moleculeView = new ImageView(); // ImageViewer for molecules
        ImageLoaderController imageloaderController = new ImageLoaderController();
        StoichiometryController stoichio = new StoichiometryController();
        BorderPane root = new BorderPane();

        // GridPane used for handling the stoichiometry interface
        GridPane gridPane = new GridPane();

        gridPane.setHgap(8);
        gridPane.setVgap(10);
        // Creating all TextFields, Text and Buttons
        TextField molecule1 = styleTextField("Enter Molecule");
        TextField molecule2 = styleTextField("Enter Molecule");
        TextField molecule3 = styleTextField("Enter Molecule");
        TextField molecule4 = styleTextField("Enter Molecule");
        TextField molecule5 = styleTextField("Enter Molecule");
        TextField molecule6 = styleTextField("Enter Molecule");


        Text plus = new Text("+");
        Text plus2 = new Text("+");
        Text arrow = new Text("→");
        Text plus3 = new Text("+");
        Text plus4 = new Text("+");

        Button view1 = styleButton("View");
        Button view2 = styleButton("View");
        Button view3 = styleButton("View");
        Button view4 = styleButton("View");
        Button view5 = styleButton("View");
        Button view6 = styleButton("View");

        // When the view button is clicked, it creates a popup
        view1.setOnAction(e -> {
            imageloaderController.loadMoleculeImage(molecule1.getText(), image -> moleculeView.setImage(image));
            stoichio.moleculeViewerScreen(moleculeView);
        });

        view2.setOnAction(e -> {
            imageloaderController.loadMoleculeImage(molecule2.getText(), image -> moleculeView.setImage(image));
            stoichio.moleculeViewerScreen(moleculeView);
        });

        view3.setOnAction(e -> {
            imageloaderController.loadMoleculeImage(molecule3.getText(), image -> moleculeView.setImage(image));
            stoichio.moleculeViewerScreen(moleculeView);
        });

        view4.setOnAction(e -> {
            imageloaderController.loadMoleculeImage(molecule4.getText(), image -> moleculeView.setImage(image));
            stoichio.moleculeViewerScreen(moleculeView);
        });

        view5.setOnAction(e -> {
            imageloaderController.loadMoleculeImage(molecule5.getText(), image -> moleculeView.setImage(image));
            stoichio.moleculeViewerScreen(moleculeView);
        });

        view6.setOnAction(e -> {
            imageloaderController.loadMoleculeImage(molecule6.getText(), image -> moleculeView.setImage(image));
            stoichio.moleculeViewerScreen(moleculeView);
        });

        TextField mass1 = new TextField();
        TextField mass2 = new TextField();
        TextField mass3 = new TextField();
        TextField mass4 = new TextField();
        TextField mass5 = new TextField();
        TextField mass6 = new TextField();
        mass1.setPromptText("Enter Mass (in g)");
        mass2.setPromptText("Enter Mass (in g)");
        mass3.setPromptText("Enter Mass (in g)");
        mass4.setPromptText("Enter Mass (in g)");
        mass5.setPromptText("Enter Mass (in g)");
        mass6.setPromptText("Enter Mass (in g)");

        TextField energy1 = new TextField();
        TextField energy2 = new TextField();
        TextField energy3 = new TextField();
        TextField energy4 = new TextField();
        TextField energy5 = new TextField();
        TextField energy6 = new TextField();
        energy1.setPromptText("Enter Energy (in kJ/mol)");
        energy2.setPromptText("Enter Energy (in kJ/mol)");
        energy3.setPromptText("Enter Energy (in kJ/mol)");
        energy4.setPromptText("Enter Energy (in kJ/mol)");
        energy5.setPromptText("Enter Energy (in kJ/mol)");
        energy6.setPromptText("Enter Energy (in kJ/mol)");

        TextField concentration1 = new TextField();
        TextField concentration2 = new TextField();
        TextField concentration3 = new TextField();
        TextField concentration4 = new TextField();
        TextField concentration5 = new TextField();
        TextField concentration6 = new TextField();
        concentration1.setPromptText("Enter Concentration (in g/L)");
        concentration2.setPromptText("Enter Concentration (in g/L)");
        concentration3.setPromptText("Enter Concentration (in g/L");
        concentration4.setPromptText("Enter Concentration (in g/L");
        concentration5.setPromptText("Enter Concentration (in g/L");
        concentration6.setPromptText("Enter Concentration (in g/L");

        VBox errorLog = new VBox(15);
        errorLog.setPadding(new Insets(10));

        Button solve = styleButton("Solve!");
        solve.setAlignment(Pos.CENTER);

        solve.setOnAction(e -> {
            errorLog.getChildren().clear();
            ArrayList<String> reactants = new ArrayList<>();
            ArrayList<String> products = new ArrayList<>();

            // Collect inputs properly
            if (!molecule1.getText().isEmpty()) {
                reactants.add(molecule1.getText());
            }
            if (!molecule2.getText().isEmpty()) {
                reactants.add(molecule2.getText());
            }
            if (!molecule3.getText().isEmpty()) {
                reactants.add(molecule3.getText());
            }
            if (!molecule4.getText().isEmpty()) {
                products.add(molecule4.getText());
            }
            if (!molecule5.getText().isEmpty()) {
                products.add(molecule5.getText());
            }
            if (!molecule6.getText().isEmpty()) {
                products.add(molecule6.getText());
            }

            /*System.out.println(molecule3.getText());
            System.out.println(reactants);
            System.out.println(products);*/

            try {
                ArrayList<ArrayList<String>> balancedEquation = stoichio.getBalancedEquation(
                        stoichio.rref(
                                stoichio.createMatrix(reactants, products,
                                        stoichio.getUniqueElements(reactants))
                        ),
                        reactants,
                        products
                );

                System.out.println(balancedEquation);

                // Replacing the updated Molecules
                molecule1.setText(balancedEquation.get(0).get(0));
                molecule2.setText(balancedEquation.get(0).get(1));
                molecule3.setText(balancedEquation.get(0).get(2));
                molecule4.setText(balancedEquation.get(1).get(0));
                molecule5.setText(balancedEquation.get(1).get(1));
                molecule6.setText(balancedEquation.get(1).get(2));

                System.out.println("Solved!");
                System.out.println(balancedEquation);
            }
            catch (Exception stoichioException) {
                TextField stoichiometryError = new TextField("Invalid Equation. Try Again.");
                stoichiometryError.setEditable(false);
                stoichiometryError.setStyle(
                        "-fx-background-color: #ffe6e6;" + // light red background
                                "-fx-font-size: 16px;" +
                                "-fx-border-color: red;" +             // red border
                                "-fx-border-width: 2px;" +
                                "-fx-text-fill: darkred;" +            // dark red text
                                "-fx-background-radius: 5;" +
                                "-fx-border-radius: 5;" +
                                "-fx-padding: 5;"
                );
                errorLog.getChildren().add(stoichiometryError);
            }

            try {
                ArrayList<String> solvedMass = stoichio.missingFieldCalculator(mass1, mass2, mass3, mass4, mass5, mass6);
                System.out.println(solvedMass);
                mass1.setText(solvedMass.get(0));
                mass2.setText(solvedMass.get(1));
                mass3.setText(solvedMass.get(2));
                mass4.setText(solvedMass.get(3));
            }
            catch (Exception solvedMassException) {
                TextField massError = new TextField("Invalid Masses. Try Again.");
                massError.setEditable(false);
                massError.setStyle(
                        "-fx-background-color: #ffe6e6;" + // light red background
                                "-fx-font-size: 16px;" +
                                "-fx-border-color: red;" +             // red border
                                "-fx-border-width: 2px;" +
                                "-fx-text-fill: darkred;" +            // dark red text
                                "-fx-background-radius: 5;" +
                                "-fx-border-radius: 5;" +
                                "-fx-padding: 5;"
                );
                errorLog.getChildren().add(massError);
            }

            try {
                ArrayList<String> solvedEnergy = stoichio.missingFieldCalculator(energy1, energy2, energy3, energy4, energy5, energy6);
                energy1.setText(solvedEnergy.get(0));
                energy2.setText(solvedEnergy.get(1));
                energy3.setText(solvedEnergy.get(2));
                energy4.setText(solvedEnergy.get(3));
            }
            catch (Exception solvedEnergyException) {
                TextField energyyError = new TextField("Invalid Energies. Try Again.");
                energyyError.setEditable(false);
                energyyError.setStyle(
                        "-fx-background-color: #ffe6e6;" + // light red background
                                "-fx-font-size: 16px;" +
                                "-fx-border-color: red;" +             // red border
                                "-fx-border-width: 2px;" +
                                "-fx-text-fill: darkred;" +            // dark red text
                                "-fx-background-radius: 5;" +
                                "-fx-border-radius: 5;" +
                                "-fx-padding: 5;"
                );
                errorLog.getChildren().add(energyyError);
            }


            try {
                ArrayList<String> solvedConcentration = stoichio.missingFieldCalculator(concentration1, concentration2,
                        concentration3, concentration4, concentration5, concentration6);
                concentration1.setText(solvedConcentration.get(0));
                concentration2.setText(solvedConcentration.get(1));
                concentration3.setText(solvedConcentration.get(2));
                concentration4.setText(solvedConcentration.get(3));

            }
            catch (Exception solvedConcentrationException) {
                TextField concentrationError = new TextField("Invalid Concentrations. Try Again.");
                concentrationError.setEditable(false);
                concentrationError.setStyle(
                        "-fx-background-color: #ffe6e6;" + // light red background
                                "-fx-font-size: 16px;" +
                                "-fx-border-color: red;" +             // red border
                                "-fx-border-width: 2px;" +
                                "-fx-text-fill: darkred;" +            // dark red text
                                "-fx-background-radius: 5;" +
                                "-fx-border-radius: 5;" +
                                "-fx-padding: 5;"
                );
                errorLog.getChildren().add(concentrationError);
            }

        });

        // Adding all Text, TextField and Buttons to the GridPane
        gridPane.add(molecule1, 0,0);
        gridPane.add(plus, 1,0);
        gridPane.add(molecule2, 2, 0);
        gridPane.add(plus2, 3,0);
        gridPane.add(molecule3, 4,0);
        gridPane.add(arrow, 5, 0);
        gridPane.add(molecule4, 6,0);
        gridPane.add(plus3, 7,0);
        gridPane.add(molecule5, 8,0);
        gridPane.add(plus4, 9,0);
        gridPane.add(molecule6, 10,0);

        gridPane.add(view1, 0,1);
        gridPane.add(view2, 2,1);
        gridPane.add(view3, 4,1);
        gridPane.add(view4, 6,1);
        gridPane.add(view5, 8,1);
        gridPane.add(view6, 10,1);

        gridPane.add(mass1, 0,2);
        gridPane.add(mass2, 2,2);
        gridPane.add(mass3, 4,2);
        gridPane.add(mass4, 6,2);
        gridPane.add(mass5, 8,2);
        gridPane.add(mass6, 10,2);

        gridPane.add(energy1, 0, 3);
        gridPane.add(energy2, 2, 3);
        gridPane.add(energy3, 4, 3);
        gridPane.add(energy4, 6, 3);
        gridPane.add(energy5, 8, 3);
        gridPane.add(energy6, 10, 3);

        gridPane.add(concentration1, 0, 4);
        gridPane.add(concentration2, 2, 4);
        gridPane.add(concentration3, 4, 4);
        gridPane.add(concentration4, 6, 4);
        gridPane.add(concentration5, 8, 4);
        gridPane.add(concentration6, 10, 4);

        gridPane.setAlignment(Pos.CENTER);

        VBox stoichiometryVBox = new VBox(15);
        stoichiometryVBox.setAlignment(Pos.CENTER);
        stoichiometryVBox.getChildren().addAll(gridPane, solve);

        root.setCenter(stoichiometryVBox);
        root.setBottom(errorLog);

        return root;
    }

    // Method to easily change the style of the TextFields
    private TextField styleTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-min-width: 170px; -fx-max-width: 170px; -fx-min-height: 45px; " +
                "-fx-border-color: lightgray; -fx-padding: 10px; -fx-border-radius: 7px; -fx-background-radius: 7px;");
        return textField;
    }

    // Method to easily change the style of the Buttons
    private Button styleButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #386641; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-font-size: 14px; -fx-min-width: 170px; -fx-max-width: 170px; -fx-min-height: 45px; " +
                "-fx-border-radius: 10px; -fx-alignment: center; -fx-padding: 10px;");
        return button;
    }
}
