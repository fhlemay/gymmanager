package com.gymargente.gymmanager.model.consultation.etat;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.utilisateur.profil.Profil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultationEtatDao implements Dao<ConsultationEtat> {
    @Override
    public void create(ConsultationEtat consultationEtat) {
        String sql = "INSERT INTO consultationEtat (nom) VALUES (?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, consultationEtat.nom());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<ConsultationEtat> findById(int id) {
        String sql = "SELECT nom FROM consultationEtat WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new ConsultationEtat(id, resultSet.getString("nom")));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(ConsultationEtat consultationEtat) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE consultationEtat SET nom=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, consultationEtat.nom());
            statement.setInt(2, consultationEtat.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(ConsultationEtat consultationEtat) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM consultationEtat WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, consultationEtat.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ConsultationEtat> getAll() {
        List<ConsultationEtat> consultationEtats = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, nom FROM consultationEtat";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                consultationEtats.add(new ConsultationEtat(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return consultationEtats;
    }
}
