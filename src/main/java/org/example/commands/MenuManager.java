package org.example.commands;

import java.util.HashMap;
import java.util.Map;

import static org.example.helper.messages.TextMessages.*;

public class MenuManager {
    private Map<String, Command> commands = new HashMap<>(); //Map with valid commands
    public void showAdminMenu(){ //Show admin menu and add commands for admin to map
        System.out.println("*************************************************");
        System.out.println("To Exit the app press 3");
        System.out.println("To see our library press 4");
        System.out.println("If you want to add a book press 5");
        System.out.println("To rate a book press 6");
        System.out.println("To see books with biggest rating press 7");
        System.out.println("For Logout press 8");
        System.out.println("To delete a book press 9");
        System.out.println("To block user press 10");
        System.out.println("To give admin role to other user press 11");
        System.out.println("*************************************************");

        commands.clear(); //TODO : think to remove map loading time with if else to see if user before is with same authorizations
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
    public void showUserMenu(){
        System.out.println("*************************************************");
        System.out.println("To Exit the app press 3");
        System.out.println("To see our library press 4");
        System.out.println("If you want to add a book press 5");
        System.out.println("To rate a book press 6");
        System.out.println("To see books with biggest rating press 7");
        System.out.println("For Logout press 8");
        System.out.println("*************************************************");

        commands.clear();
        commands.put(EXIT_COMMAND, new ExitCommand());
        commands.put(DISPLAY_ALL_BOOKS_COMMAND, new ShowLibraryCommand());
        commands.put(ADD_BOOK_COMMAND, new AddBookCommand());
        commands.put(RATE_BOOK_COMMAND, new RateBookCommand());
        commands.put(DISPLAY_BIGGEST_RATED_BOOKS_COMMAND, new ShowBiggestRatedBooksCommand());
        commands.put(LOGOUT_COMMAND, new LogoutCommand());
    }
    public void showGuestMenu(){
        System.out.println("*************************************************");
        System.out.println("For Login press 1");
        System.out.println("For Register press 2");
        System.out.println("To Exit the app press 3");
        System.out.println("To see our library press 4");
        System.out.println("*************************************************");

        commands.clear();
        commands.put(LOGIN_COMMAND, new LoginCommand());
        commands.put(REGISTER_COMMAND, new RegisterCommand());
        commands.put(EXIT_COMMAND, new ExitCommand());
        commands.put(DISPLAY_ALL_BOOKS_COMMAND, new ShowLibraryCommand());
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
