package com.ksr.ksr2;

import com.ksr.ksr2.fuzzylogic.Label;
import com.ksr.ksr2.fuzzylogic.MultiSubjectSummary;
import com.ksr.ksr2.fuzzylogic.Quantifier;
import com.ksr.ksr2.fuzzylogic.Summary;
import com.ksr.ksr2.fuzzylogic.functions.*;
import com.ksr.ksr2.model.BodySignals;
import com.ksr.ksr2.utilis.BodySignalsReader;
import com.ksr.ksr2.utilis.InitialData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();
        List<BodySignals> test = BodySignalsReader.read("smoking");
        InitialData initialData = new InitialData();
//        Summary summary;
//        List<Quantifier> quantifiersArr = new ArrayList<>();
//        quantifiersArr.add(initialData.quantifiers.get(1));
//        quantifiersArr.add(initialData.quantifiers.get(2));
//        quantifiersArr.add(initialData.quantifiers.get(7));
//        List<Label> qualifiersArr = new ArrayList<>();
//        qualifiersArr.add(initialData.labels.get(0));
//        qualifiersArr.add(initialData.labels.get(4));
//        List<Label> summarizersArr = new ArrayList<>();
//        summarizersArr.add(initialData.labels.get(31));
//        summarizersArr.add(initialData.labels.get(33));
//        List<Label> tmp1;
//        List<Label> tmp2;
//        int i = 0;
//        for (Quantifier q : quantifiersArr) {
//            for (Label ql : qualifiersArr) {
//                for (Label s : summarizersArr) {
//                    tmp1 = new ArrayList<>();
//                    tmp1.add(ql);
//                    tmp2 = new ArrayList<>();
//                    tmp2.add(s);
//                    tmp2.add(initialData.labels.get(15));
//                    summary = new Summary(q, tmp1, tmp2, test, initialData.weights);
//                    summary.calcMeasures();
//                    i ++;
//                    System.out.println("P" + i + " " + summary.toString() + "\n    " + summary.measuresToString());
//                }
//            }
//        }

        List<BodySignals> smokers = new ArrayList<>();
        List<BodySignals> nonSmokers = new ArrayList<>();
        for (BodySignals bodySignal: test) {
            if (bodySignal.isSmoking()) {
                smokers.add(bodySignal);
            } else {
                nonSmokers.add(bodySignal);
            }
        }

        // FORMA 1
        MultiSubjectSummary form1 = new MultiSubjectSummary(initialData.quantifiers.get(2), initialData.labels.subList(10,11), smokers, nonSmokers, "palacze", "niepalacy");
        form1.calcT();
        System.out.print(form1.toString());
        System.out.print("\n");

        // FORMA 2
        MultiSubjectSummary form2 = new MultiSubjectSummary(initialData.quantifiers.get(2), initialData.labels.subList(10,11), smokers, nonSmokers, initialData.labels.subList(20, 21), false, "palacze", "niepalacy");
        form2.calcT();
        System.out.print(form2.toString());
        System.out.print("\n");

        // FORMA 3
        MultiSubjectSummary form3 = new MultiSubjectSummary(initialData.quantifiers.get(2), initialData.labels.subList(10,11), smokers, nonSmokers, initialData.labels.subList(20, 21), true, "palacze", "niepalacy");
        form3.calcT();
        System.out.print(form3.toString());
        System.out.print("\n");

        // FORMA 4
        MultiSubjectSummary form4 = new MultiSubjectSummary(initialData.labels.subList(10,11), smokers, nonSmokers, "palacze", "niepalacy");
        form4.calcT();
        System.out.print(form4.toString());
        System.out.print("\n");
    }
}