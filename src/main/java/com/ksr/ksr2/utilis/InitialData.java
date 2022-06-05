package com.ksr.ksr2.utilis;

import com.ksr.ksr2.fuzzylogic.*;
import com.ksr.ksr2.fuzzylogic.functions.QuadraticFunction;
import com.ksr.ksr2.fuzzylogic.functions.SinusoidalFunction;
import com.ksr.ksr2.fuzzylogic.functions.TrapezoidalFunction;
import com.ksr.ksr2.fuzzylogic.functions.TriangularFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitialData {
    public List<Label> labels = new ArrayList<>();
    public List<LinguisticVariable> linguisticVariables = new ArrayList<>();
    public List<Quantifier> quantifiers = new ArrayList<>();
    public Weights weights;

    public InitialData() {
        // Labels
        labels.add(new Label("cisnienie skurczowe krwi", "hipotonia", new FuzzySet(new ClassicSet(60, 109), new TrapezoidalFunction(60, 109, 60, 99))));
        labels.add(new Label("cisnienie skurczowe krwi", "niskie prawidlowe", new FuzzySet(new ClassicSet(90, 129), new TrapezoidalFunction(90, 129, 100, 119))));
        labels.add(new Label("cisnienie skurczowe krwi", "prawidlowe", new FuzzySet(new ClassicSet(110, 134), new TrapezoidalFunction(110, 134, 120, 129))));
        labels.add(new Label("cisnienie skurczowe krwi", "wysokie prawidlowe", new FuzzySet(new ClassicSet(125, 144), new TrapezoidalFunction(125, 144, 130, 139))));
        labels.add(new Label("cisnienie skurczowe krwi", "nadcisnienie tetnicze 1. stopnia", new FuzzySet(new ClassicSet(135, 169), new TrapezoidalFunction(135, 169, 140, 159))));
        labels.add(new Label("cisnienie skurczowe krwi", "nadcisnienie tetnicze 2. stopnia", new FuzzySet(new ClassicSet(150, 189), new TrapezoidalFunction(150, 189, 160, 179))));
        labels.add(new Label("cisnienie skurczowe krwi", "nadcisnienie tetnicze 3. stopnia", new FuzzySet(new ClassicSet(170, 240), new TrapezoidalFunction(170, 240, 180, 240))));

        labels.add(new Label("cisnienie rozkurczowe krwi", "hipotonia", new FuzzySet(new ClassicSet(40, 69), new TrapezoidalFunction(40, 69, 40, 59))));
        labels.add(new Label("cisnienie rozkurczowe krwi", "niskie prawidlowe", new FuzzySet(new ClassicSet(50, 83), new TrapezoidalFunction(50, 83, 60, 79))));
        labels.add(new Label("cisnienie rozkurczowe krwi", "prawidlowe", new FuzzySet(new ClassicSet(77, 87), new TrapezoidalFunction(77, 87, 80, 84))));
        labels.add(new Label("cisnienie rozkurczowe krwi", "wysokie prawidlowe", new FuzzySet(new ClassicSet(82, 94), new TrapezoidalFunction(82, 94, 85, 89))));
        labels.add(new Label("cisnienie rozkurczowe krwi", "nadcisnienie tetnicze 1. stopnia", new FuzzySet(new ClassicSet(85, 104), new TrapezoidalFunction(85, 104, 90, 99))));
        labels.add(new Label("cisnienie rozkurczowe krwi", "nadcisnienie tetnicze 2. stopnia", new FuzzySet(new ClassicSet(95, 119), new TrapezoidalFunction(95, 119, 100, 109))));
        labels.add(new Label("cisnienie rozkurczowe krwi", "nadcisnienie tetnicze 3. stopnia", new FuzzySet(new ClassicSet(100, 150), new TrapezoidalFunction(100, 150, 110, 150))));

        labels.add(new Label("cukier we krwi na czczo", "hipoglikemia", new FuzzySet(new ClassicSet(40, 80), new TrapezoidalFunction(40, 80, 40, 55))));
        labels.add(new Label("cukier we krwi na czczo", "prawidlowa glikemia", new FuzzySet(new ClassicSet(60, 110), new QuadraticFunction(60, 110, 80, 90, false))));
        labels.add(new Label("cukier we krwi na czczo", "nieprawidlowa glikemia", new FuzzySet(new ClassicSet(90, 130), new TrapezoidalFunction(90, 130, 120, 130))));
        labels.add(new Label("cukier we krwi na czczo", "hiperglikemia", new FuzzySet(new ClassicSet(112, 160), new TrapezoidalFunction(112, 160, 130, 160))));

        labels.add(new Label("poziom cholesterolu", "normalny poziom", new FuzzySet(new ClassicSet(150, 220), new TrapezoidalFunction(150, 220, 150, 190))));
        labels.add(new Label("poziom cholesterolu", "podniesiony poziom", new FuzzySet(new ClassicSet(200, 255), new TrapezoidalFunction(200, 255, 210, 255))));
        labels.add(new Label("poziom cholesterolu", "znacznie podniesiony poziom", new FuzzySet(new ClassicSet(235, 300), new TrapezoidalFunction(235, 300, 260, 300))));

//        labels.add(new Label("poziom triglycerydow", "wlasciwy poziom", new FuzzySet(new ClassicSet(), new SinusoidalFunction())));
        labels.add(new Label("poziom triglycerydow", "podniesiony poziom", new FuzzySet(new ClassicSet(160, 200), new TrapezoidalFunction(160, 200, 180, 200))));

        labels.add(new Label("poziom cholesterolu HDL", "zanizony poziom", new FuzzySet(new ClassicSet(0, 30), new TrapezoidalFunction(0, 30, 0, 20))));
//        labels.add(new Label("poziom cholesterolu HDL", "wlasciwy poziom", new FuzzySet(new ClassicSet(), new SinusoidalFunction())));

        labels.add(new Label("poziom cholesterolu LDL", "normalny poziom", new FuzzySet(new ClassicSet(100, 140), new TrapezoidalFunction(100, 140, 100, 130))));
        labels.add(new Label("poziom cholesterolu LDL", "podniesiony poziom", new FuzzySet(new ClassicSet(135, 155), new TrapezoidalFunction(135, 155, 140, 155))));
        labels.add(new Label("poziom cholesterolu LDL", "znacznie podniesiony poziom", new FuzzySet(new ClassicSet(145, 200), new TrapezoidalFunction(145, 200, 160, 200))));

        labels.add(new Label("poziom hemoglobiny", "ponizej normy", new FuzzySet(new ClassicSet(4, 15), new QuadraticFunction(4, 15, 4, 5, true))));
        labels.add(new Label("poziom hemoglobiny", "norma", new FuzzySet(new ClassicSet(10, 20), new QuadraticFunction(10, 20, 14, 16, false))));
        labels.add(new Label("poziom hemoglobiny", "powyzej normy", new FuzzySet(new ClassicSet(15, 26), new QuadraticFunction(15, 26, 25, 26, false))));

        labels.add(new Label("poziom AST", "norma", new FuzzySet(new ClassicSet(10, 37), new TriangularFunction(10, 37, 21))));
//        labels.add(new Label("poziom AST", "nietypowy", new FuzzySet(new ClassicSet(), new TrapezoidalFunction())));

        labels.add(new Label("poziom ALT", "norma", new FuzzySet(new ClassicSet(15, 35), new TriangularFunction(15, 35, 26))));
        labels.add(new Label("poziom ALT", "nieprawidlowy", new FuzzySet(new ClassicSet(28, 45), new TrapezoidalFunction(28, 45, 40, 45))));


        // Linguistic Variables
        linguisticVariables.add(new LinguisticVariable("cisnienie skurczowe krwi", labels.subList(0, 7)));
        linguisticVariables.add(new LinguisticVariable("cisnienie rozkurczowe krwi", labels.subList(7, 14)));
        linguisticVariables.add(new LinguisticVariable("cukier we krwi na czczo", labels.subList(14, 18)));
        linguisticVariables.add(new LinguisticVariable("poziom cholesterolu", labels.subList(18, 21)));
        linguisticVariables.add(new LinguisticVariable("poziom triglycerydow", labels.subList(21, 23)));
        linguisticVariables.add(new LinguisticVariable("poziom cholesterolu HDL", labels.subList(23, 25)));
        linguisticVariables.add(new LinguisticVariable("poziom cholesterolu LDL", labels.subList(25, 28)));
        linguisticVariables.add(new LinguisticVariable("poziom hemoglobiny", labels.subList(28, 31)));
        linguisticVariables.add(new LinguisticVariable("poziom AST", labels.subList(31, 33)));
        linguisticVariables.add(new LinguisticVariable("poziom ALT", labels.subList(33, 35)));

        // Quantifiers
//        quantifiers.add(new Quantifier("brak", false, new FuzzySet(new ClassicSet(), new SinusoidalFunction())));
//        quantifiers.add(new Quantifier("marginalnie", false, new FuzzySet(new ClassicSet(), new SinusoidalFunction())));
//        quantifiers.add(new Quantifier("umiarkowanie", false, new FuzzySet(new ClassicSet(), new SinusoidalFunction())));
//        quantifiers.add(new Quantifier("duzo", false, new FuzzySet(new ClassicSet(), new SinusoidalFunction())));
//        quantifiers.add(new Quantifier("calkowicie", false, new FuzzySet(new ClassicSet(), new SinusoidalFunction())));

        quantifiers.add(new Quantifier("ponizej 10 tys", true, new FuzzySet(new ClassicSet(0, 12000), new TrapezoidalFunction(0, 12000, 0, 10000))));
        quantifiers.add(new Quantifier("niemalze 12.3 tys", true, new FuzzySet(new ClassicSet(10000, 15000), new TriangularFunction(10000, 15000, 12300))));
        quantifiers.add(new Quantifier("powyzej 15 tys", true, new FuzzySet(new ClassicSet(13000, 56000), new TrapezoidalFunction(13000, 56000, 15000, 56000))));

        // Weights
        List<Double> tmp = new ArrayList<>();
        tmp.add(0.5);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        tmp.add(0.05);
        weights = new Weights(tmp);
    }
}
