package org.example.service;

import org.example.controller.BookController;
import org.example.helper.validations.BookValidator;
import org.example.logger.LoggerUtil;
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
    BookValidator bookValidator = new BookValidator();
    BookRepository bookRepository = new BookRepository();
    BookController bookController = new BookController();
    @Override
    public void addBook() { // -> Add book to DB
            BookEntity bookToSave = new BookEntity();

            bookToSave.setIsbn(bookController.registrationIsbn());
            bookToSave.setBookName(bookController.registrationTitle());
            bookToSave.setAuthorName(bookController.registrationAuthor());
            bookToSave.setGenre(bookController.registrationGenre());
            bookToSave.setReleaseYear(bookController.registrationReleaseYear());
            bookToSave.setPages(bookController.registrationPages());
            // Rating is set to be 0 in initialization, will be incremented after user vote
            bookToSave.setRating(RATING_BEFORE_SOMEONE_RATE_FOR_BOOK);

            bookRepository.saveBook(bookToSave);

            System.out.println(bookToSave.getBookName() + " has been added to library!\n");
            LoggerUtil.logInfo("Book: " + bookToSave.getBookName() + " has been added successfully");
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
            LoggerUtil.logWaring("Database error occurred while attempting to display all books");
        }
    }

    @Override
    public void deleteBook() { //-> Function for admins, to delete books
        String bookToDelete = bookController.bookToDelete();

        bookRepository.deleteBookByName(bookToDelete);

        System.out.println(bookToDelete + " has been removed from library!\n");
        LoggerUtil.logInfo("Book: " + bookToDelete + " has been deleted successfully");
    }
    @Override
    public void incrementBookRating (String name) { // -> Add rating to book in DB
        if (bookValidator.isBookExist(name)) {
            try {
               ResultSet resultSet = bookRepository.getBookByName(name);

                if (resultSet.next()) {
                    int rating = resultSet.getInt(7);
                    rating++;
                    bookRepository.updateBookRatingByBookName(rating, name);
                    System.out.println("Thank you for your vote !\n");
                    LoggerUtil.logInfo(name + " has increase rating successfully");
                }
            } catch (SQLException e) {
                System.out.println("A problem has occurred with adding rating to book!\nPlease try again !");
                LoggerUtil.logWaring("Database error occurred while attempting to vote for book");
            }
        } else {
            System.out.println("Book don't exist!");
            LoggerUtil.logInfo("Failed vote attempt for book " + name + " : Book don't exist in library");
        }
    }

    @Override
    public void mostRatedBooks() { // -> Show books in order from the biggest rating to the lowest
        try {
            ResultSet resultSet = bookRepository.getAllBooksInOrderByRating();

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
            LoggerUtil.logWaring("Database error occurred while attempting to display all books in" +
                    " order by biggest rating");
        }
    }
    @Override
    public void addBookRating(int userId) { // -> Check if user is already voted, add vote is not
        List<String> listOfTitles = new ArrayList<>();
        System.out.println("Please enter title to vote for:");
        String title = scanner.nextLine().toLowerCase(); //Fetch all books from db, to ignore case sensitivity
        try {
              ResultSet resultSet = bookRepository.getBookNameByUserId(userId);

            while (resultSet.next()) {
                //resultSet return list of all books that user is already voted
                listOfTitles.add(resultSet.getString(1).toLowerCase());
            }
            if  (bookValidator.isBookExist(title)){
                if  (!listOfTitles.contains(title)){
                    incrementBookRating(title);
                    bookRepository.saveRatingToCorrespondingUser(userId, title);
                    LoggerUtil.logInfo("User rated for " + title + " successfully");
                }else{
                    System.out.println("You already voted for this book!");
                    LoggerUtil.logInfo("Failed vote attempt for " + title + " : User already voted for this book");
                }
            }else{
                System.out.println("Sorry, you can't vote for not existing books,\n" +
                        "please add this book in library first!");
                LoggerUtil.logInfo("Failed vote attempt for " + title + " : Book don't exist in library");
            }
        } catch (SQLException e) {
            System.out.println("A problem has occurred with adding a vote to database!\nPlease try again !");
            LoggerUtil.logWaring("Database error occurred while attempting to add book rating");
        }
    }
}
