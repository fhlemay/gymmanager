package com.gymargente.gymmanager.model.abonnement.etat;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbonnementEtatDao implements Dao<AbonnementEtat> {
    @Override
    public void create(AbonnementEtat abonnementEtat) {
        String sql = "INSERT INTO abonnementEtat (nom) VALUES (?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, abonnementEtat.nom());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<AbonnementEtat> findById(int id) {
        String sql = "SELECT nom FROM abonnementEtat WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new AbonnementEtat(id, resultSet.getString("nom")));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(AbonnementEtat abonnementEtat) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE abonnementEtat SET nom=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, abonnementEtat.nom());
            statement.setInt(2, abonnementEtat.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(AbonnementEtat abonnementEtat) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM abonnementEtat WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, abonnementEtat.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<AbonnementEtat> getAll() {
        List<AbonnementEtat> abonnementEtats = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, nom FROM abonnementEtat";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                abonnementEtats.add(new AbonnementEtat(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return abonnementEtats;
    }
}
