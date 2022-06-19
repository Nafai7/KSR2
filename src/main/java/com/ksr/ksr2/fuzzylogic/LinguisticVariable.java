package com.ksr.ksr2.fuzzylogic;

import java.util.List;

public class LinguisticVariable {
    protected String name;
    protected List<Label> labels;

    public LinguisticVariable(String name, List<Label> labels) {
        this.name = name;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void addLabel(Label label) {
        labels.add(label);
    }
}
