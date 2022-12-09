package com.gymargente.gymmanager.model.utilisateur.utilisateurprofilfonction;

import com.gymargente.gymmanager.db.Dao;
import com.gymargente.gymmanager.db.DaoException;
import com.gymargente.gymmanager.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurProfileFonctionDao implements Dao<UtilisateurProfilFonction> {
    @Override
    public void create(UtilisateurProfilFonction utilisateurProfilFonction) {
        String sql = "INSERT INTO utilisateurProfilFonction (idUtilisateurProfil, idFonction) VALUES (?, ?)";
        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, utilisateurProfilFonction.idUtilisateurProfile());
            statement.setInt(2, utilisateurProfilFonction.idFonction());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(UtilisateurProfilFonction utilisateurProfilFonction) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "DELETE FROM utilisateurProfilFonction WHERE idUtilisateurProfil = ? AND idFonction = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, utilisateurProfilFonction.idUtilisateurProfile());
            statement.setInt(2, utilisateurProfilFonction.idFonction());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Integer> getAllFonctionId (int idUtilisateurProfil) {
        List<Integer> fonctionsId = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = "SELECT idFonction FROM utilisateurProfilFonction WHERE idUtilisateurProfile = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, idUtilisateurProfil);
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer fonctionId = resultSet.getInt("idFonction");
                fonctionsId.add(fonctionId);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return fonctionsId;
    }

    @Override
    public Optional<UtilisateurProfilFonction> findById(int id) {
        // Ne peut pas chercher avec un id. La table a seulement 2 colonnes qui forment la clé.
        return Optional.empty();
    }
    @Override
    public void update(UtilisateurProfilFonction utilisateurProfilFonction) {
        // Rien à mettre à jour. On peut seulement effacer et ajouter.
    }
    @Override
    public List<UtilisateurProfilFonction> getAll() {
        // N'a pas d'utilité. (TODO : à confirmer ?)
        return null;
    }
}
