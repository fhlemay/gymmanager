package com.gymargente.gymmanager.model.membership;

import com.gymargente.gymmanager.db.Dao;

import java.util.List;
import java.util.Optional;

public class MembershipDao implements Dao<Membership> {
    @Override
    public void create(Membership subscription) {

    }

    @Override
    public Optional<Membership> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Membership subscription) {

    }

    @Override
    public void delete(Membership subscription) {

    }

    @Override
    public List<Membership> getAll() {
        return null;
    }
}
