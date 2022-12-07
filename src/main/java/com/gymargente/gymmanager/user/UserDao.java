package com.gymargente.gymmanager.user;

import com.gymargente.gymmanager.persistence.Dao;
import com.gymargente.gymmanager.persistence.DaoException;
import com.gymargente.gymmanager.persistence.Database;
import com.gymargente.gymmanager.user.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    @Override
    public void save(User user) {
        String sql = "insert into user (name, password) values (?, ?)";
        var connection = Database.getInstance().getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        var connection = Database.getInstance().getConnection();
        String sql = "select * from user where id = ?";
        try {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if(resultSet.next()){
                var name = resultSet.getString("name");
                var password = resultSet.getString("password");
                User user = new User(id, name, password);
                return Optional.of(user);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return Optional.empty();
    }

    @Override
    public void update(User user) {
        var connection = Database.getInstance().getConnection();
        try {
            String sql = "update user set name=? where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
//            statement.setString(2, user.getPassword());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(User user) {
        var connection = Database.getInstance().getConnection();
        try {
            String sql = "delete from user where id=?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        var connection = Database.getInstance().getConnection();

        try {
            String sql = "select * from user";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var password = resultSet.getString("password");
                users.add(new User(id, name, password));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }
}
