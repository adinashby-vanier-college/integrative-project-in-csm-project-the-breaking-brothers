package com.example.chemistryapp.Controller;

public class PHCalculator {
    public double convertTopH(double pOh) {
        return 14 - pOh;
    }

    public double convertTopOH(double pH) {
        return 14 - pH;
    }
}
