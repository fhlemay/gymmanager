package com.gymargente.gymmanager.model.plan;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanDao implements Dao<Plan> {
    @Override
    public void create(Plan plan) {
        String sql = "INSERT INTO plan (idTypeAcces, idPeriode, option, prix) VALUES (?, ?, ?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, plan.idTypeAcces());
            statement.setInt(2, plan.idPeriode());
            statement.setBoolean(3, plan.option());
            statement.setInt(4, plan.prix());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Plan> findById(int id) {
        String sql = "SELECT idTypeAcces, idPeriode, option, prix FROM plan WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Plan(
                        id,
                        resultSet.getInt("idTypeAcces"),
                        resultSet.getInt("idPeriode"),
                        resultSet.getBoolean("option"),
                        resultSet.getInt("prix")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Plan plan) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE plan SET idTypeAcces=?, idPeriode=?, option=?, prix=? WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, plan.idTypeAcces());
            statement.setInt(2, plan.idPeriode());
            statement.setBoolean(3, plan.option());
            statement.setInt(4, plan.prix());
            statement.setInt(5, plan.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Plan plan) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM plan WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, plan.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Plan> getAll() {
        List<Plan> plans = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, idTypeAcces, idPeriode, option, prix FROM plan FROM plan";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                plans.add(new Plan(
                        resultSet.getInt("id"),
                        resultSet.getInt("idTypeAcces"),
                        resultSet.getInt("idPeriode"),
                        resultSet.getBoolean("option"),
                        resultSet.getInt("prix")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return plans;
    }
}