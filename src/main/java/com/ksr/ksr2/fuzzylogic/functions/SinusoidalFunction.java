package com.ksr.ksr2.fuzzylogic.functions;

public class SinusoidalFunction implements MembershipFunction{
    private final double leftMinimum;
    private final double rightMinimum;
    private final double leftMaximum;
    private final double rightMaximum;
    private final double piValue;
    private final int xValue;

    //for label functions
    public SinusoidalFunction(double leftMinimum, double rightMinimum, double leftMaximum, double rightMaximum, double piValue) {
        this.leftMinimum = leftMinimum;
        this.rightMinimum = rightMinimum;
        this.leftMaximum = leftMaximum;
        this.rightMaximum = rightMaximum;
        this.piValue = piValue;
        this.xValue = 0;
    }

    //for quantifier functions
    public SinusoidalFunction(double leftMinimum, double rightMinimum, double piValue, int xValue) {
        this.leftMinimum = leftMinimum;
        this.rightMinimum = rightMinimum;
        this.leftMaximum = (rightMinimum - leftMinimum) / 2;
        this.rightMaximum = (rightMinimum - leftMinimum) / 2;;
        this.piValue = piValue;
        this.xValue = xValue;
    }

    @Override
    public double getMembership(double x) {
        if (x < leftMinimum || x > rightMinimum) {
            return 0;
        } else if (x >= leftMaximum && x <= rightMaximum) {
            return 1;
        } else {
            if (xValue == 0) {
                return (Math.sin((x + (piValue * Math.PI))/10) + 1) / 2;
            } else {
                return (Math.sin((xValue * x) + (piValue * Math.PI)) + 1) / 2;
            }
        }
    }

    @Override
    public double getCardinality() {
        double cardinality = 0;
        for (double i = leftMinimum; i < rightMinimum; i += 0.1) {
            cardinality += (getMembership(i) + getMembership(i + 1) / 2 * 0.1);
        }
        return cardinality;
    }

    @Override
    public double getSupport() {
        return rightMinimum - leftMinimum;
    }
}
