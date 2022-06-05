package com.ksr.ksr2.fuzzylogic;

public class Quantifier {
    protected String name;
    protected boolean absolute;
    protected FuzzySet fuzzySet;

    public Quantifier(String name, boolean absolute, FuzzySet fuzzySet) {
        this.name = name;
        this.absolute = absolute;
        this.fuzzySet = fuzzySet;
    }

    public String getName() {
        return name;
    }

    public FuzzySet getSet() {
        return fuzzySet;
    }

    public boolean isAbsolute() {
        return absolute;
    }
}
