package org.example;

import org.example.commands.*;

import java.util.Scanner;

import static org.example.helper.messages.TextMessages.*;

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

            if (choice.equals(EXIT_COMMAND)) {
                menuManager.executeCommand(choice);
                break;
            } else if (choice.equals(LOGIN_COMMAND) && currentUser.equals(EMPTY_USER)) {
                loginCommand.execute();
                currentUser = loginCommand.getCurrentUser();
                role = loginCommand.getRole();
            } else if (choice.equals(REGISTER_COMMAND) && currentUser.equals(EMPTY_USER)) {
                registerCommand.execute();
                currentUser = registerCommand.getCurrentUser();
                role = registerCommand.getRole();
            } else if (choice.equals(RATE_BOOK_COMMAND) && !currentUser.equals(EMPTY_USER)) {
                rateBookCommand.setCurrentUser(currentUser);
                rateBookCommand.execute();
            } else if (choice.equals(LOGOUT_COMMAND) && !currentUser.equals(EMPTY_USER)) {
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