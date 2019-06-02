package com.denisenko.dao;

import com.denisenko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    protected UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User save(User user) {

        try {
            String sql = "INSERT INTO  users (login,password) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(Long aLong) {
        User userDb = new User();
        try {
            String sql = "SELECT * FROM users WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            ResultSet set = preparedStatement.executeQuery();
            if (set.next()) {
                String login = set.getString("login");
                String password = set.getString("password");
                userDb.setId(aLong);
                userDb.setLogin(login);
                userDb.setPassword(password);
                return userDb;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDb;
    }

    @Override
    public User update(User user) {

        try {
            String sql = "UPDATE users SET login = ? AND  password = ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.execute();
            return get(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return super.update(user);
    }

    @Override
    public void delete(Long t) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, t);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.delete(t);
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allUsers.add(new User(resultSet.getLong("id")
                        , resultSet.getString("login")
                        , resultSet.getString("password")));
            }
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}
