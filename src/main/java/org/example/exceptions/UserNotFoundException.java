package org.example.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        System.out.println("The User does not exist!");
    }
}
