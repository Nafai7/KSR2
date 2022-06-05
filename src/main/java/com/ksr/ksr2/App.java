package com.ksr.ksr2;

import com.ksr.ksr2.fuzzylogic.Label;
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
        Summary summary = new Summary(initialData.quantifiers.get(0), new ArrayList<Label>(), initialData.labels.subList(2,4), test, initialData.weights);
        summary.calcMeasures();
        System.out.print(summary.toString() + " " + summary.measuresToString());
        System.out.print("\n");
        Summary summary2 = new Summary(initialData.quantifiers.get(2), new ArrayList<Label>(), initialData.labels.subList(2,3), test, initialData.weights);
        summary2.calcMeasures();
        System.out.print(summary2.toString() + " " + summary2.measuresToString());
    }
}