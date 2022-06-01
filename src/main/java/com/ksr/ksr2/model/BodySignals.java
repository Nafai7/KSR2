package com.ksr.ksr2.model;

public class BodySignals {
    private int ID;
    private String gender;
    private int age;
    private double systolic;
    private double relaxation;
    private double fastingBloodSugar;
    private double cholesterol;
    private double triglyceride;
    private double HDL;
    private double LDL;
    private double hemoglobin;
    private double AST;
    private double ALT;
    private boolean smoking;

    public BodySignals(int ID, String gender, int age, double systolic, double relaxation, double fastingBloodSugar, double cholesterol, double triglyceride, double HDL, double LDL, double hemoglobin, double AST, double ALT, boolean smoking) {
        this.ID = ID;
        this.gender = gender;
        this.age = age;
        this.systolic = systolic;
        this.relaxation = relaxation;
        this.fastingBloodSugar = fastingBloodSugar;
        this.cholesterol = cholesterol;
        this.triglyceride = triglyceride;
        this.HDL = HDL;
        this.LDL = LDL;
        this.hemoglobin = hemoglobin;
        this.AST = AST;
        this.ALT = ALT;
        this.smoking = smoking;
    }

    public int getID() {
        return ID;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getSystolic() {
        return systolic;
    }

    public double getRelaxation() {
        return relaxation;
    }

    public double getFastingBloodSugar() {
        return fastingBloodSugar;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public double getTriglyceride() {
        return triglyceride;
    }

    public double getHDL() {
        return HDL;
    }

    public double getLDL() {
        return LDL;
    }

    public double getHemoglobin() {
        return hemoglobin;
    }

    public double getAST() {
        return AST;
    }

    public double getALT() {
        return ALT;
    }

    public boolean isSmoking() {
        return smoking;
    }
}
