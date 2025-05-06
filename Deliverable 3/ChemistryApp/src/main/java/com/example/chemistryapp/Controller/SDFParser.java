package com.example.chemistryapp.Controller;

import com.example.chemistryapp.Model.Atom;

import java.io.*;
import java.util.*;


/**
 * Class used to store the data of the molecules scraped from pubChem
 */
public class SDFParser {

    public static List<Atom> parseSDF(String filename) throws IOException {
        List<Atom> atoms = new ArrayList<>();
        List<int[]> bonds = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        int atomCount = 0, bondCount = 0;
        int lineIndex = 0;

        while ((line = reader.readLine()) != null) {
            lineIndex++;

            if (lineIndex == 4) { //this is the first line with the atom information
                String[] parts = line.trim().split("\\s+");
                atomCount = Integer.parseInt(parts[0]);
                bondCount = Integer.parseInt(parts[1]);
            }
            else if (lineIndex > 4 && lineIndex <= 4 + atomCount) { // All atom information in here
                String[] parts = line.trim().split("\\s+");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                double z = Double.parseDouble(parts[2]);
                String element = parts[3];

                atoms.add(new Atom(element, x, y, z));
            }
            else if (lineIndex > 4 + atomCount && lineIndex <= 4 + atomCount + bondCount) { // Read bonds
                String[] parts = line.trim().split("\\s+");
                int atom1 = Integer.parseInt(parts[0]) - 1;  // we start counting from 0 so -1
                int atom2 = Integer.parseInt(parts[1]) - 1;
                bonds.add(new int[]{atom1, atom2});
            }
        }

        reader.close();

        return atoms;
    }

    // Holds atoms and bonds
    public static class MoleculeData {
        List<Atom> atoms;
        List<int[]> bonds;

        public MoleculeData(List<Atom> atoms, List<int[]> bonds) {
            this.atoms = atoms;
            this.bonds = bonds;
        }

        public List<Atom> getAtoms() {
            return atoms;
        }

        public List<int[]> getBonds() {
            return bonds;
        }
    }
}

