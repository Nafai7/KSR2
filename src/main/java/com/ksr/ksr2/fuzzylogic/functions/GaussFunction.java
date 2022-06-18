package com.ksr.ksr2.fuzzylogic.functions;

public class GaussFunction  implements MembershipFunction{
    private final double maximum;
    private final double variance;

    public GaussFunction(double variance, double maximum) {
        this.variance = variance;
        this.maximum = maximum;
    }

    @Override
    public double getMembership(double x) {
        return 1/(variance * Math.sqrt(2 * Math.PI)) * Math.pow(Math.E, -(x - maximum)*(x - maximum)/(2 * variance * variance));
    }

    @Override
    public double getCardinality() {
        return 1;
    }

    @Override
    public double getSupport() {
        return variance*2.4;
    }
}
