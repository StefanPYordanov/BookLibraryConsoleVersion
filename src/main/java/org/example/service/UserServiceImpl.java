package org.example.service;

import org.example.helper.UserMenu;
import org.example.helper.Validator;
import org.example.model.entity.UserEntity;
import org.example.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();
    UserMenu userMenu = new UserMenu();
    UserRepository userRepository = new UserRepository();

    @Override
    public boolean login(String username, String password) {
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
            System.out.println("Can't Login !!!");
            return false;

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid username or password!");
            return false;
        }
    }

    @Override
    public String register() {
        try {
            UserEntity userEntity = new UserEntity();

            userEntity.setId(nextUserId());
            userEntity.setUsername(userMenu.registerUserMenuUsername());
            userEntity.setPassword(userMenu.registerUserMenuPassword());
            userEntity.setEmail(userMenu.registerUserMenuEmail());
            userEntity.setFullName(userMenu.registerUserMenuFullName());
            userEntity.setRole("User");

            userRepository.addUser(userEntity);

            return userEntity.getUsername() + " " + userEntity.getPassword();

        } catch (SQLException e) {
            System.out.println("Can't Register!!!");
            return null;
        }

    }

    @Override
    public void deleteUser(int id) { // -> function for admins to block users
        try {
            while (!validator.isUserIdExist(id)) {
                userMenu.userDoNotExistMenu();
                id = scanner.nextInt();
            }
              userRepository.deleteUserById(id);

            System.out.println("User has been blocked!\n");
        } catch (SQLException e) {
            System.out.println("Can't delete user!!!");
        }
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
            System.out.println("Can't Display users!!!");
        }
    }

    @Override
    public void giveRole(int id) { // -> function for admins to give role to other users
        try {
            while (!validator.isUserIdExist(id)) {
                userMenu.userDoNotExistMenu();
                id = scanner.nextInt();
            }
            userRepository.getUserById(id);
            userRepository.updateUserRole(id);

            System.out.println("Successfully promoted user!\n");
        } catch (SQLException e) {
            System.out.println("Can't give role !!!");
        }
    }

    @Override
    public int nextUserId() { // -> increment id for users for next DB insert
        try {
            ResultSet resultSet = userRepository.getBiggestUserId();
            if (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println("No next id !!!");
            return 0;
        }
        return 0;
    }

    @Override
    public boolean isAdmin(String username) { // -> check if user is admin
        try {
            ResultSet resultSet = userRepository.getUserByUsername(username);
            if (resultSet.next()) {
                return resultSet.getString(6).equals("Admin");
            }
        } catch (SQLException e) {
            System.out.println("No such admin !!!");
            return false;
        }
        return false;
    }

    public int findUser(String currentUser) { // -> find user id for vote method
        try {
            ResultSet resultSet = userRepository.getIdForUserByUsername(currentUser);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("No Such User!!!");
            return 0;
        }
        return 0;
    }
}
