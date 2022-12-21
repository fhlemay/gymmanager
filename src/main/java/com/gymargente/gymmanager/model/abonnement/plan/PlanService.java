package com.gymargente.gymmanager.model.abonnement.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;

public class PlanService {

    public static Map<Plan, List<Plan>> getTreeOfPlans(){

        PlanDao dao = new PlanDao();
        List<Plan> allPlans = dao.getAll();

        Map<Plan, List<Plan>> tree = new HashMap<>();

        List<Plan> plans = allPlans.stream()
                .filter(plan -> Objects.isNull(plan.parent())).toList();
        List<Plan> options = allPlans.stream()
                .filter(plan -> Objects.nonNull(plan.parent())).toList();

        // initialise la map des plans (sans les options).
        for(Plan plan : plans) {
            tree.put(plan, new ArrayList<>());
        }

        // ajoute les options liées au plan.
        for(Plan option : options) {
            tree.get(option.parent()).add(option);
        }

        return tree;
    }
}
