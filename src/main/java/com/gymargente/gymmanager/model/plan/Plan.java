package com.gymargente.gymmanager.model.plan;

public record Plan(int id,
                   int idTypeAcces,
                   int idPeriode,
                   boolean option,
                   int prix) {
    public Plan(int idTypeAcces,
                int idPeriode,
                boolean option,
                int prix){
        this(0,idTypeAcces, idPeriode, option, prix);
    }
}
