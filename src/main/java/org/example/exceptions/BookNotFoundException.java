package org.example.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        System.out.println("The Book does not exist!");
    }
}
