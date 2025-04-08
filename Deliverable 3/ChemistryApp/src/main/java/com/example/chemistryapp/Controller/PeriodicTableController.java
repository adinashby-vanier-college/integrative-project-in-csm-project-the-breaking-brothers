package com.example.chemistryapp.Controller;

import java.util.HashMap;
import java.util.Map;

public class PeriodicTableController {

    public String[] colorsInfo() {
        String[] colors = {
                "#FF9999", "#FFB499", "#FFFF99", "#B4FF99",
                "#99FFFF", "#9999FF", "#FF99FF", "#FF99CC", "#F4AC56", "#F365E6"
        };
        return colors;
    }

    public Object[][] lanthanidesInfo() {
        Object[][] lan = {
                {"La", "Lanthanum", 57, 8}, {"Ce", "Cerium", 58, 8},
                {"Pr", "Praseodymium", 59, 8}, {"Nd", "Neodymium", 60, 8},
                {"Pm", "Promethium", 61, 8}, {"Sm", "Samarium", 62, 8},
                {"Eu", "Europium", 63, 8}, {"Gd", "Gadolinium", 64, 8},
                {"Tb", "Terbium", 65, 8}, {"Dy", "Dysprosium", 66, 8},
                {"Ho", "Holmium", 67, 8}, {"Er", "Erbium", 68, 8},
                {"Tm", "Thulium", 69, 8}, {"Yb", "Ytterbium", 70, 8},
                {"Lu", "Lutetium", 71, 8},
        };

        return lan;
    }

    public  Object[][] actinidesInfo () {
        Object[][] act = {
                {"Ac", "Actinium",89, 9}, {"Th", "Thorium", 90, 9},
                {"Pa", "Protactinium", 91, 9}, {"U", "Uranium", 92, 9},
                {"Np", "Neptunium", 93, 9}, {"Pu", "Plutonium", 94, 9},
                {"Am", "Americium", 95, 9}, {"Cm", "Curium", 96, 9},
                {"Bk", "Berkelium", 97, 9}, {"Cf", "Californium", 98, 9},
                {"Es", "Einsteinium", 99, 9}, {"Fm", "Fermium", 100, 9},
                {"Md", "Mendelevium", 101, 9}, {"No", "Nobelium", 102, 9},
                {"Lr", "Lawrencium", 103, 9}
        };
        return act;
    }

