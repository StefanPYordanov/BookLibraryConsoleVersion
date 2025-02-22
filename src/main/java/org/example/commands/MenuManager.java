package org.example.commands;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private Map<String, Command> commands = new HashMap<>();
    public void showAdminMenu(){
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

        commands.clear();
        commands.put("3", new ExitCommand());
        commands.put("4", new ShowLibraryCommand());
        commands.put("5", new AddBookCommand());
        commands.put("7", new ShowBiggestRatedBooksCommand());
        commands.put("9", new DeleteBookCommand());
        commands.put("10", new DeleteUserCommand());
        commands.put("11", new GiveRoleCommand());
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
        commands.put("3", new ExitCommand());
        commands.put("4", new ShowLibraryCommand());
        commands.put("5", new AddBookCommand());
        commands.put("7", new ShowBiggestRatedBooksCommand());
    }
    public void showGuestMenu(){
        System.out.println("*************************************************");
        System.out.println("For Login press 1");
        System.out.println("For Register press 2");
        System.out.println("To Exit the app press 3");
        System.out.println("To see our library press 4");
        System.out.println("*************************************************");

        commands.clear();
        commands.put("3", new ExitCommand());
        commands.put("4", new ShowLibraryCommand());
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
        if (role.equals("admin")){
            showAdminMenu();
        } else if (role.equals("user")) {
            showUserMenu();
        }else{
            showGuestMenu();
        }
    }
}
