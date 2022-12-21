package com.gymargente.gymmanager.controller;

import com.gymargente.gymmanager.model.abonnement.plan.Plan;
import com.gymargente.gymmanager.model.abonnement.plan.PlanService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class PlanController implements Initializable {

    @FXML
    private TreeTableColumn<Plan, String> colAcces;

    @FXML
    private TreeTableColumn<Plan, String> colPeriode;

    @FXML
    private TreeTableColumn<Plan, String> colPrix;

    @FXML
    private TreeTableView<Plan> treePlans;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TreeItem<Plan> hiddenRoot = new TreeItem<>(new Plan(0, null, "", "", 0));
        treePlans.setRoot(hiddenRoot);
        treePlans.setShowRoot(false);

        var tree = PlanService.getTreeOfPlans();

        tree.forEach(
                (plan, options) -> {
                    TreeItem<Plan> subRoot = new TreeItem<>(plan);
                    for (Plan option : options) {
                        subRoot.getChildren().add(new TreeItem<>(option));
                    }
                    hiddenRoot.getChildren().add(subRoot);
                }
        );

        hiddenRoot.getChildren().sort(
                Comparator.comparing((TreeItem<Plan> t) -> t.getValue().acces())
                        .thenComparing((TreeItem<Plan> t) -> t.getValue().prix()));

        colAcces.setCellValueFactory(p -> {
            // p.getValue() returns the TreeItem<Plan> instance for a particular TreeTableView row,
            // p.getValue().getValue() returns the Plan instance inside the TreeItem<Plan>
            return new ReadOnlyStringWrapper(p.getValue().getValue().acces()); // doit retourner un observable.
        });

        colPeriode.setCellValueFactory(p -> {
            return new ReadOnlyStringWrapper(p.getValue().getValue().periode()); // doit retourner un observable.
        });

        colPrix.setCellValueFactory(p -> {
            return new ReadOnlyStringWrapper(Integer.toString(p.getValue().getValue().prix())); // doit retourner un observable.
        });
    }
}
