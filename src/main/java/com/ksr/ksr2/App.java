package com.ksr.ksr2;

import com.ksr.ksr2.fuzzylogic.functions.*;
import com.ksr.ksr2.model.BodySignals;
import com.ksr.ksr2.utilis.BodySignalsReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
//        List<BodySignals> test = BodySignalsReader.read("smoking");
//        System.out.print(test.get(0).getGender() + " " + test.get(0).getAge() + " " + test.get(0).getSystolic());
//        System.out.print("\n" + test.size());
//        launch();
//        SinusoidalFunction(double leftMinimum, double rightMinimum, int piValue, double leftMaximum, double rightMaximum) {
//        MembershipFunction f;
//// N       f = new SinusoidalFunction(100, 56 * Math.PI, 19, 100, 46 * Math.PI);
//// D       f = new TriangularFunction(10, 37, 21);
//// Dn       f = new TrapezoidalFunction(150, 220, 150, 190);
//// D       f = new QuadraticFunction(3, 15, 3, 5, true);
//        System.out.println("Membership: " + f.getMembership(4) + " " + f.getMembership(10.5));
//        System.out.println("Cardinality: " + f.getCardinality());
//        System.out.println("Support: " + f.getSupport());
    }
}