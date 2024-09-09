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
//        bookServiceImpl.displayBooks();

        //Delete book test functionality - only for ADMINS
//        System.out.println("isbn to delete");
//        int isbn = scanner.nextInt();
//        bookServiceImpl.deleteBook(isbn);

        //Find book by book name - for USERS
//         System.out.println("book name to find");
//         String name = scanner.nextLine();
//         bookServiceImpl.findBookByName(name);

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


    }

    }
