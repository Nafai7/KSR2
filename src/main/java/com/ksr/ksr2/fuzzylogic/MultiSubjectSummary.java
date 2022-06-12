package com.ksr.ksr2.fuzzylogic;

import com.ksr.ksr2.model.BodySignals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class MultiSubjectSummary {
    private Quantifier quantifier;
    private List<Label> qualifiers1;
    private List<Label> qualifiers2;
    private List<Label> summarizers;
    private List<BodySignals> bodySignals1;
    private List<BodySignals> bodySignals2;

    private double T;

    private short form;

    private String subject1;
    private String subject2;

    public MultiSubjectSummary(Quantifier quantifier, List<Label> summarizers, List<BodySignals> bodySignals1, List<BodySignals> bodySignals2, String subject1, String subject2) {
        form = 1;
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.bodySignals1 = bodySignals1;
        this.bodySignals2 = bodySignals2;
        this.subject1 = subject1;
        this.subject2 = subject2;
    }

    public MultiSubjectSummary(Quantifier quantifier, List<Label> summarizers, List<BodySignals> bodySignals1, List<BodySignals> bodySignals2, List<Label> qualifiers, boolean qualifiersToSubject1, String subject1, String subject2) {
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.bodySignals1 = bodySignals1;
        this.bodySignals2 = bodySignals2;
        if (qualifiersToSubject1) {
            this.qualifiers1 = qualifiers;
            form = 3;
        } else {
            this.qualifiers2 = qualifiers;
            form = 2;
        }

        this.subject1 = subject1;
        this.subject2 = subject2;
    }

    public MultiSubjectSummary(List<Label> summarizers, List<BodySignals> bodySignals1, List<BodySignals> bodySignals2, String subject1, String subject2) {
        form = 4;
        this.summarizers = summarizers;
        this.bodySignals1 = bodySignals1;
        this.bodySignals2 = bodySignals2;

        this.subject1 = subject1;
        this.subject2 = subject2;
    }

    public double getT() {
        return T;
    }

    private double sigmaCount(List<BodySignals> bodySignals, List<Label> labels) {
        double sum = 0;
        List<Double> summarizerMembership;
        for (BodySignals bodySignal: bodySignals) {
            summarizerMembership = new ArrayList<>();
            for (Label label: labels) {
                summarizerMembership.add(label.getFuzzySet().getMembershipFunction().getMembership(bodySignal.get(label.getLinguisticVariableName())));
            }

            sum += Collections.min(summarizerMembership);
        }

        return sum;
    }

    private double implication(double a, double b) {
        return Math.min(1, 1 - a + b);
    }

    public void calcT() {
        double sigma1 = sigmaCount(bodySignals1, summarizers);
        double sigma2 = sigmaCount(bodySignals2, summarizers);


        switch (form) {
            case 1:
                T = quantifier.getSet().getMembershipFunction().getMembership((sigma1 / bodySignals1.size()) / ((sigma1 / bodySignals1.size()) + (sigma2 / bodySignals2.size())));
                break;
            case 2:
                sigma2 = sigmaCount(bodySignals2, Stream.concat(qualifiers2.stream(), summarizers.stream()).toList());
                T = quantifier.getSet().getMembershipFunction().getMembership((sigma1 / bodySignals1.size()) / ((sigma1 / bodySignals1.size()) + (sigma2 / bodySignals2.size())));
                break;
            case 3:
                sigma1 = sigmaCount(bodySignals1, Stream.concat(qualifiers1.stream(), summarizers.stream()).toList());
                T = quantifier.getSet().getMembershipFunction().getMembership((sigma1 / bodySignals1.size()) / ((sigma1 / bodySignals1.size()) + (sigma2 / bodySignals2.size())));
                break;
            case 4:
                List<BodySignals> everyone = Stream.concat(bodySignals1.stream(), bodySignals2.stream()).toList();
                List<Double> summarizerMembership;
                double sum = 0;

                for (BodySignals bodySignal: everyone) {
                    summarizerMembership = new ArrayList<>();

                    for (Label label: summarizers) {
                        summarizerMembership.add(label.getFuzzySet().getMembershipFunction().getMembership(bodySignal.get(label.getLinguisticVariableName())));
                    }

                    if (bodySignals1.contains(bodySignal)) {
                        sum += implication(0, Collections.min(summarizerMembership));
                    }
                    if (bodySignals2.contains(bodySignal)) {
                        sum += implication(Collections.min(summarizerMembership), 0);
                    }
                }
                T = 1 - (sum / everyone.size());
                break;
        }
    }

    public String toString() {
        String result = "";

        if (form != 4) {
            result += quantifier.getName() + " ";
        } else {
            result += "Wiecej ";
        }
        result += subject1;

        if (form == 3) {
            result += " majacych ";
            for (int i = 0; i < qualifiers1.size(); i++) {
                result += qualifiers1.get(i).getLabel() + " " + qualifiers1.get(i).getLinguisticVariableName();
                if (i < qualifiers1.size() - 1) {
                    result += " i ";
                }
            }
        }

        result += " w porównaniu do " + subject2;

        if (form == 2) {
            result += " majacych ";
            for (int i = 0; i < qualifiers2.size(); i++) {
                result += qualifiers2.get(i).getLabel() + " " + qualifiers2.get(i).getLinguisticVariableName();
                if (i < qualifiers2.size() - 1) {
                    result += " i ";
                }
            }
        }

        result += " jest/ma ";

        for (int i = 0; i < summarizers.size(); i++) {
            result += summarizers.get(i).getLabel() + " " + summarizers.get(i).getLinguisticVariableName();
            if (i < summarizers.size() - 1) {
                result += " i ";
            }
        }

        result += " [" + (Math.round(T * 1000.0) / 1000.0) + "]";

        return result;
    }
}
