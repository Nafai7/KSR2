package com.ksr.ksr2.fuzzylogic;

public class Label {
    protected String linguisticVariableName;
    protected String label;
    protected FuzzySet fuzzySet;

    public Label(String linguisticVariableName, String label, FuzzySet fuzzySet) {
        this.linguisticVariableName = linguisticVariableName;
        this.label = label;
        this.fuzzySet = fuzzySet;
    }

    public String getLinguisticVariableName() {
        return linguisticVariableName;
    }

    public String getLabel() {
        return label;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }
}
