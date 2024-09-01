package org.example.service;

import org.example.config.ConnectionFactory;
import org.example.model.entity.UserEntity;

import java.sql.Connection;
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
}
