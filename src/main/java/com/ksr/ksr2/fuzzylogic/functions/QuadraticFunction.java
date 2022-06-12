package com.ksr.ksr2.fuzzylogic.functions;

public class QuadraticFunction implements MembershipFunction{
    private final double leftMinimum;
    private final double rightMinimum;
    private final double leftMaximum;
    private final double rightMaximum;
    private final boolean positive;

    public QuadraticFunction(double leftMinimum, double rightMinimum, double leftMaximum, double rightMaximum, boolean positive) {
        this.leftMinimum = leftMinimum;
        this.rightMinimum = rightMinimum;
        this.leftMaximum = leftMaximum;
        this.rightMaximum = rightMaximum;
        this.positive = positive;
    }

    @Override
    public double getMembership(double x) {
        if (x <= leftMinimum || x >= rightMinimum) {
            return 0;
        } else if (x >= leftMaximum && x <= rightMaximum) {
            return 1;
        } else {
            return quadraticValue(x);
        }
    }

    private double quadraticValue(double x) {
        double border;
        double divider;
        if (positive) {
            if (x < leftMaximum) {
                border = leftMinimum;
                divider = leftMaximum - leftMinimum;
            } else {
                border = rightMinimum;
                divider = rightMinimum - rightMaximum;
            }
            return Math.pow((x/divider) - (border/divider), 2);
        } else {
            if (x < leftMaximum) {
                border = leftMaximum;
                divider = leftMaximum - leftMinimum;
            } else {
                border = rightMaximum;
                divider = rightMinimum - rightMaximum;
            }
            return 1 - Math.pow((x/divider) - (border/divider), 2);
        }
    }

    @Override
    public double getCardinality() {
        double cardinality = 0;
        for (double i = leftMinimum; i < rightMinimum; i += 0.1) {
            cardinality += ((getMembership(i) + getMembership(i + 1)  * 0.1) / 2);
        }
        return cardinality;
    }

    @Override
    public double getSupport() {
        return rightMinimum - leftMinimum;
    }
}
