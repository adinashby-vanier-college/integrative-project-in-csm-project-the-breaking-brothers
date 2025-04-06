package com.example.chemistryapp.View;

import com.example.chemistryapp.Controller.StoichiometryController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class StoichiometryView {
    public BorderPane initializeStoichiometry() {
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
        /*molecule2.setPromptText("Enter Molecule");
        molecule3.setPromptText("Enter Molecule");
        molecule4.setPromptText("Enter Molecule");*/

        Text plus = new Text("+");
        Text arrow = new Text("→");
        Text plus3 = new Text("+");

        Button view1 = styleButton("View");
        Button view2 = styleButton("View");
        Button view3 = styleButton("View");
        Button view4 = styleButton("View");

        view1.setOnAction(e -> System.out.println("Button Pressed"));

        TextField mass1 = new TextField();
        TextField mass2 = new TextField();
        TextField mass3 = new TextField();
        TextField mass4 = new TextField();
        mass1.setPromptText("Enter Mass (in g)");
        mass2.setPromptText("Enter Mass (in g)");
        mass3.setPromptText("Enter Mass (in g)");
        mass4.setPromptText("Enter Mass (in g)");

        TextField moles1 = new TextField();
        TextField moles2 = new TextField();
        TextField moles3 = new TextField();
        TextField moles4 = new TextField();
        moles1.setPromptText("Enter amount of Moles");
        moles2.setPromptText("Enter amount of Moles");
        moles3.setPromptText("Enter amount of Moles");
        moles4.setPromptText("Enter amount of Moles");

        TextField energy1 = new TextField();
        TextField energy2 = new TextField();
        TextField energy3 = new TextField();
        TextField energy4 = new TextField();
        energy1.setPromptText("Enter Energy (in kJ/mol)");
        energy2.setPromptText("Enter Energy (in kJ/mol)");
        energy3.setPromptText("Enter Energy (in kJ/mol)");
        energy4.setPromptText("Enter Energy (in kJ/mol)");

        TextField concentration1 = new TextField();
        TextField concentration2 = new TextField();
        TextField concentration3 = new TextField();
        TextField concentration4 = new TextField();
        concentration1.setPromptText("Enter Concentration (in g/L)");
        concentration2.setPromptText("Enter Concentration (in g/L)");
        concentration3.setPromptText("Enter Concentration (in g/L");
        concentration4.setPromptText("Enter Concentration (in g/L");

        Button solve = styleButton("Solve!");
        solve.setAlignment(Pos.CENTER);

        solve.setOnAction(e -> {
            ArrayList<String> reactants = new ArrayList<>();
            ArrayList<String> products = new ArrayList<>();

            // Collect inputs properly
            if (molecule1 != null) {
                reactants.add(molecule1.getText());
            }
            if (molecule2 != null) {
                reactants.add(molecule2.getText());
            }
            if (molecule3 != null) {
                products.add(molecule3.getText());
            }
            if (molecule4 != null) {
                products.add(molecule4.getText());
            }


            ArrayList<String> balancedEquation = stoichio.getBalancedEquation(
                    stoichio.rref(
                            stoichio.createMatrix(reactants, products,
                                    stoichio.getUniqueElements(molecule1.getText(), molecule2.getText()))
                    ),
                    reactants,
                    products
            );

            // Adding the updated Molecules
            if (balancedEquation.size() >= 1) molecule1.setText(balancedEquation.get(0));
            if (balancedEquation.size() >= 2) molecule2.setText(balancedEquation.get(1));
            if (balancedEquation.size() >= 3) molecule3.setText(balancedEquation.get(2));
            if (balancedEquation.size() >= 4) molecule4.setText(balancedEquation.get(3));

            System.out.println("Solved!");
            System.out.println(balancedEquation);
        });

        // Adding all Text, TextField and Buttons to the GridPane
        gridPane.add(molecule1, 0,0);
        gridPane.add(plus, 1,0);
        gridPane.add(molecule2, 2, 0);
        gridPane.add(arrow, 3,0);
        gridPane.add(molecule3, 4,0);
        gridPane.add(plus3, 5, 0);
        gridPane.add(molecule4, 6,0);

        gridPane.add(view1, 0,1);
        gridPane.add(view2, 2,1);
        gridPane.add(view3, 4,1);
        gridPane.add(view4, 6,1);

        gridPane.add(mass1, 0,2);
        gridPane.add(mass2, 2,2);
        gridPane.add(mass3, 4,2);
        gridPane.add(mass4, 6,2);

        gridPane.add(moles1, 0, 3);
        gridPane.add(moles2, 2, 3);
        gridPane.add(moles3, 4, 3);
        gridPane.add(moles4, 6, 3);

        gridPane.add(energy1, 0, 4);
        gridPane.add(energy2, 2, 4);
        gridPane.add(energy3, 4, 4);
        gridPane.add(energy4, 6, 4);

        gridPane.add(concentration1, 0, 5);
        gridPane.add(concentration2, 2, 5);
        gridPane.add(concentration3, 4, 5);
        gridPane.add(concentration4, 6, 5);

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
        textField.setStyle("-fx-min-width: 364px; -fx-max-width: 364px; -fx-min-height: 45px; " +
                "-fx-border-color: lightgray; -fx-padding: 10px; -fx-border-radius: 7px; -fx-background-radius: 7px;");
        return textField;
    }

    // Method to easily change the style of the Buttons
    private Button styleButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #386641; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-font-size: 14px; -fx-min-width: 364px; -fx-max-width: 364px; -fx-min-height: 45px; " +
                "-fx-border-radius: 10px; -fx-alignment: center; -fx-padding: 10px;");
        return button;
    }
}
