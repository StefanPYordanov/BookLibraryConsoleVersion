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

            String query="select * from users where username='"+username+"' and password= '"+password+"' ";

            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()){
                System.out.println("Invalid username or password !");
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
            System.out.println("username");
            String username = scanner.nextLine();
            while (validator.isFieldEmpty(username)|| validator.isUsernameExist(username)){
                System.out.println("try again with username");
                username = scanner.nextLine();
            }
            userEntity.setUsername(username);

            //       PASSWORD
            System.out.println("password");
            String password = scanner.nextLine();

            System.out.println("repeat password");
            String rePass = scanner.nextLine();
            while (!validator.isPasswordsMatch(password, rePass) || validator.isFieldEmpty(password) || validator.isFieldEmpty(rePass)){
                System.out.println("try again with password");
                password = scanner.nextLine();
                System.out.println("try again to repeat password");
                rePass = scanner.nextLine();
            }
            userEntity.setPassword(password);

            //      EMAIL
            System.out.println("email");
            String email = scanner.nextLine();
            while (!validator.isEmailValid(email) || validator.isEmailExist(email) || validator.isFieldEmpty(email)){
                System.out.println("try again with email");
                email = scanner.nextLine();
            }
            userEntity.setEmail(email);

            //      FULL NAME
            System.out.println("full name");
            String fullName = scanner.nextLine();
            while (validator.isFieldEmpty(fullName)){
                System.out.println("try again with full name");
                fullName = scanner.nextLine();
            }
            userEntity.setFullName(fullName);

            //      ROLE
            String role = "User";
            userEntity.setRole(role);

            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

                String query = "insert into users values ('"+id+"','"+username
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
    public void deleteUser(int id) {
        Validator validator = new Validator();
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            while (!validator.isUserIdExist(id)){
                System.out.println("User don't exist");
                id = scanner.nextInt();
            }
            String query="delete from users where id='"+id+"'";
            statement.executeUpdate(query);
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void displayUsers() {
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "select * from users";

            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("id username password email name role");

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)
                        + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3)
                        + " " + resultSet.getString(4)
                        + " " + resultSet.getString(5)
                        + " " + resultSet.getString(6));
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void giveRole(int id) {
        Validator validator = new Validator();
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            while (!validator.isUserIdExist(id)){
                System.out.println("User don't exist");
                id = scanner.nextInt();
            }

            String query="select * from users where id='"+id+"'";

            ResultSet resultSet = statement.executeQuery(query);

            String update = "update users set `role` = 'Admin' where id ='"+id+"'";
            statement.executeUpdate(update);

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public int nextUserId() {
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "select * from users order by id desc limit 0, 1";

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
    public boolean isAdmin(String username) {
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "select * from users where username = '"+username+"'";

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
}
