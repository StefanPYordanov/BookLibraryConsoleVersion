package org.example.commands;

import org.example.service.UserServiceImpl;

public class RegisterCommand implements Command{
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    String currentUser;
    String role;

    public String getCurrentUser() {
        return currentUser;
    }

    public RegisterCommand setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        return this;
    }

    public String getRole() {
        return role;
    }

    public RegisterCommand setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public void execute() {
        String[] tokens = userServiceImpl.register().split(" ");
        userServiceImpl.login(tokens[0], tokens[1]);
        currentUser = tokens[0];
        if (userServiceImpl.isAdmin(currentUser)){
            role = "admin";
        }else{
            role = "user";
        }
    }
}
