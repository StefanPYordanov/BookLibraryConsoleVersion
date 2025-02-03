package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.entity.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    Connection connection = ConnectionFactory.getConnection();
    public ResultSet getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username='" + username + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public int addUser (UserEntity userEntity) throws SQLException {
        String query = "INSERT INTO users (id, username, password, email, full_name, role) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userEntity.getId());
        statement.setString(2, userEntity.getUsername());
        statement.setString(3, BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt()));
        statement.setString(4, userEntity.getEmail());
        statement.setString(5, userEntity.getFullName());
        statement.setString(6, userEntity.getRole());
        return statement.executeUpdate();

    }
    public void deleteUserById (int id) throws SQLException {
        String query = "DELETE FROM users WHERE id='" + id + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }
    public ResultSet getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public ResultSet getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id='" + id + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public void updateUserRole (int id) throws SQLException {
        String query = "UPDATE users SET `role` = 'Admin' WHERE id ='" + id + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }
    public ResultSet getBiggestUserId () throws SQLException {
        String query = "SELECT * FROM users ORDER BY id DESC LIMIT 0, 1";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public ResultSet getIdForUserByUsername(String currentUser) throws SQLException {
        String query = "SELECT id FROM users WHERE username = '" + currentUser + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
}
