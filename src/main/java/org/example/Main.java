package org.example;

import org.example.commands.*;

import java.util.Scanner;

import static org.example.helper.messages.TextMessages.*;
import static org.example.model.dto.LoggedUserDto.*;

public class Main {
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menuManager.showMenu(loggedUserRole);

            System.out.println("Please choose option from the menu");
            String choice = scanner.nextLine();

            if (choice.equals(EXIT_COMMAND)) {
                menuManager.executeCommand(choice);
                break;
            }else {
                menuManager.executeCommand(choice);
            }
        }
    }
}