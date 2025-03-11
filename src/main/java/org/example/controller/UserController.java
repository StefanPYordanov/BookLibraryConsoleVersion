package org.example.controller;

import org.example.helper.validations.GenericValidator;
import org.example.helper.validations.UserValidator;

import java.util.Scanner;

import static org.example.helper.validations.GenericValidator.isFieldEmpty;

public class UserController {
    Scanner scanner = new Scanner(System.in);
    UserValidator userValidator = new UserValidator();

    public String registrationUsername() {
        // Set user username in Register user form
        System.out.println("Please enter username:");
        String username = scanner.nextLine();
        while (isFieldEmpty(username) || userValidator.isUsernameExist(username)) {
            System.out.println("Please enter username:");
            username = scanner.nextLine();
        }
        return username;
    }
    public String registrationPassword() {
        // Set user password in Register user form
        System.out.println("Please enter password:");
        String password = scanner.nextLine();
        System.out.println("Repeat password:");
        String rePass = scanner.nextLine();
        while (!userValidator.isPasswordsMatch(password, rePass) || isFieldEmpty(password) || isFieldEmpty(rePass)) {
            System.out.println("Please enter password:");
            password = scanner.nextLine();
            System.out.println("Please repeat password:");
            rePass = scanner.nextLine();
        }
        return password;
    }
    public String registrationEmail() {
        // Set user email in Register user form
        System.out.println("Please enter email:");
        String email = scanner.nextLine();
        while (!userValidator.isEmailValid(email) || userValidator.isEmailExist(email) || isFieldEmpty(email)) {
            System.out.println("Please enter email:");
            email = scanner.nextLine();
        }
        return email;
    }
    public String registrationFullName() {
        // Set user full name in Register user form
        System.out.println("Please enter your full name:");
        String fullName = scanner.nextLine();
        while (isFieldEmpty(fullName)) {
            System.out.println("Please enter your full name:");
            fullName = scanner.nextLine();
        }
        return fullName;
    }
    public int userToDelete(){
        System.out.println("Please enter the id of the user you want to delete:");
        return GenericValidator.readNumber();
    }
    public int promoteUser(){
        System.out.println("Please enter the id of the user you want to become admin:");
        return GenericValidator.readNumber();
    }
    public String loginUsername(){
        System.out.println("Please Enter your username:");
        return scanner.nextLine();
    }
    public String loginPassword(){
        System.out.println("Please Enter your password");
        return scanner.nextLine();
    }
}
