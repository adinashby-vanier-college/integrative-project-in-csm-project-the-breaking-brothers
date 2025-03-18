package com.example.chemistryapp.Model;

public class Atom {
    public String element;
    public double x, y, z;

    public Atom(String element, double x, double y, double z) {
        this.element = element;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return element + " (" + x + ", " + y + ", " + z + ")";
    }
}