    public Object[][] elementInfo () {
        Object[][] elements = {
                // Period 1
                {0, 0, "H", "Hydrogen", 1, 5}, {0, 17, "He", "Helium", 2, 7},

                // Period 2
                {1, 0, "Li", "Lithium", 3, 0}, {1, 1, "Be", "Beryllium", 4, 1},
                {1, 12, "B", "Boron", 5, 4}, {1, 13, "C", "Carbon", 6, 5},
                {1, 14, "N", "Nitrogen", 7, 5}, {1, 15, "O", "Oxygen", 8, 5},
                {1, 16, "F", "Fluorine", 9, 6}, {1, 17, "Ne", "Neon", 10, 7},

                // Period 3
                {2, 0, "Na", "Sodium", 11, 0}, {2, 1, "Mg", "Magnesium", 12, 1},
                {2, 12, "Al", "Aluminum", 13, 3}, {2, 13, "Si", "Silicon", 14, 4},
                {2, 14, "P", "Phosphorus", 15, 5}, {2, 15, "S", "Sulfur", 16, 5},
                {2, 16, "Cl", "Chlorine", 17, 6}, {2, 17, "Ar", "Argon", 18, 7},

                // Period 4
                {3, 0, "K", "Potassium", 19, 0}, {3, 1, "Ca", "Calcium", 20, 1},
                {3, 2, "Sc", "Scandium", 21, 2}, {3, 3, "Ti", "Titanium", 22, 2},
                {3, 4, "V", "Vanadium", 23, 2}, {3, 5, "Cr", "Chromium", 24, 2},
                {3, 6, "Mn", "Manganese", 25, 2}, {3, 7, "Fe", "Iron", 26, 2},
                {3, 8, "Co", "Cobalt", 27, 2}, {3, 9, "Ni", "Nickel", 28, 2},
                {3, 10, "Cu", "Copper", 29, 2}, {3, 11, "Zn", "Zinc", 30, 2},
                {3, 12, "Ga", "Gallium", 31, 3}, {3, 13, "Ge", "Germanium", 32, 4},
                {3, 14, "As", "Arsenic", 33, 4}, {3, 15, "Se", "Selenium", 34, 5},
                {3, 16, "Br", "Bromine", 35, 6}, {3, 17, "Kr", "Krypton", 36, 7},

                // Period 5
                {4, 0, "Rb", "Rubidium", 37, 0}, {4, 1, "Sr", "Strontium", 38, 1},
                {4, 2, "Y", "Yttrium", 39, 2}, {4, 3, "Zr", "Zirconium", 40, 2},
                {4, 4, "Nb", "Niobium", 41, 2}, {4, 5, "Mo", "Molybdenum", 42, 2},
                {4, 6, "Tc", "Technetium", 43, 2}, {4, 7, "Ru", "Ruthenium", 44, 2},
                {4, 8, "Rh", "Rhodium", 45, 2}, {4, 9, "Pd", "Palladium", 46, 2},
                {4, 10, "Ag", "Silver", 47, 2}, {4, 11, "Cd", "Cadmium", 48, 2},
                {4, 12, "In", "Indium", 49, 3}, {4, 13, "Sn", "Tin", 50, 3},
                {4, 14, "Sb", "Antimony", 51, 4}, {4, 15, "Te", "Tellurium", 52, 4},
                {4, 16, "I", "Iodine", 53, 6}, {4, 17, "Xe", "Xenon", 54, 7},

                // Period 6
                {5, 0, "Cs", "Cesium", 55, 0}, {5, 1, "Ba", "Barium", 56, 1},
                {5, 2, "*", "***", 57, 8}, {5, 3, "Hf", "Hafnium", 72, 2},
                {5, 4, "Ta", "Tantalum", 73, 2}, {5, 5, "W", "Tungsten", 74, 2},
                {5, 6, "Re", "Rhenium", 75, 2}, {5, 7, "Os", "Osmium", 76, 2},
                {5, 8, "Ir", "Iridium", 77, 2}, {5, 9, "Pt", "Platinum", 78, 2},
                {5, 10, "Au", "Gold", 79, 2}, {5, 11, "Hg", "Mercury", 80, 2},
                {5, 12, "Tl", "Thallium", 81, 3}, {5, 13, "Pb", "Lead", 82, 3},
                {5, 14, "Bi", "Bismuth", 83, 3}, {5, 15, "Po", "Polonium", 84, 4},
                {5, 16, "At", "Astatine", 85, 6}, {5, 17, "Rn", "Radon", 86, 7},

                // Period 7
                {6, 0, "Fr", "Francium", 87, 0}, {6, 1, "Ra", "Radium", 88, 1},
                {6, 2, "*", "***", 89, 9}, {6, 3, "Rf", "Rutherfordium", 104, 2},
                {6, 4, "Db", "Dubnium", 105, 2}, {6, 5, "Sg", "Seaborgium", 106, 2},
                {6, 6, "Bh", "Bohrium", 107, 2}, {6, 7, "Hs", "Hassium", 108, 2},
                {6, 8, "Mt", "Meitnerium", 109, 2}, {6, 9, "Ds", "Darmstadtium", 110, 2},
                {6, 10, "Rg", "Roentgenium", 111, 2}, {6, 11, "Cn", "Copernicium", 112, 2},
                {6, 12, "Nh", "Nihonium", 113, 3}, {6, 13, "Fl", "Flerovium", 114, 3},
                {6, 14, "Mc", "Moscovium", 115, 3}, {6, 15, "Lv", "Livermorium", 116, 3},
                {6, 16, "Ts", "Tennessine", 117, 6}, {6, 17, "Og", "Oganesson", 118, 7}
        };

        return elements;
    }

    public Object[][] getElementDetails (int atomicNumber) {
        com.example.chemistryapp.Controller.ElementData elementData = ElementDataScraper.scrapeElementData(atomicNumber);

        return new Object[][] {
                {"Name", elementData.getName()},
                {"Symbol", elementData.getSymbol()},
                {"Atomic Number", elementData.getAtomicNumber()},
                {"Atomic Weight", elementData.getAtomicWeight()},
                {"Group", elementData.getGroup()},
                {"Period", elementData.getPeriod()},
                {"Electron Configuration", elementData.getElectronConfiguration()},
                {"Density", elementData.getDensity()},
                {"Boiling Point", elementData.getBoilingPoint()},
                {"Melting Point", elementData.getMeltingPoint()},
                {"Description", elementData.getDescription()}
        };
    }
}
