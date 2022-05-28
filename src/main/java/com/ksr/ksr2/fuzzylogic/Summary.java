package com.ksr.ksr2.fuzzylogic;

import java.util.List;

public class Summary<T> {
    protected Quantifier quantifier;
    protected Label qualifier;
    protected List<Label> summarizers;
    protected List<T> objects;

    Summary(Quantifier quantifier, Label qualifier, List<Label> summarizers, List<T> objects) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizers = summarizers;
        this.objects = objects;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public Label getQualifier() {
        return qualifier;
    }

    public List<Label> getSummarizers() {
        return summarizers;
    }


}
