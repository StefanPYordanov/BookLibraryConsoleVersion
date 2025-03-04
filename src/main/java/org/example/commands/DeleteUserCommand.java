package org.example.commands;

import org.example.controller.UserController;
import org.example.service.UserServiceImpl;

public class DeleteUserCommand implements Command {
    UserController userController = new UserController();
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    @Override
    public void execute() {
        userServiceImpl.displayUsers();
        userServiceImpl.deleteUser(userController.userToDelete());
    }
}
