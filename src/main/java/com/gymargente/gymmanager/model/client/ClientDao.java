package com.gymargente.gymmanager.model.client;

import com.gymargente.gymmanager.db.Dao;

import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao<Client> {
    @Override
    public void create(Client client) {

    }

    @Override
    public Optional<Client> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void delete(Client client) {

    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
