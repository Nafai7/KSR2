package com.ksr.ksr2.fuzzylogic;

public class Quantifier {
    protected String name;
    protected boolean absolute;
    protected Label label;

    Quantifier(String name, boolean absolute, Label label) {
        this.name = name;
        this.absolute = absolute;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }

    public boolean isAbsolute() {
        return absolute;
    }
}
