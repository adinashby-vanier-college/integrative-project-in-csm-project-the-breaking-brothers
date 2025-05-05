package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.ImageLoaderController;
import com.example.chemistryapp.Controller.StoichiometryController;
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
        StoichiometryController stoichio = new StoichiometryController();
        CustomMenuBarView customMenuBarView = new CustomMenuBarView();
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
        /*molecule2.setPromptText("Enter Molecule");
        molecule3.setPromptText("Enter Molecule");
        molecule4.setPromptText("Enter Molecule");*/

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

        /*view1.setOnAction(e ->

        )*/;

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

        /*TextField moles1 = new TextField();
        TextField moles2 = new TextField();
        TextField moles3 = new TextField();
        TextField moles4 = new TextField();
        TextField moles5 = new TextField();
        TextField moles6 = new TextField();
        moles1.setPromptText("Enter amount of Moles");
        moles2.setPromptText("Enter amount of Moles");
        moles3.setPromptText("Enter amount of Moles");
        moles4.setPromptText("Enter amount of Moles");
        moles5.setPromptText("Enter amount of Moles");
        moles6.setPromptText("Enter amount of Moles");*/

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

        Button solve = styleButton("Solve!");
        solve.setAlignment(Pos.CENTER);

        solve.setOnAction(e -> {
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

            System.out.println(molecule3.getText());
            System.out.println(reactants);
            System.out.println(products);

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
                System.out.println("Invalid Equation. Try Again.");
                // stoichio.errorBox("Equation");
            }

            try {
                ArrayList<String> solvedMass = stoichio.missingFieldCalculator(mass1, mass2, mass3, mass4);
                System.out.println(solvedMass);
                mass1.setText(solvedMass.get(0));
                mass2.setText(solvedMass.get(1));
                mass3.setText(solvedMass.get(2));
                mass4.setText(solvedMass.get(3));
            }
            catch (Exception solvedMassException) {
                System.out.println("Invalid Masses. Try Again.");
                // stoichio.errorBox("Masses");
            }

            try {
                ArrayList<String> solvedEnergy = stoichio.missingFieldCalculator(energy1, energy2, energy3, energy4);
                energy1.setText(solvedEnergy.get(0));
                energy2.setText(solvedEnergy.get(1));
                energy3.setText(solvedEnergy.get(2));
                energy4.setText(solvedEnergy.get(3));
            }
            catch (Exception solvedEnergyException) {
                System.out.println("Invalid Energies. Try Again.");
                // stoichio.errorBox("Energies");
            }


            try {
                ArrayList<String> solvedConcentration = stoichio.missingFieldCalculator(concentration1, concentration2, concentration3, concentration4);
                concentration1.setText(solvedConcentration.get(0));
                concentration2.setText(solvedConcentration.get(1));
                concentration3.setText(solvedConcentration.get(2));
                concentration4.setText(solvedConcentration.get(3));

            }
            catch (Exception solvedConcentrationException) {
                System.out.println("Invalid Concentrations. Try Again.");
                // stoichio.errorBox("Concentrations");
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

        /*gridPane.add(moles1, 0, 3);
        gridPane.add(moles2, 2, 3);
        gridPane.add(moles3, 4, 3);
        gridPane.add(moles4, 6, 3);
        gridPane.add(moles5, 8, 3);
        gridPane.add(moles6, 10, 3);*/

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
        root.setTop(customMenuBarView.initializeMenuBar());

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
