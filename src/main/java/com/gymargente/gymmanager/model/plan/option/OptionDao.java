package com.gymargente.gymmanager.model.plan.option;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionDao implements Dao<Option> {
    @Override
    public void create(Option option) {
        String sql = "INSERT INTO option (idPlan, idOption) VALUES (?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, option.idPlan());
            statement.setInt(2, option.idOption());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Option option) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM option WHERE idPlan = ? AND idOption = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, option.idPlan());
            statement.setInt(2, option.idOption());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Integer> getAllOptions (int idPlan) {
        List<Integer> fonctionsId = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT idOption FROM option WHERE idPlan = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, idPlan);
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer fonctionId = resultSet.getInt("idOption");
                fonctionsId.add(fonctionId);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return fonctionsId;
    }

    @Override
    public Optional<Option> findById(int id) {
        // Ne peut pas chercher avec un id. La table a seulement 2 colonnes qui forment la clé.
        return Optional.empty();
    }
    @Override
    public void update(Option option) {
        // Rien à mettre à jour. On peut seulement effacer et ajouter.
    }
    @Override
    public List<Option> getAll() {
        // N'a pas d'utilité. (TODO : à confirmer ?)
        return null;
    }
}
