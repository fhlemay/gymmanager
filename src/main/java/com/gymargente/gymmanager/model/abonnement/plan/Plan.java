package com.gymargente.gymmanager.model.abonnement.plan;

public record Plan(int id,
                   Plan parent,
                   String acces,
                   String periode,
                   int prix) {
    public Plan(Plan Parent,
                String acces,
                String periode,
                int prix){
        this(0,Parent, acces, periode, prix);
    }
}
