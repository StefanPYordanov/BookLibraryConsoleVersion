package org.example.commands;

import org.example.service.UserServiceImpl;

import java.util.Scanner;

public class DeleteUserCommand implements Command {
    Scanner scanner = new Scanner(System.in);
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    @Override
    public void execute() {
        userServiceImpl.displayUsers();
        System.out.println("Please enter the id of the user you want to delete:");
        int idToDeleteUser = scanner.nextInt();
        userServiceImpl.deleteUser(idToDeleteUser);
        scanner.nextLine();
    }
}
