package com.example.chemistryapp.Controller;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StoichiometryControllerTest {

    // Test for a simple Molecule
    @Test
    void getSimpleElementSubscripts() {
        String formula = "H2O";
        StoichiometryController stoichio = new StoichiometryController();

        ArrayList<Integer> result = stoichio.getElementSubscripts(formula);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);

        assertEquals(expected, result);
    }

    // Test for a more complex Molecule
    @Test
    public void getComplexElementSubscripts() {
        StoichiometryController stoichio = new StoichiometryController();
        String formula = "C6H12O6";

        ArrayList<Integer> result = stoichio.getElementSubscripts(formula);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(6);   // C6
        expected.add(12);  // H12
        expected.add(6);   // O6

        assertEquals(expected, result);
    }


    @Test
    void getUniqueElements() {
        StoichiometryController parser = new StoichiometryController();
        ArrayList<String> reactants = new ArrayList<>();
        reactants.add("C6H12O6");
        reactants.add("O2");
        reactants.add("CO2");

        ArrayList<String> result = parser.getUniqueElements(reactants);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("C");
        expected.add("H");
        expected.add("O");

        assertTrue(result.containsAll(expected));
        assertEquals(expected.size(), result.size());
    }


    @Test
    void createMatrix() {
        StoichiometryController parser = new StoichiometryController();

        ArrayList<String> reactants = new ArrayList<>();
        reactants.add("H2");
        reactants.add("O2");

        ArrayList<String> products = new ArrayList<>();
        products.add("H2O");

        ArrayList<String> elements = new ArrayList<>();
        elements.add("H");
        elements.add("O");

        double[][] result = parser.createMatrix(reactants, products, elements);

        double[][] expected = {
                { 2, 0, -2 },
                { 0, 2, -1 }
        };

        // Comparing each value in the matrices
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }
    }

    @Test
    void getElementSymbols() {
        StoichiometryController stoichio = new StoichiometryController();
        String formula = "C6H12O6";

        ArrayList<String> result = stoichio.getElementSymbols(formula);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("C");
        expected.add("H");
        expected.add("O");

        assertEquals(expected, result);
    }

    @Test
    void getBalancedEquation() {
        StoichiometryController stoichio = new StoichiometryController();
        /*double[][] rrefMatrix = {
                { 1, -1, 0, 0 },
                { 0, 1, -1, 0 },
                { 0, 0, 0, 1 }
        };*/

        // Unbalanced Inputs
        ArrayList<String> reactants = new ArrayList<>();
        reactants.add("H2");
        reactants.add("O2");
        ArrayList<String> products = new ArrayList<>();
        products.add("H2O");

        // Expected output
        ArrayList<String> expectedReactants = new ArrayList<>();
        expectedReactants.add("2H2");
        expectedReactants.add("O2");
        expectedReactants.add("");
        ArrayList<String> expectedProducts = new ArrayList<>();
        expectedProducts.add("2H2O");
        expectedProducts.add("");
        expectedProducts.add("");

        double[][] rrefMatrix = stoichio.rref(stoichio.createMatrix(reactants, products, stoichio.getUniqueElements(reactants)));

        ArrayList<ArrayList<String>> result = stoichio.getBalancedEquation(rrefMatrix, reactants, products);

        // Verifying the ArrayList of balanced reactants and products
        assertEquals(expectedReactants, result.get(0));
        assertEquals(expectedProducts, result.get(1));
    }
}