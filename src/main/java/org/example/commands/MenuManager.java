package org.example.commands;

import java.util.HashMap;
import java.util.Map;

import static org.example.helper.messages.CommandsMessages.*;
import static org.example.helper.messages.UserMessages.*;
import static org.example.helper.messages.PrintMenu.*;

public class MenuManager {
    private Map<String, Command> commands = new HashMap<>(); //Map with valid commands
    public void showAdminMenu(){ //Show admin menu and add commands for admin to map
        PrintAdminMenu();

        if (commands.size() != ADMIN_MENU) { // check if commands map have the same commands, if not clear it and add new commands
            commands.clear();                // prevent map to load every time if contain same values
            commands.put(EXIT_COMMAND, new ExitCommand());
            commands.put(DISPLAY_ALL_BOOKS_COMMAND, new ShowLibraryCommand());
            commands.put(ADD_BOOK_COMMAND, new AddBookCommand());
            commands.put(RATE_BOOK_COMMAND, new RateBookCommand());
            commands.put(DISPLAY_BIGGEST_RATED_BOOKS_COMMAND, new ShowBiggestRatedBooksCommand());
            commands.put(LOGOUT_COMMAND, new LogoutCommand());
            commands.put(DELETE_BOOK_COMMAND, new DeleteBookCommand());
            commands.put(DELETE_USER_COMMAND, new DeleteUserCommand());
            commands.put(GIVE_ADMIN_ROLE_TO_USER_COMMAND, new GiveRoleCommand());
        }
    }
    public void showUserMenu(){
        PrintUserMenu();

        if (commands.size() != USER_MENU) {
            commands.clear();
            commands.put(EXIT_COMMAND, new ExitCommand());
            commands.put(DISPLAY_ALL_BOOKS_COMMAND, new ShowLibraryCommand());
            commands.put(ADD_BOOK_COMMAND, new AddBookCommand());
            commands.put(RATE_BOOK_COMMAND, new RateBookCommand());
            commands.put(DISPLAY_BIGGEST_RATED_BOOKS_COMMAND, new ShowBiggestRatedBooksCommand());
            commands.put(LOGOUT_COMMAND, new LogoutCommand());
        }
    }
    public void showGuestMenu(){
        PrintGuestMenu();

        if (commands.size() != GUEST_MENU) {
            commands.clear();
            commands.put(LOGIN_COMMAND, new LoginCommand());
            commands.put(REGISTER_COMMAND, new RegisterCommand());
            commands.put(EXIT_COMMAND, new ExitCommand());
            commands.put(DISPLAY_ALL_BOOKS_COMMAND, new ShowLibraryCommand());
        }
    }

    public void executeCommand(String choice){
        Command command = commands.get(choice);
        if (command != null){
            command.execute();
        }else{
            System.out.println("Invalid command, please select from existing one");
        }
    }

    public void showMenu (String role){
        if (role.equals("Admin")){
            showAdminMenu();
        } else if (role.equals("User")) {
            showUserMenu();
        }else{
            showGuestMenu();
        }
    }
}
