package com.gymargente.gymmanager.model.utilisateur.fonction;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.utilisateur.profil.Profil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FonctionDao implements Dao<Fonction> {

    @Override
    public void create(Fonction fonction) {
        String sql = "INSERT INTO fonction (nom) VALUES (?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, fonction.nom());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Fonction> findById(int id) {
        String sql = "SELECT nom FROM fonction WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var fonction = new Fonction (
                        id,
                        resultSet.getString("nom")
                );
                return Optional.of(fonction);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Fonction fonction) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE fonction SET nom=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, fonction.nom());
            statement.setInt(2, fonction.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Fonction fonction) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM fonction WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, fonction.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Fonction> getAll() {
        List<Fonction> fonctions = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, nom FROM profil";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("nom");
                fonctions.add(new Fonction(id, name));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return fonctions;
    }
}
