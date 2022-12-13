package com.gymargente.gymmanager.model.client;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao<Client> {
    @Override
    public void create(Client client) {
        String sql = "INSERT INTO client (nom, prenom, dateAdhesion, heureSpecialiste, heureReservee) VALUES (?, ?, ?, ?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, client.nom());
            statement.setString(2, client.prenom());
            statement.setDate(3, new java.sql.Date(client.dateAdhesion().getTime()));
            statement.setInt(4, client.heureSpecialiste());
            statement.setInt(5, client.heureReservee());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Client> findById(int id) {
        String sql = "SELECT nom, prenom, dateAdhesion, heureSpecialiste, heureReservee FROM client WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var client = new Client(
                        id,
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
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
            String sql = "UPDATE client SET nom=?, prenom=?, dateAdhesion=?, heureSpecialiste=?, heureReservee=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, client.nom());
            statement.setString(2, client.prenom());
            statement.setDate(3, new java.sql.Date(client.dateAdhesion().getTime()));
            statement.setInt(4, client.heureSpecialiste());
            statement.setInt(5, client.heureReservee());
            statement.setInt(6, client.id());
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
            String sql = "SELECT id, nom, prenom, dateAdhesion, heureSpecialiste, heureReservee FROM client";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                clients.add(new Client (
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
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

    public List<Client> getClientsByTextSearch(String textToSearch) {
        List<Client> clients = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql_header = "SELECT id, nom, prenom, dateAdhesion, heureSpecialiste, heureReservee FROM client ";
            String sqlSearch = "WHERE CONCAT_WS(\" \", nom, prenom) LIKE ?"; // concat pour chercher sur plusieurs colonnes
            String sql = sql_header+sqlSearch;
            var statement = connection.prepareStatement(sql);
            statement.setString(1, "%"+textToSearch+"%");
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client (
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
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
