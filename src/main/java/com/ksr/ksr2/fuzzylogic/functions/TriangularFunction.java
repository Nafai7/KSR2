package com.ksr.ksr2.fuzzylogic.functions;

public class TriangularFunction  implements MembershipFunction{
    private final double leftMinimum;
    private final double rightMinimum;
    private final double maximum;

    public TriangularFunction(double leftMinimum, double rightMinimum, double maximum) {
        this.leftMinimum = leftMinimum;
        this.rightMinimum = rightMinimum;
        this.maximum = maximum;
    }

    @Override
    public double getMembership(double x) {
        if (x < leftMinimum || x > rightMinimum) {
            return 0;
        } else if (x == maximum) {
            return 1;
        } else if (x < maximum){
            return (x - leftMinimum)/(maximum - leftMinimum);
        } else {
            return (rightMinimum - x)/(rightMinimum - maximum);
        }
    }

    @Override
    public double getCardinality() {
        return (rightMinimum - leftMinimum)/2;
    }

    @Override
    public double getSupport() {
        return rightMinimum - leftMinimum;
    }
}
