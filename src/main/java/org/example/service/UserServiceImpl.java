package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.model.entity.UserEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserServiceImpl implements UserService {

    @Override
    public void addUser() {

        try{
            Scanner scanner = new Scanner(System.in);
            UserEntity userEntity = new UserEntity();

            System.out.println("id");
            userEntity.setId(scanner.nextInt());
            System.out.println("username");
            userEntity.setUsername(scanner.nextLine());
            userEntity.setUsername(scanner.nextLine());
            System.out.println("password");
            userEntity.setPassword(scanner.nextLine());
            System.out.println("email");
            userEntity.setEmail(scanner.nextLine());
            System.out.println("full name");
            userEntity.setFullName(scanner.nextLine());
            System.out.println("role");
            userEntity.setRole(scanner.nextLine());

            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "insert into users values ('"+userEntity.getId()+"','"+userEntity.getUsername()
                    +"','"+userEntity.getPassword()+"','"+userEntity.getEmail()+"','"+userEntity.getFullName()
                    +"','"+userEntity.getRole()+"')";
            statement.executeUpdate(query);

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
