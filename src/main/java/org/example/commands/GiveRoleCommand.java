package org.example.commands;

import org.example.service.UserServiceImpl;

import java.util.Scanner;

public class GiveRoleCommand implements Command {
    Scanner scanner = new Scanner(System.in);
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    @Override
    public void execute() {
        userServiceImpl.displayUsers();
        System.out.println("Please enter the id of the user you want to become admin:");
        int idToBecomeAdmin = scanner.nextInt();
        userServiceImpl.giveRole(idToBecomeAdmin);
        scanner.nextInt();
    }
}
