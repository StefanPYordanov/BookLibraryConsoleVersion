package org.example.helper;

import java.util.Scanner;

public class UserMenu {
    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();

    public String registerUserMenuUsername() {
        System.out.println("Please enter username:");
        String username = scanner.nextLine();
        while (validator.isFieldEmpty(username) || validator.isUsernameExist(username)) {
            System.out.println("Please enter username:");
            username = scanner.nextLine();
        }
        return username;
    }
    public String registerUserMenuPassword(){
        System.out.println("Please enter password:");
        String password = scanner.nextLine();
        System.out.println("Repeat password:");
        String rePass = scanner.nextLine();
        while (!validator.isPasswordsMatch(password, rePass) || validator.isFieldEmpty(password) || validator.isFieldEmpty(rePass)) {
            System.out.println("Please enter password:");
            password = scanner.nextLine();
            System.out.println("Please repeat password:");
            rePass = scanner.nextLine();
        }
        return password;
    }
    public String registerUserMenuEmail(){
        System.out.println("Please enter email:");
        String email = scanner.nextLine();
        while (!validator.isEmailValid(email) || validator.isEmailExist(email) || validator.isFieldEmpty(email)) {
            System.out.println("Please enter email:");
            email = scanner.nextLine();
        }
        return email;
    }
    public String registerUserMenuFullName(){
        System.out.println("Please enter your full name:");
        String fullName = scanner.nextLine();
        while (validator.isFieldEmpty(fullName)) {
            System.out.println("Please enter your full name:");
            fullName = scanner.nextLine();
        }
        return fullName;
    }
    public void userDoNotExistMenu(){
        System.out.println("User don't exist \nPlease enter existing id:");
    }
}