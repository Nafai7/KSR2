package com.ksr.ksr2.fuzzylogic.functions;

public class SinusoidalFunction implements MembershipFunction{
    private final double leftMinimum;
    private final double rightMinimum;
    private final double leftMaximum;
    private final double rightMaximum;
    private final int piValue;
    private final int xValue;

    //for label functions
    public SinusoidalFunction(double leftMinimum, double rightMinimum, int piValue, double leftMaximum, double rightMaximum) {
        this.leftMinimum = leftMinimum;
        this.rightMinimum = rightMinimum;
        this.leftMaximum = leftMaximum;
        this.rightMaximum = rightMaximum;
        this.piValue = piValue;
        this.xValue = 0;
    }

    //for quantifier functions
    public SinusoidalFunction(double leftMinimum, double rightMinimum, int piValue, int xValue, double leftMaximum, double rightMaximum) {
        this.leftMinimum = leftMinimum;
        this.rightMinimum = rightMinimum;
        this.leftMaximum = leftMaximum;
        this.rightMaximum = rightMaximum;
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
                return (Math.sin((x + piValue * Math.PI)/rightMinimum - rightMaximum) + 1)/2;
            } else {
                return (Math.sin(xValue * x + piValue * Math.PI / 24) + 1)/2;
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
