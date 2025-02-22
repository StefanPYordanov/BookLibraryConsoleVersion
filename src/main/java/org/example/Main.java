package org.example;

import org.example.commands.LoginCommand;
import org.example.commands.MenuManager;
import org.example.commands.RateBookCommand;
import org.example.commands.RegisterCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MenuManager menuManager = new MenuManager();
        Scanner scanner = new Scanner(System.in);
        LoginCommand loginCommand = new LoginCommand();
        RegisterCommand registerCommand = new RegisterCommand();
        RateBookCommand rateBookCommand = new RateBookCommand();

        String currentUser = "";
        String role = "";

        while (true) {
            menuManager.showMenu(role);

            System.out.println("Please choose option from the menu");
            String choice = scanner.nextLine();

            if (choice.equals("3")) {
                menuManager.executeCommand(choice);
                break;
            } else if (choice.equals("1") && currentUser.equals("")) {
                loginCommand.execute();
                currentUser = loginCommand.getCurrentUser();
                role = loginCommand.getRole();
            } else if (choice.equals("2") && currentUser.equals("")) {
                registerCommand.execute();
                currentUser = registerCommand.getCurrentUser();
                role = registerCommand.getRole();
            } else if (choice.equals("6") && !currentUser.equals("")) {
                rateBookCommand.setCurrentUser(currentUser);
                rateBookCommand.execute();
            } else if (choice.equals("8") && !currentUser.equals("")) {
                currentUser = "";
                role = "";
                loginCommand.setCurrentUser(null);
                loginCommand.setRole(null);
            } else {
                menuManager.executeCommand(choice);
            }
        }
    }
}