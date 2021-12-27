package com.sbrf.reboot.calculator;

import com.sbrf.reboot.Calculator;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void getAddition() {
        assertEquals(9, new Calculator().getAddition(4, 5));
    }

    @Test
    void getSubtraction() {
        assertEquals(-1, new Calculator().getSubtraction(4, 5));
    }

    @Test
    void getMultiplication() {
        assertEquals(20, new Calculator().getMultiplication(4, 5));
    }

    @Test
    void getDivision() {
        assertEquals(3, new Calculator().getDivision(9, 3));
    }

    @Test
    void getSquareNumber() {
        assertEquals(4, new Calculator().getSquareNumber(2));
        assertEquals(81, new Calculator().getSquareNumber(-9));
        assertEquals(0, new Calculator().getSquareNumber(0));
    }

    @Test
    void getSquareRoot() {
        assertEquals(5, new Calculator().getSquareRoot(25));
        assertEquals(0, new Calculator().getSquareRoot(0));
        assertEquals(NaN, new Calculator().getSquareRoot(-25));
    }

    @Test
    void getRemainder() {
        assertEquals(0, new Calculator().getRemainder(5, 5));
        assertEquals(2, new Calculator().getRemainder(2, 3));
        assertEquals(0, new Calculator().getRemainder(0, 3));
        assertEquals(-5, new Calculator().getRemainder(-5, 223));
    }

    @Test
    void classHasSevenMethods() {
        assertEquals(7, Calculator.class.getMethods().length - Object.class.getMethods().length);
    }
}