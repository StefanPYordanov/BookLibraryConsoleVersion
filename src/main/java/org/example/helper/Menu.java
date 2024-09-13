package org.example.helper;

public class Menu {

    public void nonUserMenu() { //-> This menu is shown when there are no logged users !
        System.out.println("*************************************************");
        System.out.println("Welcome to Book Library");
        System.out.println("Please choose option from the menu");
        System.out.println("*************************************************");
        System.out.println("For Login press 1");
        System.out.println("For Register press 2");
        System.out.println("To see our library press 3");
        System.out.println("To Exit the app press 9");
        System.out.println("*************************************************");
    }

    public void userMenu() { // -> This menu is shown when there are logged users, but they don't have admin permissions
        System.out.println("*************************************************");
        System.out.println("Please choose option from the menu");
        System.out.println("*************************************************");
        System.out.println("If you want to add a book press 1");
        System.out.println("To see our library press 2");
        System.out.println("To rate a book press 3");
        System.out.println("To see books with biggest rating press 4");
        System.out.println("For Logout press 5");
        System.out.println("To Exit the app press 9");
        System.out.println("*************************************************");
    }

    public void adminMenu() { // -> This menu is shown when the logged user is admin
        System.out.println("*************************************************");
        System.out.println("Please choose option from the menu");
        System.out.println("*************************************************");
        System.out.println("If you want to add a book press 1");
        System.out.println("To see our library press 2");
        System.out.println("To rate a book press 3");
        System.out.println("To see books with biggest rating press 4");
        System.out.println("For Logout press 5");
        System.out.println("To delete a book press 6");
        System.out.println("To block user press 7");
        System.out.println("To give admin role to other user press 8");
        System.out.println("To Exit the app press 9");
        System.out.println("*************************************************");
    }
}
