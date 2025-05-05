package com.example.chemistryapp.Controller;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StoichiometryController {
    /**
     * Takes in the formula of a molecule and returns an arraylist of subscripts, each entry being the subscript of an atom
     * @param formula formula of a molecule (e.g: H2O2)
     * @return an ArrayList of subscripts (e.g: [2,2])
     */
    public ArrayList<Integer> getElementSubscripts(String formula) {
        ArrayList<String> elements = new ArrayList<>(); // For each molecule, it creates a list of elements in it; e.g. [Ca, C, O]
        ArrayList<Integer> counts = new ArrayList<>(); // For each molecule, it creates a list of numbers corresponding to how many of that element is in the molecule

        int i = 0; // The i serves as a pointer in the equation of the molecule
        while (i < formula.length()) {
            char c = formula.charAt(i);
            StringBuilder element = new StringBuilder();
            element.append(c); // Add first letter of the element to the string
            i++;

            if (i < formula.length() && Character.isLowerCase(formula.charAt(i))) {
                element.append(formula.charAt(i)); // If the letter after character c is lowercase, add it to the string.
                // This is useful for when elements have 2 letters instead of just one (Ca, Mg, etc.)
                i++;
            }

            StringBuilder subscript = new StringBuilder();
            while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                subscript.append(formula.charAt(i)); // Add the subscript of the number in the string
                i++;
            }

            int defaultSubscript = 1; // If there is no number after an element, it means that the subscript is 1.
            if (subscript.length() > 0) { // If there is a number, we change defaultSubscript to that number
                defaultSubscript = Integer.parseInt(subscript.toString());
            }

            // Adding element symbol and subscript to the main arrays
            elements.add(element.toString());
            counts.add(defaultSubscript);
        }

        // Add Element String and Subscript
        ArrayList<Integer> result = new ArrayList<>();
        for (int j = 0; j < elements.size(); j++) {
            result.add(counts.get(j));
        }

        return result;
    }

    /**
     * Finds all the unique elements from the array of reactants
     * @param reactants ArrayList of reactants (e.g.: [H2, O2, CO])
     * @return an ArrayList of unique, unrepeated elements (e.g.: [H, O, C])
     */
    public ArrayList<String> getUniqueElements(ArrayList<String> reactants) {
        ArrayList<String> elements = new ArrayList<>();

        // Allows the program to find all the elements in the equation.
        /*extractElements(molecule1, elements);
        extractElements(molecule2, elements);*/

        for (int i = 0; i < reactants.size(); i++) {
            extractElements(reactants.get(i), elements);
        }

        // Only adds unique elements, doesn't add common elements
        ArrayList<String> uniqueElements = new ArrayList<>();
        for (String element : elements) {
            if (!uniqueElements.contains(element)) {
                uniqueElements.add(element);
            }
        }

        // Convert the ArrayList to a String array and return it
        return uniqueElements;
    }

    /**
     * Finds all elements in a molecule
     * @param molecule formula of a molecule to scan through
     * @param elements an empty ArrayList that gets filled up with elements
     */
    private void extractElements(String molecule, ArrayList<String> elements) {
        StringBuilder element = new StringBuilder();

        for (int i = 0; i < molecule.length(); i++) {
            char currentChar = molecule.charAt(i);

            // An uppercase character means its the start of an element, so it gets added into 'element'
            if (Character.isUpperCase(currentChar)) {
                if (element.length() > 0) {
                    elements.add(element.toString());
                }
                element.setLength(0); // Essentially signifies the start of a new element being added.
                element.append(currentChar);
            }
            // If it's a lowercase letter, it means it's the 2nd letter of the element, so we append it to 'element'
            else if (Character.isLowerCase(currentChar)) {
                element.append(currentChar);
            }
        }

        // Add the last element found
        if (element.length() > 0) {
            elements.add(element.toString());
        }
    }

    /**
     * Creates a matrix to balance a chemical equation
     * @param reactants ArrayList of reactant molecules as each column represents a reactant
     * @param products ArrayList of product molecules as each column represents a product
     * @param elements ArrayList of Unique elements as each row is equal to an element
     * @return
     */
    public double[][] createMatrix(ArrayList<String> reactants, ArrayList<String> products, ArrayList<String> elements) {
        int rowCount = elements.size();
        int colCount = reactants.size() + products.size();
        double[][] matrix = new double[rowCount][colCount];

        // Adding the reactants to the matrix
        for (int j = 0; j < reactants.size(); j++) {
            ArrayList<String> elementSymbols = getElementSymbols(reactants.get(j));
            ArrayList<Integer> elementCounts = getElementSubscripts(reactants.get(j));
            for (int i = 0; i < elements.size(); i++) {
                for (int k = 0; k < elementSymbols.size(); k++) {
                    if (elements.get(i).equals(elementSymbols.get(k))) {
                        matrix[i][j] = elementCounts.get(k);
                    }
                }
            }
        }

        // Adding the products to the matrix
        for (int j = 0; j < products.size(); j++) {
            ArrayList<String> elementSymbols = getElementSymbols(products.get(j));
            ArrayList<Integer> elementCounts = getElementSubscripts(products.get(j));
            for (int i = 0; i < elements.size(); i++) {
                for (int k = 0; k < elementSymbols.size(); k++) {
                    if (elements.get(i).equals(elementSymbols.get(k))) {
                        matrix[i][j + reactants.size()] = -elementCounts.get(k);
                    }
                }
            }
        }
        return matrix;
    }

    /**
     * From a molecule it creates an ArrayList of each element's symbol.
     * @param formula String of a Molecule
     * @return an ArrayList of element symboles
     */
    public ArrayList<String> getElementSymbols(String formula) {
        ArrayList<String> elements = new ArrayList<>();
        int i = 0;

        while (i < formula.length()) {
            StringBuilder element = new StringBuilder();
            element.append(formula.charAt(i)); // Add first letter of the element
            i++;

            if (i < formula.length() && Character.isLowerCase(formula.charAt(i))) {
                element.append(formula.charAt(i)); // If the next letter is lowercase, add it to the string
                i++;
            }

            elements.add(element.toString()); // Add element string to the arraylist

            // Skip any numbers
            while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                i++;
            }
        }
        return elements;
    }


    /**
     * Uses a Reduced-Row Echelon Form algorithm to solve a matrix
     * @param matrix a matrix set up previously to balance a chemical equation
     * @return a matrix in RREF format
     */
    public double[][] rref(double[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int lead = 0; // Pointer for the Pivot Column aka the column that has a 1 in it

        // Looping through each row until we find a row without a leading 1
        for (int r = 0; r < rowCount; r++) {
            if (lead >= colCount) return matrix;

            int i = r;

            while (matrix[i][lead] == 0) {
                i++;
                if (i == rowCount) {
                    i = r;
                    lead++;
                    if (lead == colCount) return matrix;
                }
            }

            // Once row with no leading 1 found, we switch them with the row with a leading 1
            double[] temp = matrix[r];
            matrix[r] = matrix[i];
            matrix[i] = temp;

            // Divide the row with the leading value so that the pivot becomes 1
            double leadVal = matrix[r][lead];
            for (int j = 0; j < colCount; j++) {
                matrix[r][j] /= leadVal;
            }

            // Simplify the values in the leading columns using a multiple of the pivot value
            for (int k = 0; k < rowCount; k++) {
                if (k != r) {
                    double factor = matrix[k][lead];
                    for (int j = 0; j < colCount; j++) {
                        matrix[k][j] -= factor * matrix[r][j];
                    }
                }
            }
            lead++;
        }
        return matrix;
    }

    // Method to find the Greatest Common Denominator
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Method to find the Least Common Multiple
    public int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    /**
     * Using a matrix in RREF, it uses a free variable to create a balanced equation.
     * @param rrefMatrix Matrix in RREF
     * @param reactants An ArrayList of Reactants so that the size of the updated ArrayList of balanced reactants doesn't
     *                  exceed the original
     * @param products An ArrayList of Reactants so that the size of the updated ArrayList of balanced products doesn't
     *                 exceed the original
     * @return an ArrayList containing an ArrayList of balanced Reactants and an ArrayList of balanced Products
     */
    public ArrayList<ArrayList<String>> getBalancedEquation(double[][] rrefMatrix, ArrayList<String> reactants, ArrayList<String> products) {
        int totalMolecules = rrefMatrix[0].length; // rrefMatrix[0].length is the amount of molecules in the first row.
        ArrayList<Double> rawCoefficients = new ArrayList<>(); // Coefficients from the RREF Matrix

        // Put all values in the rawCoefficient ArrayList as 0 so it's easier to change later
        for (int i = 0; i < totalMolecules; i++) {
            rawCoefficients.add(0.0);
        }

        // Set the last variable to 1 so it becomes a free variable
        rawCoefficients.set(totalMolecules - 1, 1.0);

        // Replaces the 0s in rawCoefficients with actual numbers by solving equations thanks to the free variable
        for (int i = totalMolecules - 2; i >= 0; i--) { // int i = totalMolecules -2
            double sum = 0.0;
            for (int j = i + 1; j < totalMolecules; j++) {
                sum += rrefMatrix[i][j] * rawCoefficients.get(j);
            }
            rawCoefficients.set(i, -sum);  // Solve for this variable
        }

        // Reduces the numbers set in rawCoefficents into smaller numbers
        ArrayList<Integer> numerators = new ArrayList<>();
        ArrayList<Integer> denominators = new ArrayList<>();

        // Convert all coefficients into fractions so we can later find the common denominator
        for (int i = 0; i < rawCoefficients.size(); i++) {
            double val = rawCoefficients.get(i);
            int denom = 1;
            while (Math.abs(val * denom - Math.round(val * denom)) > 1e-6) {
                denom++;
            }
            numerators.add((int)Math.round(val * denom));
            denominators.add(denom);
        }

        // Finding common denominator to multiply all the coefficients with
        int commonMultiple = 1;
        for (int d = 0; d < denominators.size(); d++) {
            commonMultiple = lcm(commonMultiple, denominators.get(d));
        }

        // Multiplies all fraction forms of coefficient with common denominator to turn into whole numbers
        ArrayList<Integer> finalCoefficients = new ArrayList<>();
        for (int i = 0; i < numerators.size(); i++) {
            int scaled = numerators.get(i) * (commonMultiple / denominators.get(i));
            finalCoefficients.add(Math.abs(scaled)); // Absolute value for positive coefficients
        }

        // Adding all coefficients to the molecules and adding them into an ArrayList that will be returned
        ArrayList<String> balancedMolecules = new ArrayList<>();
        ArrayList<String> balancedReactants = new ArrayList<>();
        ArrayList<String> balancedProducts = new ArrayList<>();

        for (int i = 0; i < reactants.size(); i++) {
            int coeff = finalCoefficients.get(i);
            String balanced = (coeff != 1 ? coeff : "") + reactants.get(i);
            balancedReactants.add(balanced);
        }

        for (int i = 0; i < products.size(); i++) {
            int coeff = finalCoefficients.get(i + reactants.size());
            String balanced = (coeff != 1 ? coeff : "") + products.get(i);
            balancedProducts.add(balanced);
        }


        // Adding empty strings to later be placed in the unused slots
        if (balancedReactants.size() < 2) {
            balancedReactants.add("");
            balancedReactants.add("");
            System.out.println("REACTANT - 2 empty Strings added");
        }

        if (balancedReactants.size() < 3) {
            balancedReactants.add("");
            System.out.println("REACTANT - 1 empty string added");
        }

        if (balancedProducts.size() < 2) {
            balancedProducts.add("");
            balancedProducts.add("");
            System.out.println("PRODUCT - 2 empty Strings added");
        }
        if (balancedProducts.size() < 3) {
            balancedProducts.add("");
            System.out.println("PRODUCT - 1 empty string added");
        }

        ArrayList<ArrayList<String>> balancedReactantsAndProducts = new ArrayList<>();
        balancedReactantsAndProducts.add(balancedReactants);
        balancedReactantsAndProducts.add(balancedProducts);

        return balancedReactantsAndProducts;
    }

    /**
     * Takes in concentration, energy or mass and finds the missing input. Replaces the missing input through simple
     *  addition and substraction
     * @param molecule1 TextField containing a numerical value of concentration, energy or mass
     * @param molecule2 TextField containing a numerical value of concentration, energy or mass
     * @param molecule3 TextField containing a numerical value of concentration, energy or mass
     * @param molecule4 TextField containing a numerical value of concentration, energy or mass
     * @param molecule5 TextField containing a numerical value of concentration, energy or mass
     * @param molecule6 TextField containing a numerical value of concentration, energy or mass
     * @return An updated arraylist of all the fields including missing value
     */
    public ArrayList<String> missingFieldCalculator(TextField molecule1, TextField molecule2, TextField molecule3,
                                                    TextField molecule4, TextField molecule5, TextField molecule6) {
        TextField[] textFields = {molecule1, molecule2, molecule3, molecule4, molecule5, molecule6};
        int emptyFieldPosition = -1;
        Double[] values = new Double[6];

        for (int i = 0; i < textFields.length; i++) {
            String text = textFields[i].getText().trim();
            if (text.isEmpty()) {
                emptyFieldPosition = i;
            } else {
                values[i] = Double.parseDouble(text);
            }
        }


        double missingValue = 0;
        switch (emptyFieldPosition) {
            case 0: // Reactant 1 missing
                missingValue = (values[3] + values[4] + values[5]) - (values[1] + values[2]);
                break;
            case 1: // Reactant 2 missing
                missingValue = (values[3] + values[4] + values[5]) - (values[0] + values[2]);
                break;
            case 2: // Reactant 3 missing
                missingValue = (values[3] + values[4] + values[5]) - (values[0] + values[1]);
                break;
            case 3: // Product 1 missing
                missingValue = (values[0] + values[1] + values[2]) - (values[4] + values[5]);
                break;
            case 4: // Product 2 missing
                missingValue = (values[0] + values[1] + values[2]) - (values[3] + values[5]);
                break;
            case 5: // Product 3 missing
                missingValue = (values[0] + values[1] + values[2]) - (values[3] + values[4]);
                break;
        }
        if (textFields[emptyFieldPosition] != null) {
            textFields[emptyFieldPosition].setText(String.valueOf(missingValue));
        }
        values[emptyFieldPosition] = missingValue;

        ArrayList<String> result = new ArrayList<>();
        for (Double v : values) {
            result.add(String.valueOf(v));
        }
        return result;
    }
    public void errorBox(String exceptionName) {
        Stage stage = new Stage();
        stage.setTitle("Error.");

        TextField errorMessage = new TextField("Invalid " + exceptionName + ". Please Try Again.");
        errorMessage.setAlignment(Pos.CENTER);

        Scene scene = new Scene(errorMessage, 100, 100);
        stage.setScene(scene);
        stage.show();
    }
}
