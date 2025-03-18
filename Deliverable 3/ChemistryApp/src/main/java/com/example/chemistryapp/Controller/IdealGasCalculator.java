package com.example.chemistryapp.Controller;

// IdealGasCalculator.java (Handles the Ideal Gas Law Calculations)
public class IdealGasCalculator {
    private static final double R = 8.314; // Ideal Gas Constant in J/(mol·K)

    public static Double calculateMissingValue(Double P, Double V, Double n, Double T) {
        int missingCount = 0;
        if (P == null) missingCount++;
        if (V == null) missingCount++;
        if (n == null) missingCount++;
        if (T == null) missingCount++;

        if (missingCount != 1) {
            throw new IllegalArgumentException("Exactly one parameter must be missing.");
        }

        if (P == null) return (n * R * T) / V; // Solve for Pressure
        if (V == null) return (n * R * T) / P; // Solve for Volume
        if (n == null) return (P * V) / (R * T); // Solve for Amount (n)
        if (T == null) return (P * V) / (n * R); // Solve for Temperature

        return null; // This should never happen
    }
}
