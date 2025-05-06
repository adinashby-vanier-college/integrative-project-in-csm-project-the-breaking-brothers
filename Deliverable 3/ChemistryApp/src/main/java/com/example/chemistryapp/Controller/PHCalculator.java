package com.example.chemistryapp.Controller;

public class PHCalculator {
    /**
     * Converts a pOH value to pH
     * @param pOh user input
     * @return a pH value
     */
    public double convertTopH(double pOh) {
        return 14 - pOh;
    }

    /**
     * Converts a pH value to pOH
     * @param pH user input
     * @return a pOH value
     */
    public double convertTopOH(double pH) {
        return 14 - pH;
    }

    /**
     * H3O+ concentration and finds the pH of the concentration
     * @param concentration user input
     * @return pH of the solution
     */
    public double H30ConcentrationTopH(double concentration) {
        double pH = -(Math.log10(concentration));
        return Math.round(pH * 10.0) / 10.0; // Rounding to 1 decimal
    }

    /**
     * OH- concentration and finds the pOH of the solution
     * @param concentration user input
     * @return pOh value
     */
    public double OHConcentrationTopOH(double concentration) {
        double pOH = -(Math.log10(concentration));
        return Math.round((pOH * 10)/10);
    }

    /**
     * Takes in a pH value and returns its concentration
     * @param pH user input
     * @return concentration of H3O+
     */
    public double pHToH30Concentration(double pH) {
        return Math.pow(10, -pH);
    }

    /**
     * Takes in a pOH value and returns its concentration
     * @param pOH user input
     * @return concentration of OH-
     */
    public double pOHToOHConcentration(double pOH) {
        return Math.pow(10, -pOH);
    }
}
