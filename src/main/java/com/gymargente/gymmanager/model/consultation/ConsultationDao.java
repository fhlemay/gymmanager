package com.gymargente.gymmanager.model.consultation;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.utilisateur.Utilisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultationDao implements Dao<Consultation> {
    @Override
    public void create(Consultation consultation) {
        String sql = "INSERT INTO consultation (idSpecialiste, idClient, dateConsultation, etat) VALUES (?, ?, ?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, consultation.idSpecialiste());
            statement.setInt(2, consultation.idClient());
            statement.setDate(3, new java.sql.Date(consultation.dateConsultation().getTime()));
            statement.setInt(4, consultation.etat());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Consultation> findById(int id) {
        String sql = "SELECT idSpecialiste, idClient, dateConsultation, etat FROM consultation WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var consultation = new Consultation(
                        id,
                        resultSet.getInt("idSpecialiste"),
                        resultSet.getInt("idClient"),
                        resultSet.getDate("dateConsultation"),
                        resultSet.getInt("etat")
                );
                return Optional.of(consultation);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Consultation consultation) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE consultation SET idSpecialiste=?, idClient=?, dateConsultation=?, etat=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, consultation.idSpecialiste());
            statement.setInt(2, consultation.idClient());
            statement.setDate(3, new java.sql.Date(consultation.dateConsultation().getTime()));
            statement.setInt(4, consultation.etat());
            statement.setInt(5, consultation.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Consultation consultation) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM consulation WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, consultation.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Consultation> getAll() {
        List<Consultation> consultations = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, idSpecialiste, idClient, dateConsultation, etat FROM consultation";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                consultations.add(new Consultation(
                        resultSet.getInt("id"),
                        resultSet.getInt("idSpecialiste"),
                        resultSet.getInt("idClient"),
                        new java.util.Date(resultSet.getTimestamp("dateConsultation").getTime()),
                        resultSet.getInt("etat")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return consultations;
    }
}
