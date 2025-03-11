package org.example.commands;

import org.example.controller.UserController;
import org.example.service.UserServiceImpl;

import static org.example.model.dto.LoggedUserDto.*;

public class LoginCommand implements Command{
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    UserController userController = new UserController();

    @Override
    public void execute() {
            String username = userController.loginUsername();
            String password = userController.loginPassword();

            while (!userServiceImpl.login(username, password)) {
                username = userController.loginUsername();
                password = userController.loginPassword();
            }
            loggedUserUsername = username;
            loggedUserRole = userServiceImpl.findRole(username);
    }
}
