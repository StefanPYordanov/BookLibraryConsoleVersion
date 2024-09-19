package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.helper.Validator;
import org.example.model.entity.UserEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String username, String password) {
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            String query="SELECT * FROM users WHERE username='"+username+"' and password= '"+password+"' ";

            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()){
                System.out.println("Invalid username or password!");
                return false;
            }else{
                String fullName = resultSet.getString(5);
                System.out.println("Welcome " + fullName);
                return true;
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    @Override
    public String register() {
        try{
            Scanner scanner = new Scanner(System.in);
            UserEntity userEntity = new UserEntity();
            Validator validator = new Validator();
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

            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

                String query = "INSERT INTO users VALUES ('"+id+"','"+username
                        +"','"+password+"','"+email+"','"+fullName
                        +"','"+role+"')";
                statement.executeUpdate(query);
                return username + " " + password;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    @Override
    public void deleteUser(int id) { // -> function for admins to block users
        Validator validator = new Validator();
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            while (!validator.isUserIdExist(id)){
                System.out.println("User don't exist \nPlease enter existing id:");
                id = scanner.nextInt();
            }
            String query="DELETE FROM users WHERE id='"+id+"'";
            statement.executeUpdate(query);
            System.out.println("User has been blocked!\n");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void displayUsers() { // -> show all users from DB
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users";

            ResultSet resultSet = statement.executeQuery(query);

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
            System.out.println(e);
        }
    }
    @Override
    public void giveRole(int id) { // -> function for admins to give role to other users
        Validator validator = new Validator();
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            while (!validator.isUserIdExist(id)){
                System.out.println("User don't exist \nPlease enter existing id:");
                id = scanner.nextInt();
            }
            String query="SELECT * FROM users WHERE id='"+id+"'";

            ResultSet resultSet = statement.executeQuery(query);

            String update = "UPDATE users SET `role` = 'Admin' WHERE id ='"+id+"'";
            statement.executeUpdate(update);
            System.out.println("Successfully promoted user!\n");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public int nextUserId() { // -> increment id for users for next DB insert
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users ORDER BY id DESC LIMIT 0, 1";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                return resultSet.getInt(1) + 1;
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }
    @Override
    public boolean isAdmin(String username) { // -> check if user is admin
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users WHERE username = '"+username+"'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                if(resultSet.getString(6).equals("Admin")){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return false;
    }
    public int findUser(String currentUser){ // -> find user id for vote method
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT id FROM users WHERE username = '"+currentUser+"'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }
}
