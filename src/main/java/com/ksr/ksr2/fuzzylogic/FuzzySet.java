package com.ksr.ksr2.fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class FuzzySet {
    protected ClassicSet universeOfDiscourse;
    protected MembershipFunction membershipFunction;

    FuzzySet(ClassicSet universeOfDiscourse, MembershipFunction membershipFunction) {

        this.universeOfDiscourse = universeOfDiscourse;
        this.membershipFunction = membershipFunction;
    }

    public ClassicSet getUniverse() {
        return universeOfDiscourse;
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    public double getSupport() {
//        if (universeOfDiscourse.isDiscrete()) {
//            List<Double> suppSet = new ArrayList<>();
//            for (Double x : universeOfDiscourse.getSet()) {
//                if (membershipFunction.getMembership(x) > 0) {
//                    suppSet.add(x);
//                }
//            }
//
//            return new ClassicSet(suppSet);
//        } else {
//            //TODO support dla przedziału
//        }

        return 0;
    }

    public ClassicSet getAlphaCut(double alfa) {
        if (universeOfDiscourse.isDiscrete()) {
            List<Double> suppSet = new ArrayList<>();
            for (Double x : universeOfDiscourse.getSet()) {
                if (membershipFunction.getMembership(x) > alfa) {
                    suppSet.add(x);
                }
            }

            return new ClassicSet(suppSet);
        } else {
            //TODO alpha cut dla przedziału
        }

        return null;
    }

    public double getHeight() {
        double height = Double.NEGATIVE_INFINITY;
        if (universeOfDiscourse.isDiscrete()) {
            for (Double x : universeOfDiscourse.getSet()) {
                double membership = membershipFunction.getMembership(x);
                if (membership > height) {
                    height = membership;
                }
            }
        } else {
            //TODO wysokość dla przedziału
        }

        return height;
    }

    public double getCardinality() {
        double cardinality = 0;
        if (universeOfDiscourse.isDiscrete()) {
            for (Double x : universeOfDiscourse.getSet()) {
                cardinality += membershipFunction.getMembership(x);
            }
        } else {
            //TODO kardynalność dla przedziału
        }

        return cardinality;
    }

    public boolean isNormal() {
        if (getHeight() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isConvex() {
        //TODO wypukłość
        return false;
    }

    public boolean isEmpty() {
        boolean empty = false;
        if (universeOfDiscourse.isDiscrete()) {
            for (Double x : universeOfDiscourse.getSet()) {
                if (membershipFunction.getMembership(x) > 0) {
                    empty = true;
                }
            }
        } else {
            //TODO pustość dla przedziału dla przedziału
        }

        return empty;
    }

//    public double degreeOfFuzziness() {
//        return (double) getSupport().getSet().size() / universeOfDiscourse.getSet().size();
//    }
    
}
