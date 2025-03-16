package org.example.service;

import org.example.controller.UserController;
import org.example.exceptions.InvalidUsernameOrPasswordException;
import org.example.helper.validations.GenericValidator;
import org.example.helper.validations.UserValidator;
import org.example.logger.LoggerUtil;
import org.example.model.entity.UserEntity;
import org.example.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.helper.messages.TextMessages.*;

public class UserServiceImpl implements UserService {
    UserValidator userValidator = new UserValidator();
    UserRepository userRepository = new UserRepository();
    UserController userController = new UserController();

    @Override
    public boolean login(String username, String password) { //-> Check if user exist in DB with provided credentials1
        try {
            ResultSet resultSet = userRepository.getUserByUsername(username);

            if (!resultSet.next()) {
                System.out.println("Invalid username or password!");
                LoggerUtil.logInfo("Failed login attempt for user " + username + " : Invalid username or password");
                return false;
            } else {
                String fullName = resultSet.getString(5);
                String storedPassword = resultSet.getString(3);
                if (userValidator.isPasswordCorrect(password, storedPassword)) {
                    System.out.println("Welcome " + fullName);
                    LoggerUtil.logInfo(username + " has logged in successfully");
                    return true;
                } else {
                    throw new InvalidUsernameOrPasswordException();
                }
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with login!\nPlease try again !");
            LoggerUtil.logWaring("Database error occurred while attempting to login");
            return false;

        } catch (InvalidUsernameOrPasswordException e) {
            LoggerUtil.logWaring("Login problem due to Invalid Username or Password exception");
            return false;
        }
    }

    @Override
    public String register() { //-> Add user to DB
            UserEntity userToSave = new UserEntity();

            userToSave.setId(nextUserId());
            userToSave.setUsername(userController.registrationUsername());
            userToSave.setPassword(userController.registrationPassword());
            userToSave.setEmail(userController.registrationEmail());
            userToSave.setFullName(userController.registrationFullName());
            userToSave.setRole(INITIAL_USER_ROLE_AFTER_REGISTER);

            userRepository.saveUser(userToSave);

            LoggerUtil.logInfo(userToSave.getUsername() + " has register successfully");

            return userToSave.getUsername() + " " + userToSave.getPassword(); // return credential for login after registration
    }

    @Override
    public void deleteUser(int id) { // -> function for admins to block users
            while (!userValidator.isUserIdExist(id)) {
                System.out.println("User don't exist \nPlease enter existing id:");
                id = GenericValidator.readNumber();
            }
              userRepository.deleteRatingsByUserId(id);
              userRepository.deleteUserById(id);

            System.out.println("User has been blocked!\n");
            LoggerUtil.logInfo("User has been successfully blocked");
    }

    @Override
    public void displayUsers() { // -> show all users from DB
        try {
            ResultSet resultSet = userRepository.getAllUsers();

            while (resultSet.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("\n*ID* : ")
                        .append(resultSet.getString(1))
                        .append(" *Username* :")
                        .append(resultSet.getString(2))
                        .append(" *Email* : ")
                        .append(resultSet.getString(4))
                        .append(" *Full Name* : ")
                        .append(resultSet.getString(5))
                        .append(" *Role* : ")
                        .append(resultSet.getString(6));
                System.out.println(builder);
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with displaying users!\nPlease try again !");
            LoggerUtil.logWaring("Database error occurred while attempting to display all users");
        }
    }

    @Override
    public void giveRole(int id) { // -> function for admins to give role to other users
            while (!userValidator.isUserIdExist(id)) {
                System.out.println("User don't exist \nPlease enter existing id:");
                id = GenericValidator.readNumber();
            }
            userRepository.getUserById(id);
            userRepository.updateUserRole(id);

            System.out.println("Successfully promoted user!\n");
            LoggerUtil.logInfo("User has been successfully promoted");
    }

    @Override
    public int nextUserId() { // -> increment id for users for next DB insert
        try {
            ResultSet resultSet = userRepository.getBiggestUserId();
            if (resultSet.next()) {
                return resultSet.getInt(1) + INCREMENT_LAST_USER_ID_BY_ONE;
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with finding next user id in database!");
            LoggerUtil.logWaring("Database error occurred while attempting to find next user id");
            return 0;
        }
        return 0;
    }

    @Override
    public String findRole(String username) {
        try {
            ResultSet resultSet = userRepository.getUserByUsername(username);
            if (resultSet.next()) {
                if (resultSet.getString(6).equals("Admin")){
                   return "Admin";
                }else{
                   return "User";
                }
            }
        } catch (SQLException e) {
            System.out.println("There isn't admin with this username!");
            LoggerUtil.logWaring("Database error occurred while attempting to find user role");
        }
        return EMPTY_USER;
    }
    @Override
    public int findUserId(String currentUser) { // -> find user id for vote method
        try {
            ResultSet resultSet = userRepository.getUserIdByUsername(currentUser);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("There isn't user with this username!");
            LoggerUtil.logWaring("Database error occurred while attempting to find user id from ratings table");
            return 0;
        }
        return 0;
    }
}
