package com.example.chemistryapp.Controller;

public class PHCalculator {
    public double convertTopH(double pOh) {
        return 14 - pOh;
    }

    public double convertTopOH(double pH) {
        return 14 - pH;
    }

    public double H30ConcentrationTopH(double concentration) {
        return -(Math.log(concentration));
    }

    public double OHConcentrationTopOH(double concentration) {
        return -(Math.log(concentration));
    }

    public double pHToH30Concentration(double pH) {
        return Math.pow(10, -pH);
    }

    public double pOHToOHConcentration(double pOH) {
        return Math.pow(10, -pOH);
    }
}
