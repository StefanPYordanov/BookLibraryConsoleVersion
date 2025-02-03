package org.example.helper;

import java.util.Scanner;

public class BookMenu {
    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();

    public int registerMenuIsbn() {
        // Enter whole data for the book
        System.out.println("Please enter isbn:");
        int isbn = scanner.nextInt();
        while (!validator.isNumberValid(isbn)) {
            isbn = scanner.nextInt();
        }
        return isbn;
    }

    public String registerMenuTitle() {
        System.out.println("Please enter title:");
        scanner.nextLine(); // Clearing buffer
        String bookName = scanner.nextLine();
        while (validator.isBookExist(bookName) || validator.isFieldEmpty(bookName)) {
            System.out.println("Book already exist, please try with other book!");
            bookName = scanner.nextLine();
        }
        return bookName;
    }

    public String registerMenuAuthor() {
        System.out.println("Please enter author:");
        String author = scanner.nextLine();
        while (validator.isFieldEmpty(author)) {
            author = scanner.nextLine();
        }
        return author;
    }

    public String registerMenuGenre() {
        System.out.println("Please enter genre:");
        String genre = scanner.nextLine();
        while (validator.isFieldEmpty(genre)) {
            genre = scanner.nextLine();
        }
        return genre;
    }

    public int registerMenuReleaseYear() {
        System.out.println("Please enter the release year of the book");
        int releaseYear = scanner.nextInt();
        while (!validator.isYear(releaseYear)) {
            releaseYear = scanner.nextInt();
        }
        return releaseYear;
    }

    public int registerMenuPages() {
        System.out.println("Please enter book pages:");
        int pages = scanner.nextInt();
        while (!validator.isNumberValid(pages)) {
            pages = scanner.nextInt();
        }
        return pages;
    }

    public String deleteBookMenu() {
        System.out.println("Please enter book name to delete book");
        String bookToDelete = scanner.nextLine();
        while (!validator.isBookExist(bookToDelete)) {
            System.out.println("Please chose from existing library");
            bookToDelete = scanner.nextLine();
        }
        return bookToDelete;
    }
}
