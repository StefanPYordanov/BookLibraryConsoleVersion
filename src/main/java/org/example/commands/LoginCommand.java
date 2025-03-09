package org.example.commands;

import org.example.service.UserServiceImpl;

import java.util.Scanner;
import static org.example.model.dto.LoggedUserDto.*;

public class LoginCommand implements Command{
    Scanner scanner = new Scanner(System.in);
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    @Override
    public void execute() {
        if (loggedUserUsername.equals("")) {
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
            loggedUserUsername = username;
            if (userServiceImpl.isAdmin(username)) {
                loggedUserRole = "Admin";
            } else {
                loggedUserRole = "User";
            }
        } else {
            System.out.println("Invalid Command");
        }
    } //TODO : Move to controller
}
