package org.example.commands;

import org.example.service.UserServiceImpl;

import java.util.Scanner;

public class LoginCommand implements Command{
    Scanner scanner = new Scanner(System.in);
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    private String currentUser;
    private String role;

    public String getCurrentUser() {
        return currentUser;
    }

    public LoginCommand setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        return this;
    }

    public String getRole() {
        return role;
    }

    public LoginCommand setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public void execute() {
        if (currentUser == null) {
            System.out.println("Please Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Please Enter your password");
            String password = scanner.nextLine();

            while (!userServiceImpl.login(username, password)) {
                System.out.println("Please Enter your username:");
                username = scanner.nextLine();
                System.out.println("Please Enter your password");
                password = scanner.nextLine();
            }
            currentUser = username;
            if (userServiceImpl.isAdmin(username)) {
                role = "admin";
            } else {
                role = "user";
            }
        } else {
            System.out.println("Invalid Command");
        }
    } //TODO : Move to controller
}
