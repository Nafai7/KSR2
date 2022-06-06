package com.ksr.ksr2;

import com.ksr.ksr2.fuzzylogic.Label;
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
        List<BodySignals> test = BodySignalsReader.read("smoking");
//        System.out.print(test.get(0).getGender() + " " + test.get(0).getAge() + " " + test.get(0).getSystolic());
//        System.out.print("\n" + test.size());
//        launch();
//        SinusoidalFunction(double leftMinimum, double rightMinimum, int piValue, double leftMaximum, double rightMaximum) {
//        MembershipFunction f;
// D       f = new SinusoidalFunction(100, 56 * Math.PI, 100, 46 * Math.PI, 19);
// D       f = new TriangularFunction(10, 37, 21);
// D       f = new TrapezoidalFunction(17, 25, 6, 45);
// D       f = new QuadraticFunction(3, 15, 3, 5, true);
// D       f = new SinusoidalFunction((6-Math.PI)/12, (6+Math.PI)/12, 17.0/24.0, 24);
//        System.out.println("Membership: " + f.getMembership(30) + " " + f.getMembership(40));
//        System.out.println("Cardinality: " + f.getCardinality());
//        System.out.println("Support: " + f.getSupport());
        InitialData initialData = new InitialData();
        Summary summary;
        List<Quantifier> quantifiersArr = new ArrayList<>();
        quantifiersArr.add(initialData.quantifiers.get(1));
        quantifiersArr.add(initialData.quantifiers.get(2));
        quantifiersArr.add(initialData.quantifiers.get(7));
        List<Label> qualifiersArr = new ArrayList<>();
        qualifiersArr.add(initialData.labels.get(0));
        qualifiersArr.add(initialData.labels.get(4));
        List<Label> summarizersArr = new ArrayList<>();
        summarizersArr.add(initialData.labels.get(31));
        summarizersArr.add(initialData.labels.get(33));
        List<Label> tmp1;
        List<Label> tmp2;
        int i = 0;
        for (Quantifier q : quantifiersArr) {
            for (Label ql : qualifiersArr) {
                for (Label s : summarizersArr) {
                    tmp1 = new ArrayList<>();
                    tmp1.add(ql);
                    tmp2 = new ArrayList<>();
                    tmp2.add(s);
                    tmp2.add(initialData.labels.get(15));
                    summary = new Summary(q, tmp1, tmp2, test, initialData.weights);
                    summary.calcMeasures();
                    i ++;
                    System.out.println("P" + i + " " + summary.toString() + "\n    " + summary.measuresToString());
                }
            }
        }
    }
}