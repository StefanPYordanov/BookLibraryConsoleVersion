package org.example.exceptions;

public class InvalidUsernameOrPasswordException extends Exception{
    public InvalidUsernameOrPasswordException() {
        System.out.println("Invalid Username or Password !");
    }
}
