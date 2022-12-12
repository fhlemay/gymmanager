package com.gymargente.gymmanager.model.plan.periode;

public record Periode(int id, String nom, int dureeMois) {

    public Periode(String nom, int dureeMois) {
        this(0, nom, dureeMois);
    }
}
