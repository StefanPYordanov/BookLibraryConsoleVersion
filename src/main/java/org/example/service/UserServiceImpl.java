package org.example.service;

import org.example.controller.UserController;
import org.example.helper.validations.GenericValidator;
import org.example.helper.validations.UserValidator;
import org.example.model.entity.UserEntity;
import org.example.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

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
                return false;
            } else {
                String fullName = resultSet.getString(5);
                String pass = resultSet.getString(3);
                if (BCrypt.checkpw(password, pass)) {
                    System.out.println("Welcome " + fullName);
                    return true;
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with login!\nPlease try again !");
            return false;

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid username or password!");
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
    }

    @Override
    public int nextUserId() { // -> increment id for users for next DB insert
        try {
            ResultSet resultSet = userRepository.getBiggestUserId();
            if (resultSet.next()) {
                return resultSet.getInt(1) + INCREMENT_LAST_USER_ID_BY_ONE;
            }
        } catch (Exception e) {
            System.out.println("A problem has occurred with finding next user id in database!");
            return 0;
        }
        return 0;
    }

    @Override
    public boolean isAdmin(String username) { // -> check if user is admin //TODO : consider return String loggedUserRole DTO
        try {
            ResultSet resultSet = userRepository.getUserByUsername(username);
            if (resultSet.next()) {
                return resultSet.getString(6).equals("Admin");
            }
        } catch (SQLException e) {
            System.out.println("There isn't admin with this username!");
            return false;
        }
        return false;
    }
    @Override
    public int findUser(String currentUser) { // -> find user id for vote method
        try {
            ResultSet resultSet = userRepository.getUserIdByUsername(currentUser);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("There isn't user with this username!");
            return 0;
        }
        return 0;
    }
}
