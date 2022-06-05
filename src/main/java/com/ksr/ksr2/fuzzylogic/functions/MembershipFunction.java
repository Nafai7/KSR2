package com.ksr.ksr2.fuzzylogic.functions;

public interface MembershipFunction {
    public double getMembership(double x);
    public double getCardinality();
    public double getSupport();
}
