package com.gymargente.gymmanager.model.abonnement.plan.acces;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccesDao implements Dao<Acces> {
    @Override
    public void create(Acces acces) {
        String sql = "INSERT INTO acces (nom) VALUES (?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, acces.nom());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Acces> findById(int id) {
        String sql = "SELECT nom FROM acces WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Acces(id, resultSet.getString("nom")));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Acces acces) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE acces SET nom=? WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, acces.nom());
            statement.setInt(2, acces.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Acces acces) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM acces WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, acces.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Acces> getAll() {
        List<Acces> access = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, nom FROM acces";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                access.add(new Acces(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return access;
    }
}
