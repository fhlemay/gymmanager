package com.gymargente.gymmanager.model.consultation.prixheuresconsultation;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.client.Client;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrixHeuresConsultationDao implements Dao<PrixHeuresConsultation> {

    @Override
    public void create(PrixHeuresConsultation prixHeuresConsultation) {

    }

    @Override
    public Optional<PrixHeuresConsultation> findById(int id) {
        String sql = "SELECT id, heureMin, heureMax, prix FROM prixHeuresConsultation WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var prixHeuresConsultation = new PrixHeuresConsultation (
                        resultSet.getInt("id"),
                        resultSet.getInt("heureMin"),
                        resultSet.getInt("heureMAx"),
                        resultSet.getBigDecimal("prix")
                );
                return Optional.of(prixHeuresConsultation);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(PrixHeuresConsultation prixHeuresConsultation) {

    }

    @Override
    public void delete(PrixHeuresConsultation prixHeuresConsultation) {

    }

    @Override
    public List<PrixHeuresConsultation> getAll() {
        List<PrixHeuresConsultation> clients = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, heureMin, heureMax, prix FROM prixHeuresConsultation";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                clients.add(new PrixHeuresConsultation (
                        resultSet.getInt("id"),
                        resultSet.getInt("heureMin"),
                        resultSet.getInt("heureMAx"),
                        resultSet.getBigDecimal("prix")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return clients;
    }

    public Optional<PrixHeuresConsultation> getPriceForHoursQty(int hoursQty) {
        String sql = "SELECT id, heureMin, heureMax, prix FROM prixHeuresConsultation WHERE heureMin <= ? AND heureMax >= ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, hoursQty);
            statement.setInt(2, hoursQty);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return Optional.of(new PrixHeuresConsultation(
                        resultSet.getInt("id"),
                        resultSet.getInt("heureMin"),
                        resultSet.getInt("heureMax"),
                        resultSet.getBigDecimal("prix")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }
}