package com.gymargente.gymmanager.model.client;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;
import com.gymargente.gymmanager.model.consultation.Consultation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao<Client> {
    @Override
    public void create(Client client) {
        String sql = "INSERT INTO client (nom, dateAdhesion, heureSpecialiste, heureReservee) VALUES (?, ?, ?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, client.nom());
            statement.setDate(2, new java.sql.Date(client.dateAdhesion().getTime()));
            statement.setInt(3, client.heureSpecialiste());
            statement.setInt(4, client.heureReservee());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Client> findById(int id) {
        String sql = "SELECT nom, dateAdhesion, heureSpecialiste, heureReservee FROM client WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var client = new Client(
                        id,
                        resultSet.getString("nom"),
                        resultSet.getDate("dateConsultation"),
                        resultSet.getInt("heureSpecialiste"),
                        resultSet.getInt("heureReservee")
                );
                return Optional.of(client);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();

    }

    @Override
    public void update(Client client) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE client SET name=?, dateAdhesion=?, heureSpecialiste=?, heureReservee=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, client.nom());
            statement.setDate(2, new java.sql.Date(client.dateAdhesion().getTime()));
            statement.setInt(3, client.heureSpecialiste());
            statement.setInt(4, client.heureReservee());
            statement.setInt(5, client.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Client client) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM client WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, client.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, name, dateAdhesion, heureSpecialiste, heureReservee FROM client";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                clients.add(new Client (
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new java.util.Date(resultSet.getTimestamp("dateAdhesion").getTime()),
                        resultSet.getInt("heureSpecialiste"),
                        resultSet.getInt("heureReservee")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return clients;
    }
}
