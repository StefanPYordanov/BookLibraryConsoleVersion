package org.example.commands;

import org.example.service.UserServiceImpl;

import static org.example.model.LoggedUserDto.*;

public class RegisterCommand implements Command{
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    @Override
    public void execute() {
        String[] credentials = userServiceImpl.register().split(" ");
        userServiceImpl.login(credentials[0], credentials[1]); //use credential for login after register
        loggedUserUsername = credentials[0];
        if (userServiceImpl.isAdmin(loggedUserUsername)){
            loggedUserRole = "Admin";
        }else{
            loggedUserRole = "User";
        }
    }
}
