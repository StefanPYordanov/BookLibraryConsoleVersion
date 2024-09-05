package org.example.helper;

public class Menu {

    public void nonUserMenu(){
        System.out.println("Welcome to Book Library");
        System.out.println("Please choose option from the menu");
        System.out.println("For Login press 1");
        System.out.println("For Register press 2");
        System.out.println("To see our books press 3");
        System.out.println("To close app press 4");
    }
    public void userMenu(){
        System.out.println("Welcome ->USER FULL NAME<-");
        System.out.println("Please choose option from the menu");
        System.out.println("If you want to add a book press 1");
        System.out.println("To see our books press 2");
        System.out.println("To rate a book press 3");
        System.out.println("For Logout press 4");
        System.out.println("To close app press 5");
        // if user is admin press 6 to access admin panel
    }
    public void adminMenu(){
        System.out.println("To delete a book press 1");
        System.out.println("To block user press 2");
        System.out.println("To give admin role to other user press 3");
    }
}
