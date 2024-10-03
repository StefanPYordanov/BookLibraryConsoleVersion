package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.helper.PasswordManager;
import org.example.helper.Validator;
import org.example.model.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    Scanner scanner = new Scanner(System.in);
    Connection connection = ConnectionFactory.getConnection();
    Validator validator = new Validator();
    PasswordManager passwordManager = new PasswordManager();
    @Override
    public boolean login(String username, String password) {
        try {
            String query="SELECT * FROM users WHERE username='"+username+"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                System.out.println("Invalid username or password!");
                return false;
            }else{
                String fullName = resultSet.getString(5);
                String pass = resultSet.getString(3);
                if (password.equals(passwordManager.passwordDecrypt(pass))){
                    System.out.println("Welcome " + fullName);
                    return true;
                }else{
                    System.out.println("Invalid username or password!");
                 return false;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Can't Login !!!");
            return false;
        }
    }
    @Override
    public String register() {
        try{
            UserEntity userEntity = new UserEntity();
            //      ID
            int id = nextUserId();
            userEntity.setId(id);
            //      USERNAME
            System.out.println("Please enter username:");
            String username = scanner.nextLine();
            while (validator.isFieldEmpty(username)|| validator.isUsernameExist(username)){
                System.out.println("Please enter username:");
                username = scanner.nextLine();
            }
            userEntity.setUsername(username);
            //       PASSWORD
            System.out.println("Please enter password:");
            String password = scanner.nextLine();
            System.out.println("Repeat password:");
            String rePass = scanner.nextLine();
            while (!validator.isPasswordsMatch(password, rePass) || validator.isFieldEmpty(password) || validator.isFieldEmpty(rePass)){
                System.out.println("Please enter password:");
                password = scanner.nextLine();
                System.out.println("Please repeat password:");
                rePass = scanner.nextLine();
            }
            userEntity.setPassword(password);
            //      EMAIL
            System.out.println("Please enter email:");
            String email = scanner.nextLine();
            while (!validator.isEmailValid(email) || validator.isEmailExist(email) || validator.isFieldEmpty(email)){
                System.out.println("Please enter email:");
                email = scanner.nextLine();
            }
            userEntity.setEmail(email);
            //      FULL NAME
            System.out.println("Please enter your full name:");
            String fullName = scanner.nextLine();
            while (validator.isFieldEmpty(fullName)){
                System.out.println("Please enter your full name:");
                fullName = scanner.nextLine();
            }
            userEntity.setFullName(fullName);
            //      ROLE
            String role = "User";
            userEntity.setRole(role);

            String query = "INSERT INTO users (id, username, password, email, full_name, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userEntity.getId());
            statement.setString(2, userEntity.getUsername());
            statement.setString(3, passwordManager.passwordEncrypt(userEntity.getPassword()));
            statement.setString(4, userEntity.getEmail());
            statement.setString(5, userEntity.getFullName());
            statement.setString(6, userEntity.getRole());
            statement.executeUpdate();

                return username + " " + password;
        }catch (Exception e){
            System.out.println("Can't Register!!!");
        }
        return null;
    }
    @Override
    public void deleteUser(int id) { // -> function for admins to block users
        try {
            while (!validator.isUserIdExist(id)){
                System.out.println("User don't exist \nPlease enter existing id:");
                id = scanner.nextInt();
            }
            String query="DELETE FROM users WHERE id='"+id+"'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

            System.out.println("User has been blocked!\n");
        }
        catch (Exception e) {
            System.out.println("Can't delete user!!!");
        }
    }
    @Override
    public void displayUsers() { // -> show all users from DB
        try{
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                StringBuilder builder = new StringBuilder();
                builder.append("\n*ID* : " + resultSet.getString(1)
                        + " *Username* :" + resultSet.getString(2)
                        + " *Email* : " + resultSet.getString(4)
                        + " *Full Name* : " + resultSet.getString(5)
                        + " *Role* : " + resultSet.getString(6));
                System.out.println(builder.toString());
            }
        }catch (Exception e){
            System.out.println("Can't Display users!!!");
        }
    }
    @Override
    public void giveRole(int id) { // -> function for admins to give role to other users
        try {
            while (!validator.isUserIdExist(id)){
                System.out.println("User don't exist \nPlease enter existing id:");
                id = scanner.nextInt();
            }
            String query="SELECT * FROM users WHERE id='"+id+"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            String update = "UPDATE users SET `role` = 'Admin' WHERE id ='"+id+"'";
            statement.executeUpdate(update);

            System.out.println("Successfully promoted user!\n");
        }
        catch (Exception e) {
            System.out.println("Can't give role !!!");
        }
    }
    @Override
    public int nextUserId() { // -> increment id for users for next DB insert
        try{
            String query = "SELECT * FROM users ORDER BY id DESC LIMIT 0, 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt(1) + 1;
            }
        }catch (Exception e){
            System.out.println("No next id !!!");
            return 0;
        }
        return 0;
    }
    @Override
    public boolean isAdmin(String username) { // -> check if user is admin
        try{
            String query = "SELECT * FROM users WHERE username = '"+username+"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                if(resultSet.getString(6).equals("Admin")){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (Exception e){
            System.out.println("No such admin !!!");
            return false;
        }
        return false;
    }
    public int findUser(String currentUser){ // -> find user id for vote method
        try{
            String query = "SELECT id FROM users WHERE username = '"+currentUser+"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (Exception e){
            System.out.println("No Such User!!!");
            return 0;
        }
        return 0;
    }
}
