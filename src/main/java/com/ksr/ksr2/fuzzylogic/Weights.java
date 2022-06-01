package com.ksr.ksr2.fuzzylogic;

import java.util.List;

public class Weights {
    List<Double> weights;

    Weights(List<Double> weights) {
        this.weights = weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public boolean correct() {
        double sum = 0;
        for (double weight: weights) {
            sum += weight;
        }
        if (sum != 1) {
            return false;
        } else {
            return true;
        }
    }
}
