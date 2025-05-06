package com.example.cal;

public class RealGasCalculator {
    private static final double R = 8.314; // Ideal Gas Constant (J/(mol·K))

    public static double calculateMissingValue(Double P, Double V, Double n, Double T, Double a, Double b) {
        if (P == null) {
            return ((n * R * T) / (V - n * b)) - ((a * Math.pow(n, 2)) / Math.pow(V, 2)); // Solve for Pressure (P)
        } else if (V == null) {
            // Use numerical approximation since the equation is nonlinear
            throw new UnsupportedOperationException("Solving for V is complex and requires iteration.");
        } else if (n == null) {
            return (P + (a * Math.pow(n, 2)) / Math.pow(V, 2)) * (V - n * b) / (R * T); // Solve for n (Amount)
        } else if (T == null) {
            return ((P + (a * Math.pow(n, 2)) / Math.pow(V, 2)) * (V - n * b)) / (n * R); // Solve for T (Temperature)
        } else if (a == null || b == null) {
            throw new IllegalArgumentException("a and b must be provided.");
        }
        throw new IllegalArgumentException("Exactly one parameter must be null.");
    }
}