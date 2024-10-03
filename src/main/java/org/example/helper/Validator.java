package org.example.helper;

import org.example.config.ConnectionFactory;
import org.example.model.entity.BookEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
   public boolean isPasswordsMatch (String password, String rePassword){ // -> return true if passwords match
       if (password.equals(rePassword)){
           return true;
       }else{
           System.out.println("Passwords don't match!");
           return false;
       }
    }
    public boolean isEmailValid(String email){ // -> return true if user email is valid
        String regex = "^\\S+@\\S+\\.\\S+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.find()){
            return true;
        }else{
            System.out.println("Invalid email format!");
            return false;
        }
    }
    public boolean isUsernameExist(String username){ // -> return true if user already exist in DB
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT username FROM users WHERE username = '"+username+"'";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                System.out.println("Username already exist!");
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println("No Such Username!!!");
            return true;
        }
    }
    public boolean isEmailExist(String email){ // -> return true if email exist in DB
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT email FROM users WHERE email = '"+email+"'";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                System.out.println("Email already exist!");
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println("No Such Email!!!");
            return true;
        }
    }
    public boolean isFieldEmpty(String param){ //-> Check if someone try to input empty text or only few letters
       if (param.length() < 4){
           System.out.println("Field must contain at least 4 symbols!");
           return true;
       }else{
           return false;
       }
    }
    public boolean isNumberValid (int number){ //-> Check if isbn is a positive number
       if (number < 1){
           System.out.println("Must be positive number!");
           return false;
       }else{
           return true;
       }
    }
    public boolean isUserIdExist (int id){ // -> Check if there are users with this id
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT id FROM users WHERE id = '"+id+"'";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            System.out.println("No Such User!!!");
            return true;
        }
    }
}

