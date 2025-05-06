package com.example.chemistryapp.Controller;

public class PHCalculator {
    public double convertTopH(double pOh) {
        return 14 - pOh;
    }

    public double convertTopOH(double pH) {
        return 14 - pH;
    }

    public double H30ConcentrationTopH(double concentration) {
        double pH = -(Math.log10(concentration));
        return Math.round(pH * 10.0) / 10.0; // Rounding to 1 decimal
    }

    public double OHConcentrationTopOH(double concentration) {
        double pOH = -(Math.log10(concentration));
        return Math.round((pOH * 10)/10);
    }

    public double pHToH30Concentration(double pH) {
        return Math.pow(10, -pH);
    }

    public double pOHToOHConcentration(double pOH) {
        return Math.pow(10, -pOH);
    }
}
