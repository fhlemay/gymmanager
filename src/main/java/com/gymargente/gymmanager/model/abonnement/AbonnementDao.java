package com.gymargente.gymmanager.model.abonnement;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.consultation.Consultation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbonnementDao implements Dao<Abonnement> {
    @Override
    public void create(Abonnement abonnement) {
        String sql = "INSERT INTO abonnement (idClient, idPlan, dateDebut, dateFin, etat) VALUES (?, ?, ?, ?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, abonnement.idClient());
            statement.setInt(2, abonnement.idPlan());
            statement.setDate(3, new java.sql.Date(abonnement.dateDebut().getTime()));
            statement.setDate(4, new java.sql.Date(abonnement.dateFin().getTime()));
            statement.setInt(5, abonnement.etat());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Abonnement> findById(int id) {
        String sql = "SELECT idClient, idPlan, dateDebut, dateFin, etat FROM abonnement WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Abonnement(
                        id,
                        resultSet.getInt("idClient"),
                        resultSet.getInt("idPlan"),
                        resultSet.getDate("dateDebut"),
                        resultSet.getDate("dateFin"),
                        resultSet.getInt("etat")
                        ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Abonnement abonnement) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE abonnement SET idClient=?, idPlan=?, dateDebut=?, dateFin=?, etat=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, abonnement.idClient());
            statement.setInt(2, abonnement.idPlan());
            statement.setDate(3, new java.sql.Date(abonnement.dateDebut().getTime()));
            statement.setDate(4, new java.sql.Date(abonnement.dateFin().getTime()));
            statement.setInt(5, abonnement.etat());
            statement.setInt(6, abonnement.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Abonnement abonnement) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM abonnement WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, abonnement.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Abonnement> getAll() {
        List<Abonnement> abonnements = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, idClient, idPlan, dateDebut, dateFin, etat FROM abonnement FROM abonnement";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                abonnements.add(new Abonnement(
                        resultSet.getInt("id"),
                        resultSet.getInt("idClient"),
                        resultSet.getInt("idPlan"),
                        new java.util.Date(resultSet.getTimestamp("dateDebut").getTime()),
                        new java.util.Date(resultSet.getTimestamp("dateFin").getTime()),
                        resultSet.getInt("etat")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return abonnements;
    }
}
