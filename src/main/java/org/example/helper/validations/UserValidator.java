package org.example.helper.validations;

import org.example.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.helper.messages.UserMessages.*;

public class UserValidator {
    UserRepository userRepository = new UserRepository();

    public boolean isPasswordsMatch(String password, String rePassword) { // -> return true if passwords match
        if (password.equals(rePassword)) {
            return true;
        } else {
            System.out.println("Passwords don't match!");
            return false;
        }
    }

    public boolean isEmailValid(String email) { // -> return true if user email is valid
//        String regex = "^\\S+@\\S+\\.\\S+$"; // Regular Expression for email(need to contain /text--@--text--.--text/)
        Pattern pattern = Pattern.compile(REGEX_FOR_VALID_EMAIL);
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
            ResultSet resultSet = userRepository.getUsername(username);

            if (resultSet.next()) {
                System.out.println("Username already exist!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with finding username in database !");
            return true;
        }
    }

    public boolean isEmailExist(String email) { // -> return true if email exist in DB
        try {
            ResultSet resultSet = userRepository.getEmail(email);

            if (resultSet.next()) {
                System.out.println("Email already exist!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with finding email in database !");
            return true;
        }
    }

    public boolean isUserIdExist(int id) { // -> Check if there are users with this id in DB
        try {
            ResultSet resultSet = userRepository.getId(id);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("A problem has occurred with finding user id in database !");
            return true;
        }
    }
    public boolean isPasswordCorrect(String providedPassword, String storedPassword){
        return BCrypt.checkpw(providedPassword, storedPassword);
    }
}
