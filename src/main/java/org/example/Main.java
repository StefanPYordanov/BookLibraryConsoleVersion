package org.example;


import org.example.helper.Menu;
import org.example.helper.Validator;
import org.example.model.entity.UserEntity;
import org.example.service.BookServiceImpl;
import org.example.service.UserServiceImpl;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        UserEntity userEntity = new UserEntity();
        Menu menu = new Menu();
        Validator validator = new Validator();



                        //BOOK

        // Add book test functionality - only for USERS
//        bookServiceImpl.addBook();

        //Display all books test functionality - for USERS and NOT-USERS
        bookServiceImpl.displayBooks();

        //Delete book test functionality - only for ADMINS
//        System.out.println("isbn to delete");
//        int isbn = scanner.nextInt();
//        bookServiceImpl.deleteBook(isbn);

        //Find book by book name - for USERS
//         System.out.println("book name to find");
//         String name = scanner.nextLine();
//         bookServiceImpl.findBookByName(name);

//        bookServiceImpl.mostRatedBooks();

//        bookServiceImpl.addRating();

                        //USER

        //Display all users for admin purpose - only for ADMINS
//        userServiceImpl.displayUsers();

        //Block user - only for ADMINS
//        System.out.println("user to delete");
//        int id = scanner.nextInt();
//        userServiceImpl.deleteUser(id);

        //Give role to user - only for ADMINS
//        System.out.println("id to make admin");
//        int id = scanner.nextInt();
//        userServiceImpl.giveRole(id);

        //Login - only for NOT-USERS
//        System.out.println("enter username");
//        String username = scanner.nextLine();
//        System.out.println("enter password");
//        String password = scanner.nextLine();
//        userServiceImpl.login(username, password);

        //Register - only for NOT-USERS
//        add check if username and email is already exist,
//        userServiceImpl.register();

//        menu.nonUserMenu();
//        menu.userMenu();
//        menu.adminMenu();

//        System.out.println(validator.isUsernameExist("stefan"));
//        System.out.println(validator.isEmailExist("stefan@gmail.com"));
//        System.out.println(validator.isEmailValid("sttt@gmail.com"));
//        System.out.println(validator.isPasswordsMatch("stefan", "stefan"));
//        System.out.println(validator.isFieldEmpty("assa"));


//        menu.nonUserMenu();
//        String menuChoice = scanner.nextLine();
//        boolean appRunning = true;
//
//        // Loop for program running
//        while (appRunning){
//
//
//
//// Loop for starting menu
//
//            switch (menuChoice) {
//
//                // Case if user chose to login
//                case "1":
//                    System.out.println("Please Enter your username:");
//                    String username = scanner.nextLine();
//                    System.out.println("Please Enter your password");
//                    String password = scanner.nextLine();
//
//// Loop for login data
//                    while (!userServiceImpl.login(username, password)){
//                        System.out.println("Please Enter your username:");
//                         username = scanner.nextLine();
//                        System.out.println("Please Enter your password");
//                         password = scanner.nextLine();
//                    } //While Loop END !!!
//
//
//// if-else if user is admin or common user
//                    // if user si admin case
//                    if (userServiceImpl.isAdmin(username)){
//                        menu.adminMenu();
//                        menuChoice = scanner.nextLine();
//                        switch (menuChoice){
//                            case "1":
//                                System.out.println("add book");
//
//                                break;
//                            case "2":
//                                System.out.println("book list");
//                                break;
//                            case "3":
//                                System.out.println("rate book");
//                                break;
//                            case "4":
//                                System.out.println("most rating");
//                                break;
//                            case "5":
//                                System.out.println("logout");
//                                break;
//                            case "6":
//                                System.out.println("delete book");
//                                break;
//                            case "7":
//                                System.out.println("block user");
//                                break;
//                            case "8":
//                                System.out.println("give role");
//                                break;
//                            case "9":
//                                appRunning = false;
//                                break;
//                            default:
//                                System.out.println("Invalid command, please select from existing one");
//                                menuChoice = scanner.nextLine();
//                                break;
//                        }
//
//
//
//
//
//                    }else{
//                        menu.userMenu();
//                    }
//
//
//
//
//
////Case 1 for login END !!!
//                    break;
//
//
//                case "2":
//                    System.out.println("register");
//                    break;
//                case "3":
//                    System.out.println("see book");
//                    break;
//                case "9":
//                    appRunning = false;
//                    break;
//                default:
//                    System.out.println("Invalid command, please select from existing one");
//
//            }
//
//
//
//
//
//
//
//
//
//
//            if (!menuChoice.equals("9")){
//                menuChoice = scanner.nextLine();
//            }
//
//        }
    }

    }
