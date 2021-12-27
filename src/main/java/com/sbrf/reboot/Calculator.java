package com.sbrf.reboot;

public class Calculator {

    public double getAddition(double a, double b) {
        return a + b;
    }

    public double getSubtraction(double a, double b) {
        return a - b;
    }

    public double getMultiplication(double a, double b) {
        return a * b;
    }

    public double getDivision(double a, double b) {
        return a / b;
    }

    public double getSquareNumber(double num) {
        return Math.pow(num, 2);
    }

    public double getSquareRoot(double num) {
        return Math.sqrt(num);
    }

    public double getRemainder(double a, double b) {
        return a % b;
    }
}
