package com.ksr.ksr2.fuzzylogic.functions;

public class TrapezoidalFunction  implements MembershipFunction{
    private final double leftMinimum;
    private final double rightMinimum;
    private final double leftMaximum;
    private final double rightMaximum;

    public TrapezoidalFunction(double leftMinimum, double rightMinimum, double leftMaximum, double rightMaximum) {
        this.leftMinimum = leftMinimum;
        this.rightMinimum = rightMinimum;
        this.leftMaximum = leftMaximum;
        this.rightMaximum = rightMaximum;
    }

    @Override
    public double getMembership(double x) {
        if (leftMinimum < leftMaximum) {
            if (x < leftMinimum || x > rightMinimum) {
                return 0;
            } else if (x >= leftMaximum && x <= rightMaximum) {
                return 1;
            } else if (x < leftMaximum) {
                return Math.abs((x - leftMinimum)/(leftMaximum - leftMinimum));
            } else {
                return Math.abs((rightMinimum - x)/(rightMinimum - rightMaximum));
            }
        } else {
            if (x < leftMaximum || x > rightMaximum) {
                return 1;
            } else if (x >= leftMinimum && x <= rightMinimum) {
                return 0;
            } else if (x > rightMinimum) {
                return Math.abs((x - rightMinimum)/(rightMaximum - rightMinimum));
            } else {
                return Math.abs((leftMinimum - x)/(leftMinimum - leftMaximum));
            }
        }
    }

    @Override
    public double getCardinality() {
        return (rightMinimum - leftMinimum) + (rightMaximum - leftMaximum)/2;
    }

    @Override
    public double getSupport() {
        return rightMinimum - leftMinimum;
    }
}
