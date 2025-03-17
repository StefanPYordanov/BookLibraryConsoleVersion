package org.example.controller;

import org.example.helper.validations.BookValidator;
import org.example.helper.validations.GenericValidator;

import java.util.Scanner;

import static org.example.helper.validations.GenericValidator.isFieldEmpty;

public class BookController {
    Scanner scanner = new Scanner(System.in);
    BookValidator bookValidator = new BookValidator();
    public int registrationIsbn() {
        // Set book isbn in AddBook form
        System.out.println("Please enter isbn:");
        int isbn = GenericValidator.readNumber();
        while (!bookValidator.isNumberPositive(isbn) || bookValidator.isIsbnExist(isbn)) {
            System.out.println("Book with same isbn already exist, check isbn of the book " +
                    "you try to add and try again");
            isbn = GenericValidator.readNumber();
        }
        return isbn;
    }
    public String registrationTitle() {
        // Set book title in AddBook form
        System.out.println("Please enter title:");
        String bookName = scanner.nextLine();
        while (bookValidator.isBookExist(bookName) || isFieldEmpty(bookName)) {
            System.out.println("Book already exist, please try with other book!");
            bookName = scanner.nextLine();
        }
        return bookName;
    }
    public String registrationAuthor() {
        // Set book author in AddBook form
        System.out.println("Please enter author:");
        String author = scanner.nextLine();
        while (isFieldEmpty(author)) {
            author = scanner.nextLine();
        }
        return author;
    }
    public String registrationGenre() {
        // Set book genre in AddBook form
        System.out.println("Please enter genre:");
        String genre = scanner.nextLine();
        while (isFieldEmpty(genre)) {
            genre = scanner.nextLine();
        }
        return genre;
    }
    public int registrationReleaseYear() {
        // Set book release year in AddBook form
        System.out.println("Please enter the release year of the book");
        int releaseYear = GenericValidator.readNumber();
        while (!bookValidator.isYearValid(releaseYear)) {
            releaseYear = GenericValidator.readNumber();
        }
        return releaseYear;
    }
    public int registrationPages() {
        // Set book pages in AddBook form
        System.out.println("Please enter book pages:");
        int pages = GenericValidator.readNumber();
        while (!bookValidator.isNumberPositive(pages)) {
            pages = GenericValidator.readNumber();
        }
        return pages;
    }
    public String bookToDelete() {
        // Return the book title we want to delete
        System.out.println("Please enter book name to delete book");
        String bookToDelete = scanner.nextLine();
        while (!bookValidator.isBookExist(bookToDelete)) {
            System.out.println("Please chose from existing library");
            bookToDelete = scanner.nextLine();
        }
        return bookToDelete;
    }
}
