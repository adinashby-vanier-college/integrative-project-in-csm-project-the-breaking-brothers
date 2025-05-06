package com.example.chemistryapp.Controller;

public class AcidBaseCalculator {

    // Calculate the amount of water produced in a neutralization reaction
    public static double calculateWaterProduced(double acidMoles, double baseMoles) {
        return Math.min(acidMoles, baseMoles);  // 1:1 reaction produces 1 mol H2O per mole
    }

    // Determine the resulting salt
    public static String getSalt(String acid, String base) {
        if (acid.equalsIgnoreCase("HCl") && base.equalsIgnoreCase("NaOH")) return "NaCl (Sodium Chloride)";
        if (acid.equalsIgnoreCase("H2SO4") && base.equalsIgnoreCase("NaOH")) return "Na2SO4 (Sodium Sulfate)";
        if (acid.equalsIgnoreCase("HNO3") && base.equalsIgnoreCase("KOH")) return "KNO3 (Potassium Nitrate)";
        return "Unknown Salt (Check Input)";
    }

    // Convert Mass to Moles
    public static double convertMassToMoles(double mass, double molarMass) {
        return mass / molarMass;
    }
}