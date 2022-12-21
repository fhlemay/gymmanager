package com.gymargente.gymmanager.model.abonnement.plan.periode;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PeriodeDao implements Dao<Periode> {
    @Override
    public void create(Periode periode) {
        String sql = "INSERT INTO periode (nom, dureeMois) VALUES (?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, periode.nom());
            statement.setInt(2, periode.dureeMois());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Periode> findById(int id) {
        String sql = "SELECT nom, dureeMois FROM periode WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Periode(
                        id,
                        resultSet.getString("nom"),
                        resultSet.getInt("dureeMois")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Periode periode) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE periode SET nom=?, dureeMois=? WHERE id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, periode.nom());
            statement.setInt(2, periode.dureeMois());
            statement.setInt(3, periode.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Periode periode) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM periode WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, periode.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Periode> getAll() {
        List<Periode> periodes = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, nom FROM periode";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                periodes.add(new Periode(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getInt("dureeMois")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return periodes;
    }
}
