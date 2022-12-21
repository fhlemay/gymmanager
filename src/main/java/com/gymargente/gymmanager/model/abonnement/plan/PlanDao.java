package com.gymargente.gymmanager.model.abonnement.plan;

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
    public Optional<Plan> findById(int id) {

        String sql = """
                SELECT A.id AS id, A.idParent AS parent, B.nom AS acces, C.nom AS periode, A.prix AS prix FROM gym_manager.plan AS A
                LEFT JOIN gym_manager.acces AS B ON A.idAcces = B.id
                LEFT JOIN gym_manager.periode AS C ON A.idPeriode = C.id
                WHERE A.id = ?;
                """;

        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int parentId = resultSet.getInt("parent"); // Lorsque int null dans la db, resultatSet est 0.

                if (parentId != 0) { // Il y a un parent.

                    Plan plan = new Plan(id, findById(parentId).get(), // recursion pour trouver le parent.
                            resultSet.getString("acces"),
                            resultSet.getString("periode"),
                            resultSet.getInt("prix"));

                    return Optional.of(plan);
                }
                Plan plan = new Plan(id, null,
                        resultSet.getString("acces"),
                        resultSet.getString("periode"),
                        resultSet.getInt("prix"));
                return Optional.of(plan);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Plan> getAll() {

        List<Plan> list = new ArrayList<>();

        String sql = """
                SELECT A.id AS id, A.idParent AS parent, B.nom AS acces, C.nom AS periode, A.prix AS prix FROM gym_manager.plan AS A
                LEFT JOIN gym_manager.acces AS B ON A.idAcces = B.id
                LEFT JOIN gym_manager.periode AS C ON A.idPeriode = C.id;
                """;

        Connection connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int parentId = resultSet.getInt("parent");
                Plan plan;

                if (parentId != 0) { // il y a un parent.

                    plan = new Plan(
                            resultSet.getInt("id"),
                            findById(parentId).get(), // on cherche le parent.
                            resultSet.getString("acces"),
                            resultSet.getString("periode"),
                            resultSet.getInt("prix"));

                } else {
                    plan = new Plan(
                            resultSet.getInt("id"),
                            null,
                            resultSet.getString("acces"),
                            resultSet.getString("periode"),
                            resultSet.getInt("prix"));
                }
                list.add(plan);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public void create(Plan plan) {

    }

    @Override
    public void update(Plan plan) {

    }

    @Override
    public void delete(Plan plan) {

    }
}
