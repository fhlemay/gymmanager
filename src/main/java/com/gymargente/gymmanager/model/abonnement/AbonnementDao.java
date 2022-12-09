package com.gymargente.gymmanager.model.abonnement;

import com.gymargente.gymmanager.db.Dao;

import java.util.List;
import java.util.Optional;

public class AbonnementDao implements Dao<Abonnement> {
    @Override
    public void create(Abonnement subscription) {

    }

    @Override
    public Optional<Abonnement> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Abonnement subscription) {

    }

    @Override
    public void delete(Abonnement subscription) {

    }

    @Override
    public List<Abonnement> getAll() {
        return null;
    }
}
