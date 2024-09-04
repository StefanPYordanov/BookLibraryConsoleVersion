package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.model.entity.UserEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class UserServiceImpl implements UserService {

    @Override
    public void login(String username, String password) {
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            String query="select * from users where username='"+username+"' and password= '"+password+"' ";

            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()){
                System.out.println("Invalid username or password !");
            }else{
                String fullName = resultSet.getString(5);
                System.out.println("Welcome " + fullName);
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void register() {
        try{
            Scanner scanner = new Scanner(System.in);
            UserEntity userEntity = new UserEntity();
            Random random = new Random();

            int id = random.nextInt(1, 10000000);
            userEntity.setId(id);
            System.out.println("username");
            String username = scanner.nextLine();
            userEntity.setUsername(username);
            System.out.println("password");
            String password = scanner.nextLine();
            userEntity.setPassword(password);
            System.out.println("repeat password");
            String rePass = scanner.nextLine();
            System.out.println("email");
            String email = scanner.nextLine();
            userEntity.setEmail(email);
            System.out.println("full name");
            String fullName = scanner.nextLine();
            userEntity.setFullName(fullName);
            String role = "User";
            userEntity.setRole(role);

            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            if (password.equals(rePass)){
                String query = "insert into users values ('"+id+"','"+username
                        +"','"+password+"','"+email+"','"+fullName
                        +"','"+role+"')";
                statement.executeUpdate(query);

            }else{
                System.out.println("Passwords mismatch");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

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
        try {
            Connection connection=ConnectionFactory.getConnection();
            Statement statement=connection.createStatement();

            String query="select * from users where id='"+id+"'";

            ResultSet resultSet = statement.executeQuery(query);

            String update = "update users set `role` = 'Admin' where id ='"+id+"'";
            statement.executeUpdate(update);

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

}
