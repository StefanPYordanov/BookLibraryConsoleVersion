package org.example.helper;

import org.example.helper.validations.BookValidator;

import java.util.Scanner;

import static org.example.helper.validations.GenericValidator.isFieldEmpty;

public class BookMenu { //TODO : Move this methods in some proper package
    Scanner scanner = new Scanner(System.in);
    BookValidator bookValidator = new BookValidator();

    public int registerMenuIsbn() {
        // Set book isbn in AddBook form
        System.out.println("Please enter isbn:");
        int isbn = scanner.nextInt();
        while (!bookValidator.isNumberPositive(isbn)) {
            isbn = scanner.nextInt();
        }
        scanner.nextLine(); //Clear scanner buffer
        return isbn;
    }

    public String registerMenuTitle() {
        // Set book title in AddBook form
        System.out.println("Please enter title:");
        String bookName = scanner.nextLine();
        while (bookValidator.isBookExist(bookName) || isFieldEmpty(bookName)) {
            System.out.println("Book already exist, please try with other book!");
            bookName = scanner.nextLine();
        }
        return bookName;
    }

    public String registerMenuAuthor() {
        // Set book author in AddBook form
        System.out.println("Please enter author:");
        String author = scanner.nextLine();
        while (isFieldEmpty(author)) {
            author = scanner.nextLine();
        }
        return author;
    }

    public String registerMenuGenre() {
        // Set book genre in AddBook form
        System.out.println("Please enter genre:");
        String genre = scanner.nextLine();
        while (isFieldEmpty(genre)) {
            genre = scanner.nextLine();
        }
        return genre;
    }

    public int registerMenuReleaseYear() {
        // Set book release year in AddBook form
        System.out.println("Please enter the release year of the book");
        int releaseYear = scanner.nextInt();
        while (!bookValidator.isYearValid(releaseYear)) {
            releaseYear = scanner.nextInt();
        }
        scanner.nextLine(); //Clear scanner buffer
        return releaseYear;
    }

    public int registerMenuPages() {
        // Set book pages in AddBook form
        System.out.println("Please enter book pages:");
        int pages = scanner.nextInt();
        while (!bookValidator.isNumberPositive(pages)) {
            pages = scanner.nextInt();
        }
        scanner.nextLine(); //Clear scanner buffer
        return pages;
    }

    public String deleteBookMenu() {
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
