package com.gymargente.gymmanager.model.utilisateur;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.*;

public class UtilisateurDao implements Dao<Utilisateur> {

    @Override
    public void create(Utilisateur utilisateur) {
        // TODO : create utilisateur : on ne veut pas 2 identifiant pareils.
        String sql = "INSERT INTO utilisateur (identifiant, motDePasse) VALUES (?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, utilisateur.identifiant());
            statement.setString(2, utilisateur.motDePasse());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Utilisateur> findById(int id) {
        String sql = "SELECT identifiant, motDePasse FROM utilisateur WHERE id = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var utilisateur = new Utilisateur(
                        id,
                        resultSet.getString("identifiant"),
                        resultSet.getString("motDePasse")
                        );
                return Optional.of(utilisateur);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    public Optional<Utilisateur> findByIdentifiant(String identifiant) {
        String sql = "SELECT id, identifiant, motDePasse FROM utilisateur WHERE identifiant = ?";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, identifiant);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var utilisateur = new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("identifiant"),
                        resultSet.getString("motDePasse")
                );
                return Optional.of(utilisateur);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Utilisateur utilisateur) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE utilisateur SET identifiant=?, motDePasse=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, utilisateur.identifiant());
            statement.setString(2, utilisateur.motDePasse());
            statement.setInt(3, utilisateur.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM utilisateur WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, utilisateur.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Utilisateur> getAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, identifiant, motDePasse FROM utilisateur";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("identifiant");
                var password = resultSet.getString("motDePasse");
                utilisateurs.add(new Utilisateur(id, name, password));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return utilisateurs;
    }
}
