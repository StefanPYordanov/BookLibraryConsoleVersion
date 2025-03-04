package org.example.commands;

import org.example.controller.UserController;
import org.example.helper.validations.GenericValidator;
import org.example.service.UserServiceImpl;

import java.util.Scanner;

public class GiveRoleCommand implements Command {
    UserController userController = new UserController();
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    @Override
    public void execute() {
        userServiceImpl.displayUsers();
        userServiceImpl.giveRole(userController.promoteUser());

    }
}
