package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.logger.LoggerUtil;
import org.example.model.entity.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserRepository {
    Connection connection = ConnectionFactory.getConnection();
    public ResultSet getUserByUsername (String username)  { //-> Show user from DB
        try {
            String query = "SELECT * FROM users WHERE username='" + username + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e){
            System.out.println("A problem has occurred with finding user by username!");
            LoggerUtil.logWaring("Finding a user by username in database failed due to SQL Exception");
            return null;

        }
    }
    public void saveUser (UserEntity userEntity) { //-> Add user to DB
        try {
            String query = "INSERT INTO users (id, username, password, email, full_name, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userEntity.getId());
            statement.setString(2, userEntity.getUsername());
            statement.setString(3, BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt()));
            statement.setString(4, userEntity.getEmail());
            statement.setString(5, userEntity.getFullName());
            statement.setString(6, userEntity.getRole());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("A problem has occurred with registration!\nPlease try again !");
            LoggerUtil.logWaring("Adding a user failed due to SQL Exception");
        }
    }
    public void deleteUserById (int id) { //-> Delete user from DB
        try {
            String query = "DELETE FROM users WHERE id='" + id + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch(SQLException e){
            System.out.println("There is problem with deleting user!\nPlease try again !");
            LoggerUtil.logWaring("Deleting a user by username in database failed due to SQL Exception");
        }
    }
    public ResultSet getAllUsers() { //-> Show all users from DB
        try {
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch(SQLException e){
            System.out.println("A problem has occurred with displaying all users!\n" +
                    "Please try again !");
            LoggerUtil.logWaring("Finding all users in database failed");
            return null;
        }
    }
    public void getUserById(int id) { //-> Show user from DB
        try {
            String query = "SELECT * FROM users WHERE id='" + id + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
        } catch(SQLException e){
            System.out.println("A problem has occurred with getting user by id");
            LoggerUtil.logWaring("Finding user by id in database failed");
        }
    }
    public void updateUserRole (int id) { //-> Change user role in DB
        try {
            String query = "UPDATE users SET `role` = 'Admin' WHERE id ='" + id + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch(SQLException e){
            System.out.println("A problem has occurred with promoting a user!\n" +
                    "Please try again !");
            LoggerUtil.logWaring("Promoting user role in database failed");
        }
    }
    public ResultSet getBiggestUserId () { //-> Show biggest id from user in DB
        try {
            String query = "SELECT * FROM users ORDER BY id DESC LIMIT 0, 1";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch(SQLException e){
            System.out.println("A problem has occurred with getting biggest user id in database");
            LoggerUtil.logWaring("Finding next unused id in database failed");
            return null;
        }
    }
    public ResultSet getUserIdByUsername (String currentUser) { //-> Show user id
        try {
            String query = "SELECT id FROM users WHERE username = '" + currentUser + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch(SQLException e){
            System.out.println("A problem has occurred with getting id for user!");
            LoggerUtil.logWaring("Finding user id by username in database failed");
            return null;
        }
    }
    public ResultSet getUsername (String username ) { //-> Show username
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT username FROM users WHERE username = '" + username + "'";
            return statement.executeQuery(query);
        } catch(SQLException e){
            System.out.println("A problem has occurred with getting user from database");
            LoggerUtil.logWaring("Finding user in database failed");
            return null;
        }
    }
    public ResultSet getEmail (String email) { //-> Show user email
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT email FROM users WHERE email = '" + email + "'";
            return statement.executeQuery(query);
        } catch(SQLException e){
            System.out.println("A problem has occurred with getting email from database");
            LoggerUtil.logWaring("Finding user email in database failed");
            return null;
        }
    }
    public ResultSet getId (int id) { //-> Show user id
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT id FROM users WHERE id = '" + id + "'";
            return statement.executeQuery(query);
        } catch(SQLException e){
            System.out.println("A problem has occurred with getting id for user!");
            LoggerUtil.logWaring("Finding user id in database failed");
            return null;
        }
    }
    public void deleteRatingsByUserId (int id){
        try {
            String query = "DELETE FROM ratings WHERE user_id='" + id + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch(SQLException e){
            System.out.println("There is problem with deleting user ratings in database");
            LoggerUtil.logWaring("Deleting user from database failed");
        }
    }
}
