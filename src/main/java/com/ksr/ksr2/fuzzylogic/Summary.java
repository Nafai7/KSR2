package com.ksr.ksr2.fuzzylogic;

import com.ksr.ksr2.model.BodySignals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Summary {
    protected Quantifier quantifier;
    protected List<Label> qualifiers;
    protected List<Label> summarizers;
    protected List<BodySignals> bodySignals;

    private Weights weights;
    private double T1 = 0;
    private double T2 = 0;
    private double T3 = 0;
    private double T4 = 0;
    private double T5 = 0;
    private double T6 = 0;
    private double T7 = 0;
    private double T8 = 0;
    private double T9 = 0;
    private double T10 = 0;
    private double T11 = 0;
    private double goodness = 0;

    public Summary(Quantifier quantifier, List<Label> qualifiers, List<Label> summarizers, List<BodySignals> bodySignals, Weights weights) {
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.bodySignals = bodySignals;
        this.weights = weights;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public List<Label> getQualifiers() {
        return qualifiers;
    }

    public List<Label> getSummarizers() {
        return summarizers;
    }

    public double degreeOfTruth() {
        return T1;
    }

    public double degreeOfImprecision() {
        return T2;
    }

    public double degreeOfCovering() {
        return T3;
    }

    public double degreeOfAppropriateness() {
        return T4;
    }

    public double lengthOfSummary() {
        return T5;
    }

    public double degreeOfQuantifierImprecision() {
        return T6;
    }

    public double degreeOfQuantifierCardinality() {
        if (T7 < 0) {
            return -T7;
        }
        return T7;
    }

    public double degreeOfSummarizerCardinality() {
        if (T8 < 0) {
            return -T8;
        }
        return T8;
    }

    public double degreeOfQualifierImprecision() {
        return T9;
    }

    public double degreeOfQualifierCardinality() {
        if (T10 < 0) {
            return -T10;
        }
        return T10;
    }

    public double lengthOfQualifier() {
        return T11;
    }

    public double goodnessOfTheSummary() {
        return goodness;
    }

    public void calcMeasures() {
        // T2
        T2 = 1;
        for (Label summarizer: summarizers) {
            T2 *= summarizer.getFuzzySet().getSupport() / summarizer.getFuzzySet().getUniverse().getSize();
        }
        T2 = Math.pow(T2, 1.0/summarizers.size());
        T2 = 1 - T2;

        // T5
        T5 = 2 * Math.pow(0.5, summarizers.size());

        // T6
        T6 = 0;
        double support = quantifier.getSet().getSupport();
        if (quantifier.isAbsolute()) {
            T6 = 1 - (support / bodySignals.size());
        } else {
            T6 = 1 - support;
        }

        // T7
        T7 = 0;
        double cardinality = quantifier.getSet().getCardinality();
        if (quantifier.isAbsolute()) {
            T7 = 1 - (cardinality / bodySignals.size());
        } else {
            T7 = 1 - cardinality;
        }

        // T8
        T8 = 1;
        for (Label summarizer: summarizers) {
            T8 *= summarizer.getFuzzySet().getCardinality() / summarizer.getFuzzySet().getUniverse().getSize();
        }
        T8 = Math.pow(T8, 1.0 / summarizers.size());
        T8 = 1 - T8;

        // T9
        T9 = 1;
        if (qualifiers.size() > 0) {
            for (Label qualifier: qualifiers) {
                T9 *= qualifier.getFuzzySet().getSupport() / qualifier.getFuzzySet().getUniverse().getSize();
            }
            T9 = Math.pow(T9, 1.0 / qualifiers.size());
            T9 = 1 - T9;
        }

        // T10
        T10 = 1;
        if (qualifiers.size() > 0) {
            for (Label qualifier : qualifiers) {
                T10 *= qualifier.getFuzzySet().getCardinality() / qualifier.getFuzzySet().getUniverse().getSize();
            }
            T10 = Math.pow(T10, 1.0 / qualifiers.size());
            T10 = 1 - T10;
        }

        // T11
        if (qualifiers.size() > 0) {
            T11 = 2 * Math.pow(0.5, qualifiers.size());
        } else {
            T11 = 0;
        }

        // T1 & T3
        double rTop = 0;
        double rBottom = 0;
        int[] cntSummarizersMoreThanZero = new int[summarizers.size()];
        int cntTop = 0;
        int cntBottom = 0;

        for (BodySignals bodySignal: bodySignals) {
            List<Double> summarizerMembership = new ArrayList<>();
            List<Double> qualifierMembership = new ArrayList<>();

            for (int i = 0; i < summarizers.size(); i++) {
//                System.out.print(summarizers.get(i).getLinguisticVariableName());
//                System.out.print("\n");
//                System.out.print(bodySignal.get(summarizers.get(i).getLinguisticVariableName()));
                double membership = summarizers.get(i).getFuzzySet().getMembershipFunction().getMembership(bodySignal.get(summarizers.get(i).getLinguisticVariableName()));
//                System.out.print("\n");
//                System.out.print(membership);
//                System.out.print("\n");
                summarizerMembership.add(membership);
                if (membership > 0) {
                    cntSummarizersMoreThanZero[i] += 1;
                }
            }

            for (Label qualifier: qualifiers) {
                qualifierMembership.add(qualifier.getFuzzySet().getMembershipFunction().getMembership(bodySignal.get(qualifier.getLinguisticVariableName())));
            }

            double tmp = Collections.min(summarizerMembership);
            double tmp2 = 1.0;

            if (qualifiers.size() > 0) {
                tmp2 = Collections.min(qualifierMembership);
            }

            double membership = Math.min(tmp, tmp2);

            rTop += membership;
            rBottom += tmp2;

            if (membership > 0) {
                cntTop += 1;
            }

            if (tmp2 > 0) {
                cntBottom += 1;
            }
        }

        T1 = quantifier.getSet().getMembershipFunction().getMembership(rTop / rBottom);
        T3 = (cntTop * 1.0) / cntBottom;

        // T4
        T4 = 1.0;
        for (int i = 0; i < summarizers.size(); i++) {
            T4 *= (cntSummarizersMoreThanZero[i] * 1.0) / bodySignals.size();
        }
        T4 -= T3;
        T4 = Math.abs(T4);

        List<Double> measures = new ArrayList<>();
        measures.add(T1);
        measures.add(T2);
        measures.add(T3);
        measures.add(T4);
        measures.add(T5);
        measures.add(T6);
        measures.add(T7);
        measures.add(T8);
        measures.add(T9);
        measures.add(T10);
        measures.add(T11);

        goodness = 0;
        for (int i = 0; i < measures.size(); i++) {
            goodness += weights.getWeights().get(i) * measures.get(i);
        }
    }

    public String toString() {
        String result = "";
        result += quantifier.getName() + " osob ";
        if (qualifiers.size() > 0) {
            result += " majacych ";
            for (int i = 0; i < qualifiers.size(); i++) {
                result += qualifiers.get(i).getLabel() + " " + qualifiers.get(i).getLinguisticVariableName();
                if (i < qualifiers.size() - 1) {
                    result += " i ";
                }
            }
        }

        result += " ma ";
        for (int i = 0; i < summarizers.size(); i++) {
            result += summarizers.get(i).getLabel() + " " + summarizers.get(i).getLinguisticVariableName();
            if (i < summarizers.size() - 1) {
                result += " i ";
            }
        }

        return result;
    }

    public String measuresToString() {
        String result = "";
        result += Math.round(goodness * 1000)/1000.0 + ", ";
        result += Math.round(T1 * 1000)/1000.0 + ", ";
        result += Math.round(T2 * 1000)/1000.0 + ", ";
        result += Math.round(T3 * 1000)/1000.0 + ", ";
        result += Math.round(T4 * 1000)/1000.0 + ", ";
        result += Math.round(T5 * 1000)/1000.0 + ", ";
        result += Math.round(T6 * 1000)/1000.0 + ", ";
        if (T7 < 0) {
            T7 = -T7;
        }
        result += Math.round(T7 * 1000)/1000.0 + ", ";
        result += Math.round(T8 * 1000)/1000.0 + ", ";
        result += Math.round(T9 * 1000)/1000.0 + ", ";
        result += Math.round(T10 * 1000)/1000.0 + ", ";
        result += Math.round(T11 * 1000)/1000.0;

        return result;
    }

}
