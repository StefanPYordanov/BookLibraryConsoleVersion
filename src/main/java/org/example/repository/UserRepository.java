package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.entity.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserRepository {
    Connection connection = ConnectionFactory.getConnection();
    public ResultSet getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username='" + username + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
    public void addUser (UserEntity userEntity) throws SQLException {
        String query = "INSERT INTO users (id, username, password, email, full_name, role) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userEntity.getId());
        statement.setString(2, userEntity.getUsername());
        statement.setString(3, BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt()));
        statement.setString(4, userEntity.getEmail());
        statement.setString(5, userEntity.getFullName());
        statement.setString(6, userEntity.getRole());
        statement.executeUpdate();

    }
    public void deleteUserById (int id) throws SQLException {
        String query = "DELETE FROM users WHERE id='" + id + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }
    public ResultSet getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
    public void getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id='" + id + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeQuery();
    }
    public void updateUserRole (int id) throws SQLException {
        String query = "UPDATE users SET `role` = 'Admin' WHERE id ='" + id + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }
    public ResultSet getBiggestUserId () throws SQLException {
        String query = "SELECT * FROM users ORDER BY id DESC LIMIT 0, 1";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
    public ResultSet getIdForUserByUsername(String currentUser) throws SQLException {
        String query = "SELECT id FROM users WHERE username = '" + currentUser + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
    public ResultSet getUsernameByUser (String username ) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT username FROM users WHERE username = '" + username + "'";
        return statement.executeQuery(query);
    }
    public ResultSet getEmail (String email) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT email FROM users WHERE email = '" + email + "'";
        return statement.executeQuery(query);
    }
    public ResultSet getId (int id) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT id FROM users WHERE id = '" + id + "'";
        return statement.executeQuery(query);
    }
}
