package org.example.helper;

import org.example.config.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    Connection connection = ConnectionFactory.getConnection();

    public boolean isPasswordsMatch(String password, String rePassword) { // -> return true if passwords match
        if (password.equals(rePassword)) {
            return true;
        } else {
            System.out.println("Passwords don't match!");
            return false;
        }
    }

    public boolean isEmailValid(String email) { // -> return true if user email is valid
        String regex = "^\\S+@\\S+\\.\\S+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.find()) {
            return true;
        } else {
            System.out.println("Invalid email format!");
            return false;
        }
    }

    public boolean isUsernameExist(String username) { // -> return true if user already exist in DB
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT username FROM users WHERE username = '" + username + "'";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Username already exist!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("No Such Username!!!");
            return true;
        }
    }

    public boolean isEmailExist(String email) { // -> return true if email exist in DB
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT email FROM users WHERE email = '" + email + "'";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Email already exist!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("No Such Email!!!");
            return true;
        }
    }

    public boolean isFieldEmpty(String param) { //-> Check if someone try to input empty text or only few letters

        if (param.trim().length() < 4) {
            System.out.println("Field must contain at least 4 symbols!");
            return true;
        } else {
            return false;
        }
    }

    public boolean isNumberValid(int number) { //-> Check if isbn is a positive number
        if (number < 1) {
            System.out.println("Must be positive number!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isUserIdExist(int id) { // -> Check if there are users with this id
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT id FROM users WHERE id = '" + id + "'";

            ResultSet resultSet = statement.executeQuery(query);

            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("No Such User!!!");
            return true;
        }
    }

    public boolean isBookExist(String name) { //-> Check if book exist in DB
        try {
            String query = "SELECT * FROM books WHERE book_name='" + name + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("No Such book!!!");
            return false;
        }
    }
    public boolean isYear(int year){
        if (year > LocalDate.now().getYear() || year < 1){
            System.out.println("Invalid Year");
            return false;
        }else{
            return true;
        }
    }
}

