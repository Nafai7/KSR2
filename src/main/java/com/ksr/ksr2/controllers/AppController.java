package com.ksr.ksr2.controllers;

import com.ksr.ksr2.App;
import com.ksr.ksr2.fuzzylogic.*;
import com.ksr.ksr2.fuzzylogic.functions.GaussFunction;
import com.ksr.ksr2.fuzzylogic.functions.MembershipFunction;
import com.ksr.ksr2.fuzzylogic.functions.TrapezoidalFunction;
import com.ksr.ksr2.fuzzylogic.functions.TriangularFunction;
import com.ksr.ksr2.model.BodySignals;
import com.ksr.ksr2.utilis.InitialData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private InitialData initialData;

    private List<BodySignals> people;

    private List<BodySignals> smokers;
    private List<BodySignals> nonsmokers;
    private List<BodySignals> men;
    private List<BodySignals> women;
    private List<BodySignals> nonsmokerMen;
    private List<BodySignals> nonsmokerWomen;
    private List<BodySignals> smokerMen;
    private List<BodySignals> smokerWomen;

    private Quantifier chosenQuantifier;
    private List<com.ksr.ksr2.fuzzylogic.Label> chosenQualifiers;
    private List<com.ksr.ksr2.fuzzylogic.Label> chosenSummarizers;

    @FXML
    private Label summaryLabel;
    private Summary summary;

    @FXML
    private ChoiceBox quantifierChoiceBox;

    @FXML
    private TreeView qualifierTreeView;
    @FXML
    private TreeView summarizerTreeView;

    @FXML
    private Label labelT0;
    @FXML
    private Label labelT1;
    @FXML
    private Label labelT2;
    @FXML
    private Label labelT3;
    @FXML
    private Label labelT4;
    @FXML
    private Label labelT5;
    @FXML
    private Label labelT6;
    @FXML
    private Label labelT7;
    @FXML
    private Label labelT8;
    @FXML
    private Label labelT9;
    @FXML
    private Label labelT10;
    @FXML
    private Label labelT11;

    private Quantifier chosenQuantifierMulti;
    private List<com.ksr.ksr2.fuzzylogic.Label> chosenQualifiersMulti;
    private List<com.ksr.ksr2.fuzzylogic.Label> chosenSummarizersMulti;
    private List<BodySignals> chosenSubject1;
    private List<BodySignals> chosenSubject2;
    private String subject1;
    private String subject2;

    @FXML
    private Label multiSummaryLabel;
    private MultiSubjectSummary multiSubjectSummary;

    @FXML
    private ChoiceBox subject1ChoiceBox;
    @FXML
    private ChoiceBox subject2ChoiceBox;

    @FXML
    private ChoiceBox multiQuantifierChoiceBox;

    @FXML
    private TreeView multiQualifierTreeView;
    @FXML
    private CheckBox subject1CheckBox;
    @FXML
    private CheckBox subject2CheckBox;

    @FXML
    private TreeView multiSummarizerTreeView;

    @FXML
    private Label labelT;

    @FXML
    private TextField labelName;
    @FXML
    private ChoiceBox labelVariable;
    @FXML
    private ChoiceBox labelFunction;
    @FXML
    private TextField labelA;
    @FXML
    private TextField labelB;
    @FXML
    private TextField labelC;
    @FXML
    private TextField labelD;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;

    public void initialize() {
        initialData = App.initialData;
        people = App.getPeople();

        chosenQualifiers = new ArrayList<>();
        chosenSummarizers = new ArrayList<>();

        chosenQualifiersMulti = new ArrayList<>();
        chosenSummarizersMulti = new ArrayList<>();
        chosenSubject1 = new ArrayList<>();
        chosenSubject2 = new ArrayList<>();

        smokers = new ArrayList<>();
        nonsmokers = new ArrayList<>();
        men = new ArrayList<>();
        women = new ArrayList<>();
        nonsmokerMen = new ArrayList<>();
        nonsmokerWomen = new ArrayList<>();
        smokerMen = new ArrayList<>();
        smokerWomen = new ArrayList<>();

        for (BodySignals person: people) {
            if (person.isSmoking()) {
                smokers.add(person);
            } else {
                nonsmokers.add(person);

            }

            if (person.getGender().equals("F")) {
                women.add(person);
                if (smokers.contains(person)) {
                    smokerWomen.add(person);
                } else {
                    nonsmokerWomen.add(person);
                }
            } else {
                men.add(person);
                if (smokers.contains(person)) {
                    smokerMen.add(person);
                } else {
                    nonsmokerMen.add(person);
                }
            }
        }


        for(Quantifier quantifier: initialData.quantifiers) {
            quantifierChoiceBox.getItems().add(quantifier.getName());
        }
        quantifierChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                quantifierChange((Integer) t1);
            }
        });

        qualifierTreeView.setRoot(new TreeItem("Bajo jajo"));
        qualifierTreeView.setShowRoot(false);
        summarizerTreeView.setRoot(new TreeItem("Bajo jajo"));
        summarizerTreeView.setShowRoot(false);
        for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
            TreeItem treeItem = new TreeItem(linguisticVariable.getName());
            TreeItem treeItem2 = new TreeItem(linguisticVariable.getName());
            for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                treeItem.getChildren().add(new TreeItem(label.getLabel()));
                treeItem2.getChildren().add(new TreeItem(label.getLabel()));
            }
            qualifierTreeView.getRoot().getChildren().add(treeItem);
            summarizerTreeView.getRoot().getChildren().add(treeItem2);

        }
        qualifierTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        qualifierTreeView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                qualifierChange();
            }
        });
        summarizerTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        summarizerTreeView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                summarizerChange();
            }
        });


        subject1ChoiceBox.getItems().add("palacze");
        subject1ChoiceBox.getItems().add("niepalacy");
        subject1ChoiceBox.getItems().add("mezczyzni");
        subject1ChoiceBox.getItems().add("kobiety");
        subject1ChoiceBox.getItems().add("niepalacy mezczyzni");
        subject1ChoiceBox.getItems().add("niepalace kobiety");
        subject1ChoiceBox.getItems().add("palacy mezczyzni");
        subject1ChoiceBox.getItems().add("palace kobiety");
        subject1ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                subject1Change((Integer) t1);
            }
        });

        subject2ChoiceBox.getItems().add("palacze");
        subject2ChoiceBox.getItems().add("niepalacy");
        subject2ChoiceBox.getItems().add("mezczyzni");
        subject2ChoiceBox.getItems().add("kobiety");
        subject2ChoiceBox.getItems().add("niepalacy mezczyzni");
        subject2ChoiceBox.getItems().add("niepalace kobiety");
        subject2ChoiceBox.getItems().add("palacy mezczyzni");
        subject2ChoiceBox.getItems().add("palace kobiety");
        subject2ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                subject2Change((Integer) t1);
            }
        });

        multiQuantifierChoiceBox.getItems().add("");
        multiQuantifierChoiceBox.getSelectionModel().selectFirst();
        for(Quantifier quantifier: initialData.quantifiers) {
            multiQuantifierChoiceBox.getItems().add(quantifier.getName());
        }
        multiQuantifierChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                multiQuantifierChange((Integer) t1);
            }
        });

        multiQualifierTreeView.setRoot(new TreeItem("Bajo jajo"));
        multiQualifierTreeView.setShowRoot(false);
        multiSummarizerTreeView.setRoot(new TreeItem("Bajo jajo"));
        multiSummarizerTreeView.setShowRoot(false);
        for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
            TreeItem treeItem = new TreeItem(linguisticVariable.getName());
            TreeItem treeItem2 = new TreeItem(linguisticVariable.getName());
            for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                treeItem.getChildren().add(new TreeItem(label.getLabel()));
                treeItem2.getChildren().add(new TreeItem(label.getLabel()));
            }
            multiQualifierTreeView.getRoot().getChildren().add(treeItem);
            multiSummarizerTreeView.getRoot().getChildren().add(treeItem2);

        }
        multiQualifierTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        multiQualifierTreeView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                multiQualifierChange();
            }
        });
        multiSummarizerTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        multiSummarizerTreeView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                multiSummarizerChange();
            }
        });

        subject1CheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                checkBox1Changed(t1);
            }
        });

        subject2CheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                checkBox2Changed(t1);
            }
        });


        for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
            labelVariable.getItems().add(linguisticVariable.getName());
        }

        labelFunction.getItems().add("Trapezowa");
        labelFunction.getItems().add("Trojkatna");
        labelFunction.getItems().add("Gaussa");
        labelFunction.getSelectionModel().selectFirst();
        labelFunction.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                functionChanged((Integer) t1);
            }
        });
    }

    private void quantifierChange(Integer selected) {
        String selectedQuantifier = (String) quantifierChoiceBox.getItems().get(selected);
        for (Quantifier quantifier: initialData.quantifiers) {
            if (selectedQuantifier.equals(quantifier.getName())) {
                chosenQuantifier = quantifier;
            }
        }
        generateSummary();
    }

    private void qualifierChange() {
        List<Integer> selectedIDs= qualifierTreeView.getSelectionModel().getSelectedIndices().stream().toList();
        chosenQualifiers.clear();

        for (Integer id: selectedIDs) {
            TreeItem treeItem = qualifierTreeView.getTreeItem(id);
            if (qualifierTreeView.getTreeItemLevel(treeItem) == 2) {
                TreeItem parent = treeItem.getParent();
                for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
                    if (linguisticVariable.getName().equals(parent.getValue())) {
                        for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                            if (label.getLabel().equals(treeItem.getValue())) {
                                chosenQualifiers.add(label);
                            }
                        }
                    }
                }
            }
        }

        generateSummary();
    }

    private void summarizerChange() {
        List<Integer> selectedIDs= summarizerTreeView.getSelectionModel().getSelectedIndices().stream().toList();
        chosenSummarizers.clear();

        for (Integer id: selectedIDs) {
            TreeItem treeItem = summarizerTreeView.getTreeItem(id);
            if (summarizerTreeView.getTreeItemLevel(treeItem) == 2) {
                TreeItem parent = treeItem.getParent();
                for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
                    if (linguisticVariable.getName().equals(parent.getValue())) {
                        for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                            if (label.getLabel().equals(treeItem.getValue())) {
                                chosenSummarizers.add(label);
                            }
                        }
                    }
                }
            }
        }

        generateSummary();
    }

    private void generateSummary() {
        if ((chosenQuantifier != null) && (chosenSummarizers.size() > 0)) {
            summary = new Summary(chosenQuantifier, chosenQualifiers, chosenSummarizers, people, initialData.weights);
            summary.calcMeasures();
            summaryLabel.setText(summary.toString());

            labelT0.setText(String.valueOf(Math.round(summary.goodnessOfTheSummary() * 1000.0) / 1000.0));
            labelT1.setText(String.valueOf(Math.round(summary.degreeOfTruth() * 1000.0) / 1000.0));
            labelT2.setText(String.valueOf(Math.round(summary.degreeOfImprecision() * 1000.0) / 1000.0));
            labelT3.setText(String.valueOf(Math.round(summary.degreeOfCovering() * 1000.0) / 1000.0));
            labelT4.setText(String.valueOf(Math.round(summary.degreeOfAppropriateness() * 1000.0) / 1000.0));
            labelT5.setText(String.valueOf(Math.round(summary.lengthOfSummary() * 1000.0) / 1000.0));
            labelT6.setText(String.valueOf(Math.round(summary.degreeOfQuantifierImprecision() * 1000.0) / 1000.0));
            labelT7.setText(String.valueOf(Math.round(summary.degreeOfQuantifierCardinality() * 1000.0) / 1000.0));
            labelT8.setText(String.valueOf(Math.round(summary.degreeOfSummarizerCardinality() * 1000.0) / 1000.0));
            labelT9.setText(String.valueOf(Math.round(summary.degreeOfQualifierImprecision() * 1000.0) / 1000.0));
            labelT10.setText(String.valueOf(Math.round(summary.degreeOfQualifierCardinality() * 1000.0) / 1000.0));
            labelT11.setText(String.valueOf(Math.round(summary.lengthOfQualifier() * 1000.0) / 1000.0));
        }
    }

    private void subject1Change(Integer selected) {
        String selectedQuantifier = (String) subject1ChoiceBox.getItems().get(selected);
        subject1 = selectedQuantifier;
        switch (selectedQuantifier) {
            case "palacze":
                chosenSubject1 = smokers;
                break;
            case "niepalacy":
                chosenSubject1 = nonsmokers;
                break;
            case "mezczyzni":
                chosenSubject1 = men;
                break;
            case "kobiety":
                chosenSubject1 = women;
                break;
            case "niepalacy mezczyzni":
                chosenSubject1 = nonsmokerMen;
                break;
            case "niepalace kobiety":
                chosenSubject1 = nonsmokerWomen;
                break;
            case "palacy mezczyzni":
                chosenSubject1 = smokerMen;
                break;
            case "palace kobiety":
                chosenSubject1 = smokerWomen;
                break;
        }
        generateMultiSummary();
    }

    private void subject2Change(Integer selected) {
        String selectedQuantifier = (String) subject2ChoiceBox.getItems().get(selected);
        subject2 = selectedQuantifier;
        switch (selectedQuantifier) {
            case "palacze":
                chosenSubject2 = smokers;
                break;
            case "niepalacy":
                chosenSubject2 = nonsmokers;
                break;
            case "mezczyzni":
                chosenSubject2 = men;
                break;
            case "kobiety":
                chosenSubject2 = women;
                break;
            case "niepalacy mezczyzni":
                chosenSubject2 = nonsmokerMen;
                break;
            case "niepalace kobiety":
                chosenSubject2 = nonsmokerWomen;
                break;
            case "palacy mezczyzni":
                chosenSubject2 = smokerMen;
                break;
            case "palace kobiety":
                chosenSubject2 = smokerWomen;
                break;
        }
        generateMultiSummary();
    }

    private void multiQuantifierChange(Integer selected) {
        String selectedQuantifier = (String) multiQuantifierChoiceBox.getItems().get(selected);
        if (!selectedQuantifier.equals("")) {
            for (Quantifier quantifier: initialData.quantifiers) {
                if (selectedQuantifier.equals(quantifier.getName())) {
                    chosenQuantifierMulti = quantifier;
                }
            }
        } else {
            chosenQuantifierMulti = null;
        }
        generateMultiSummary();
    }

    private void multiQualifierChange() {
        List<Integer> selectedIDs= multiQualifierTreeView.getSelectionModel().getSelectedIndices().stream().toList();
        chosenQualifiersMulti.clear();

        for (Integer id: selectedIDs) {
            TreeItem treeItem = multiQualifierTreeView.getTreeItem(id);
            if (multiQualifierTreeView.getTreeItemLevel(treeItem) == 2) {
                TreeItem parent = treeItem.getParent();
                for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
                    if (linguisticVariable.getName().equals(parent.getValue())) {
                        for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                            if (label.getLabel().equals(treeItem.getValue())) {
                                chosenQualifiersMulti.add(label);
                            }
                        }
                    }
                }
            }
        }

        generateMultiSummary();
    }

    private void multiSummarizerChange() {
        List<Integer> selectedIDs= multiSummarizerTreeView.getSelectionModel().getSelectedIndices().stream().toList();
        chosenSummarizersMulti.clear();

        for (Integer id: selectedIDs) {
            TreeItem treeItem = multiSummarizerTreeView.getTreeItem(id);
            if (multiSummarizerTreeView.getTreeItemLevel(treeItem) == 2) {
                TreeItem parent = treeItem.getParent();
                for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
                    if (linguisticVariable.getName().equals(parent.getValue())) {
                        for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                            if (label.getLabel().equals(treeItem.getValue())) {
                                chosenSummarizersMulti.add(label);
                            }
                        }
                    }
                }
            }
        }

        generateMultiSummary();
    }

    private void checkBox1Changed(Boolean value) {
        if (value) {
            if (subject2CheckBox.isSelected()) {
                subject2CheckBox.setSelected(false);
            }
        }
        generateMultiSummary();
    }

    private void checkBox2Changed(Boolean value) {
        if (value) {
            if (subject1CheckBox.isSelected()) {
                subject1CheckBox.setSelected(false);
            }
        }
        generateMultiSummary();
    }

    private void functionChanged(Integer selected) {
        labelC.setDisable(false);
        labelD.setDisable(false);
        label1.setText("lewe minimum");
        label2.setText("lewe maksimum");
        label3.setText("prawe maksimum");
        label4.setText("prawe minimum");
        switch ((String)labelFunction.getItems().get(selected)) {
            case "Trojkatna":
                labelD.setDisable(true);
                label2.setText("maksimum");
                label3.setText("prawe minimum");
                label4.setText("");
                break;
            case "Gaussa":
                labelC.setDisable(true);
                labelD.setDisable(true);
                label1.setText("wariancja");
                label2.setText("maksimum");
                label3.setText("");
                label4.setText("");
                break;
        }
    }

    private void generateMultiSummary() {
        if ((chosenSubject1.size() > 0) && (chosenSubject2.size() > 0) && (chosenSummarizersMulti.size() > 0)) {
            if (chosenQuantifierMulti != null) {
                if (!subject1CheckBox.isSelected() && !subject2CheckBox.isSelected()) {
                    multiSubjectSummary = new MultiSubjectSummary(chosenQuantifierMulti, chosenSummarizersMulti, chosenSubject1, chosenSubject2, subject1, subject2);
                } else {
                    if (subject1CheckBox.isSelected()) {
                        multiSubjectSummary = new MultiSubjectSummary(chosenQuantifierMulti, chosenSummarizersMulti, chosenSubject1, chosenSubject2, chosenQualifiersMulti, true, subject1, subject2);
                    } else {
                        multiSubjectSummary = new MultiSubjectSummary(chosenQuantifierMulti, chosenSummarizersMulti, chosenSubject1, chosenSubject2, chosenQualifiersMulti, false, subject1, subject2);
                    }
                }
            } else {
                multiSubjectSummary = new MultiSubjectSummary(chosenSummarizersMulti, chosenSubject1, chosenSubject2, subject1, subject2);
            }
            multiSubjectSummary.calcT();
            multiSummaryLabel.setText(multiSubjectSummary.toString());
            labelT.setText(String.valueOf(multiSubjectSummary.getT()));
        }
    }

    private void rebuildLabels() {
        qualifierTreeView.setRoot(new TreeItem("Bajo jajo"));
        qualifierTreeView.setShowRoot(false);
        summarizerTreeView.setRoot(new TreeItem("Bajo jajo"));
        summarizerTreeView.setShowRoot(false);
        for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
            TreeItem treeItem = new TreeItem(linguisticVariable.getName());
            TreeItem treeItem2 = new TreeItem(linguisticVariable.getName());
            for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                treeItem.getChildren().add(new TreeItem(label.getLabel()));
                treeItem2.getChildren().add(new TreeItem(label.getLabel()));
            }
            qualifierTreeView.getRoot().getChildren().add(treeItem);
            summarizerTreeView.getRoot().getChildren().add(treeItem2);

        }

        multiQualifierTreeView.setRoot(new TreeItem("Bajo jajo"));
        multiQualifierTreeView.setShowRoot(false);
        multiSummarizerTreeView.setRoot(new TreeItem("Bajo jajo"));
        multiSummarizerTreeView.setShowRoot(false);
        for (LinguisticVariable linguisticVariable: initialData.linguisticVariables) {
            TreeItem treeItem = new TreeItem(linguisticVariable.getName());
            TreeItem treeItem2 = new TreeItem(linguisticVariable.getName());
            for (com.ksr.ksr2.fuzzylogic.Label label: linguisticVariable.getLabels()) {
                treeItem.getChildren().add(new TreeItem(label.getLabel()));
                treeItem2.getChildren().add(new TreeItem(label.getLabel()));
            }
            multiQualifierTreeView.getRoot().getChildren().add(treeItem);
            multiSummarizerTreeView.getRoot().getChildren().add(treeItem2);

        }
    }

    @FXML
    private void createLabel() {
        if (labelVariable.getValue() != null) {
            for (int i = 0; i < initialData.linguisticVariables.size(); i++) {
                if (initialData.linguisticVariables.get(i).getName().equals(labelVariable.getValue())) {
                    ClassicSet classicSet = initialData.linguisticVariables.get(i).getLabels().get(0).getFuzzySet().getUniverse();
                    MembershipFunction function;
                    switch ((String) labelFunction.getValue()) {
                        case "Trapezowa":
                            function = new TrapezoidalFunction(Integer.valueOf(labelA.getText()), Integer.valueOf(labelD.getText()), Integer.valueOf(labelB.getText()), Integer.valueOf(labelC.getText()));
                            break;
                        case "Trojkatna":
                            function = new TriangularFunction(Integer.valueOf(labelA.getText()), Integer.valueOf(labelC.getText()), Integer.valueOf(labelB.getText()));
                            break;
                        case "Gaussa":
                            function = new GaussFunction(Integer.valueOf(labelA.getText()), Integer.valueOf(labelB.getText()));
                            break;
                        default:
                            function = new TrapezoidalFunction(Integer.valueOf(labelA.getText()), Integer.valueOf(labelB.getText()), Integer.valueOf(labelC.getText()), Integer.valueOf(labelD.getText()));
                            break;
                    }
                    initialData.linguisticVariables.get(i).addLabel(new com.ksr.ksr2.fuzzylogic.Label(initialData.linguisticVariables.get(i).getName(), labelName.getText(), new FuzzySet(classicSet, function)));
                    rebuildLabels();
                    break;
                }
            }
        }
    }

}