package com.gymargente.gymmanager.model.utilisateur.profil;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.consultation.etat.ConsultationEtat;
import com.gymargente.gymmanager.model.utilisateur.Utilisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfilDao implements Dao<Profil> {
    @Override
    public void create(Profil profil) {
        String sql = "INSERT INTO profil (nom) VALUES (?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, profil.nom());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Profil> findById(int id) {
        String sql = "SELECT nom FROM profil WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Profil(id, resultSet.getString("nom")));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Profil profil) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE profil SET nom=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, profil.nom());
            statement.setInt(2, profil.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Profil profil) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM profil WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, profil.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Profil> getAll() {
        List<Profil> profils = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, nom FROM profil";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                profils.add(new Profil(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return profils;
    }
}
