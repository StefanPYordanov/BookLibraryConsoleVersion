package org.example.service;

import org.example.helper.BookMenu;
import org.example.helper.validations.BookValidator;
import org.example.model.entity.BookEntity;
import org.example.repository.BookRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.helper.messages.TextMessages.*;

public class BookServiceImpl implements BookService {
    Scanner scanner = new Scanner(System.in);
    BookMenu bookMenu = new BookMenu();
    BookValidator bookValidator = new BookValidator();
    BookRepository bookRepository = new BookRepository();

    @Override
    public void addBook() { // -> Add book to DB
            BookEntity bookToSave = new BookEntity();

            bookToSave.setIsbn(bookMenu.registerMenuIsbn());
            bookToSave.setBookName(bookMenu.registerMenuTitle());
            bookToSave.setAuthorName(bookMenu.registerMenuAuthor());
            bookToSave.setGenre(bookMenu.registerMenuGenre());
            bookToSave.setReleaseYear(bookMenu.registerMenuReleaseYear());
            bookToSave.setPages(bookMenu.registerMenuPages());
            // Rating is set to be 0 in initialization, will be incremented after user vote
            bookToSave.setRating(RATING_BEFORE_SOMEONE_RATE_FOR_BOOK);

            bookRepository.addBook(bookToSave);

            System.out.println(bookToSave.getBookName() + " has been added to library!\n");
    }

    @Override
    public void displayBooks() { // -> Show list of all books in library
        try {
            ResultSet resultSet = bookRepository.getAllBooks();

            while (resultSet.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("\n*ISBN* : ").
                        append(resultSet.getString(1))
                        .append(" *Title* : ")
                        .append(resultSet.getString(2))
                        .append(" *Author* : ")
                        .append(resultSet.getString(3))
                        .append(" *Genre* : ")
                        .append(resultSet.getString(4))
                        .append(" *Release Year* : ")
                        .append(resultSet.getString(5))
                        .append(" *Pages* : ")
                        .append(resultSet.getString(6))
                        .append(" *Rating* : ")
                        .append(resultSet.getString(7));
                System.out.println(builder);
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with displaying all books!\nPlease try again !");
        }
    }

    @Override
    public void deleteBook() { //-> Function for admins, to delete books
        String bookToDelete = bookMenu.deleteBookMenu();

        bookRepository.deleteBookByName(bookToDelete);

        System.out.println(bookToDelete + " has been removed from library!\n");
    }

    public void addRating(String name) { // -> Add rating to book in DB
        if (bookValidator.isBookExist(name)) {
            try {
               ResultSet resultSet = bookRepository.getBookByName(name);

                if (resultSet.next()) {
                    int rating = resultSet.getInt(7);
                    rating++;
                    bookRepository.updateBookRatingByName(rating, name);
                    System.out.println("Thank you for your vote !\n");
                }
            } catch (SQLException e) {
                System.out.println("A problem has occurred with adding rating to book!\nPlease try again !");
            }
        } else {
            System.out.println("Book don't exist!");
        }
    }

    @Override
    public void mostRatedBooks() { // -> Show books in order from the biggest rating to the lowest
        try {
            ResultSet resultSet = bookRepository.getBooksByRating();

            while (resultSet.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("\n*ISBN* : ")
                        .append(resultSet.getString(1))
                        .append(" *Title* : ")
                        .append(resultSet.getString(2))
                        .append(" *Author* : ")
                        .append(resultSet.getString(3))
                        .append(" *Genre* : ")
                        .append(resultSet.getString(4))
                        .append(" *Release Year* : ")
                        .append(resultSet.getString(5))
                        .append(" *Pages* : ")
                        .append(resultSet.getString(6))
                        .append(" *Rating* : ")
                        .append(resultSet.getString(7));
                System.out.println(builder);
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with displaying most rated books!\nPlease try again !");
        }
    }

    public void vote(int userId) { // -> Check if user is already voted, add vote is not
        List<String> listOfTitles = new ArrayList<>();
        System.out.println("Please enter title to vote for:");
        String title = scanner.nextLine().toLowerCase(); //Fetch all books from db, to ignore case sensitivity
        try {
              ResultSet resultSet = bookRepository.getBookNameByUserId(userId);

            while (resultSet.next()) {
                //resultSet return list of all books that user is already voted
                listOfTitles.add(resultSet.getString(1).toLowerCase());
            }
            if  (!listOfTitles.contains(title)){
                    addRating(title);
                    bookRepository.addRating(userId, title);
            }else{
                System.out.println("You already voted for this book!");
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with adding a vote to database!\nPlease try again !");
        }
    }
}
