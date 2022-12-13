package com.gymargente.gymmanager.model.utilisateur.utilisateurprofil;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurProfilDao implements Dao<UtilisateurProfil> {
    @Override
    public void create(UtilisateurProfil utilisateurProfil) {
        String sql = "INSERT INTO utilisateurProfil (idUtilisateur, idProfil) VALUES (?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, utilisateurProfil.idUtilisateur());
            statement.setInt(2, utilisateurProfil.idProfil());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UtilisateurProfil> findById(int id) {
        return Optional.empty();
    }

    // Retourne les profilId associés à un utilisateur.
    public Optional<List<Integer>> getProfilIdsfor(int userId) {
        String sql = "SELECT idProfil FROM utilisateurProfil WHERE idUtilisateur = ?";
        Connection connection = Database.getInstance().getConnection();
        List<Integer> profilsId = new ArrayList<>();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                profilsId.add(resultSet.getInt("idProfil"));

                return Optional.of(profilsId);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(UtilisateurProfil utilisateurProfil) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "UPDATE utilisateurProfil SET idUtilisateur=?, idProfil=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, utilisateurProfil.idUtilisateur());
            statement.setInt(2, utilisateurProfil.idProfil());
            statement.setInt(3, utilisateurProfil.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(UtilisateurProfil utilisateurProfil) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM utilisateurProfil WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, utilisateurProfil.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<UtilisateurProfil> getAll() {
        List<UtilisateurProfil> utilisateurProfils = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT id, idUtilisateur, idProfile FROM utilisateurProfil";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var idUtilisateur = resultSet.getInt("idUtilisateur");
                var IdProfile = resultSet.getInt("idProfile");
                utilisateurProfils.add(new UtilisateurProfil(id, idUtilisateur, IdProfile));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return utilisateurProfils;
    }
}
