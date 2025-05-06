package com.example.chemistryapp.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PHCalculatorTest {

    @Test
    void convertTopH() {
        PHCalculator calc = new PHCalculator();
        double expected = 9.0;
        assertEquals(expected, calc.convertTopH(5));

    }

    @Test
    void convertTopOH() {
        PHCalculator calc = new PHCalculator();
        double expected = 5.0;
        assertEquals(expected, calc.convertTopH(9));
    }

    @Test
    void h30ConcentrationTopH() {
        PHCalculator calc = new PHCalculator();
        double expected = 3.6;
        assertEquals(expected, calc.H30ConcentrationTopH(0.00025));
    }

    @Test
    void OHConcentrationTopOH() {
        PHCalculator calc = new PHCalculator();
        double expected = 4;
        assertEquals(expected, calc.OHConcentrationTopOH(0.0001));
    }

    @Test
    void pHToH30Concentration() {
        PHCalculator calc = new PHCalculator();
        double expected = 0.00031622776601683794;
        assertEquals(expected, calc.pHToH30Concentration(3.5));
    }

    @Test
    void pOHToOHConcentration() {
        PHCalculator calc = new PHCalculator();
        double expected = 0.0000019952623149688787;
        assertEquals(expected, calc.pOHToOHConcentration(5.7));
    }
}