package com.ksr.ksr2.fuzzylogic;

import java.util.List;

public class ClassicSet {
    protected List<Double> set;
    protected double begin;
    protected double end;
    protected boolean discrete;

    ClassicSet(List<Double> set) {
        discrete = true;
        this.set = set;
    }

    ClassicSet(double begin, double end) {
        discrete = false;
        this.begin = begin;
        this.end = end;
    }

    public double getSize() {
        if (discrete) {
            return set.size();
        } else {
            return end - begin;
        }
    }

    public List<Double> getSet() {
        return set;
    }

    public double getBegin() {
        return begin;
    }

    public double getEnd() {
        return end;
    }

    public boolean isDiscrete() {
        return discrete;
    }

    public boolean isEmpty() {
        if (discrete) {
            return set.isEmpty();
        } else {
            if ( (begin - end) > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
