package com.gymargente.gymmanager.model.consultation.blocsheures;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.client.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlocsHeuresDao implements Dao<BlocsHeures> {

    @Override
    public void create(BlocsHeures blocsHeures) {
        String sql = "INSERT INTO blocHeuresConsultation (idClient, heuresAchetees, heuresReservees, heuresRestantes, idPrix ) VALUES (?, ?, ?, ?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, blocsHeures.client().id());
            statement.setInt(2, blocsHeures.heuresAchetees());
            statement.setInt(3, blocsHeures.heuresReservees());
            statement.setInt(4, blocsHeures.heuresRestantes());
            statement.setInt(5, blocsHeures.idPrix());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<BlocsHeures> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(BlocsHeures blocsHeures) {

    }

    @Override
    public void delete(BlocsHeures blocsHeures) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM blocHeuresConsultation WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, blocsHeures.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<BlocsHeures> getAll() {
        return null;
    }

    // Retourne les BlocsHeures associés à un client.
    public Optional<List<BlocsHeures>> getBlocHeuresfor(Client client) {

        String sql = "SELECT * FROM blocHeuresConsultation WHERE idClient = ?";
        Connection connection = Database.getInstance().getConnection();
        List<BlocsHeures> blocsHeures = new ArrayList<>();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, client.id());
            var resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst())
                return Optional.empty(); // pas de donnees
            while (resultSet.next()) {
                blocsHeures.add(new BlocsHeures(
                        resultSet.getInt("id"),
                        client,
                        resultSet.getInt("heuresAchetees"),
                        resultSet.getInt("heuresReservees"),
                        resultSet.getInt("heuresRestantes"),
                        resultSet.getInt("idPrix"))
                );
            }
            statement.close();
            return Optional.of(blocsHeures);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<BlocsHeures> getSumBlocsHeures (Client client) {
        String sql = """
        SELECT SUM(heuresAchetees) , SUM(heuresReservees), SUM(heuresRestantes)
        FROM blocHeuresConsultation
        WHERE idClient = ?
        GROUP BY idClient
        """;
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, client.id());
            var resultSet = statement.executeQuery();
            BlocsHeures blocsHeuresSum = null;

            if (!resultSet.isBeforeFirst())
                return Optional.empty(); // pas de donnees
            while (resultSet.next()) {
                blocsHeuresSum = new BlocsHeures(
                        0,
                        client,
                        resultSet.getInt("SUM(heuresAchetees)"),
                        resultSet.getInt("SUM(heuresReservees)"),
                        resultSet.getInt("SUM(heuresRestantes)"),
                        0
                );
            }
            statement.close();
            return Optional.of(blocsHeuresSum);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
