package com.example.chemistryapp.Controller;

public class GibbsFreeEnergyCalculator {

    // Gibbs Free Energy Equation: ΔG = ΔH - T(ΔS)
    public static double calculateMissingValue(Double deltaG, Double deltaH, Double temperature, Double deltaS) {
        if (deltaG == null) {
            return deltaH - (temperature * deltaS); // Solve for ΔG
        } else if (deltaH == null) {
            return deltaG + (temperature * deltaS); // Solve for ΔH
        } else if (temperature == null) {
            return (deltaH - deltaG) / deltaS; // Solve for T
        } else if (deltaS == null) {
            return (deltaH - deltaG) / temperature; // Solve for ΔS
        }
        throw new IllegalArgumentException("Exactly one parameter must be null.");
    }

    // Determine spontaneity based on ΔG
    public static String isReactionSpontaneous(double deltaG) {
        if (deltaG < 0) return "Spontaneous";
        if (deltaG > 0) return "Non-Spontaneous";
        return "At Equilibrium";
    }
}
