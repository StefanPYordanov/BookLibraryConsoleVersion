package org.example;

import org.example.commands.*;

import java.util.Scanner;

import static org.example.helper.messages.TextMessages.*;
import static org.example.model.LoggedUserDto.*;

public class Main {
    public static void main(String[] args) {

        MenuManager menuManager = new MenuManager();
        Scanner scanner = new Scanner(System.in);
        LoginCommand loginCommand = new LoginCommand();
        RegisterCommand registerCommand = new RegisterCommand();
        RateBookCommand rateBookCommand = new RateBookCommand();

        while (true) {
            menuManager.showMenu(loggedUserRole);

            System.out.println("Please choose option from the menu");
            String choice = scanner.nextLine();
// TODO : Consider change (if-else) construction with (switch)
            if (choice.equals(EXIT_COMMAND)) {
                menuManager.executeCommand(choice);
                break;
            } else if (choice.equals(LOGIN_COMMAND) && loggedUserUsername.equals(EMPTY_USER)) {
                loginCommand.execute();
            } else if (choice.equals(REGISTER_COMMAND) && loggedUserUsername.equals(EMPTY_USER)) {
                registerCommand.execute();
            } else if (choice.equals(RATE_BOOK_COMMAND) && !loggedUserUsername.equals(EMPTY_USER)) {
                rateBookCommand.execute();
            } else if (choice.equals(LOGOUT_COMMAND) && !loggedUserUsername.equals(EMPTY_USER)) {
                loggedUserUsername = "";
                loggedUserRole = "";
            } else {
                menuManager.executeCommand(choice);
            }
        }
    } //TODO : Optimize services !!!
}