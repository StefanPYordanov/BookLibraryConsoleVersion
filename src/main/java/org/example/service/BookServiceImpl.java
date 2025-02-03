package org.example.service;

import org.example.helper.BookMenu;
import org.example.helper.Validator;
import org.example.model.entity.BookEntity;
import org.example.repository.BookRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements BookService {
    Scanner scanner = new Scanner(System.in);
    BookMenu bookMenu = new BookMenu();
    Validator validator = new Validator();
    BookRepository bookRepository = new BookRepository();

    @Override
    public void addBook() { // -> Add book to DB
        BookEntity bookEntity = new BookEntity();
        try {
            bookEntity.setIsbn(bookMenu.registerMenuIsbn());
            bookEntity.setBookName(bookMenu.registerMenuTitle());
            bookEntity.setAuthorName(bookMenu.registerMenuAuthor());
            bookEntity.setGenre(bookMenu.registerMenuGenre());
            bookEntity.setReleaseYear(bookMenu.registerMenuReleaseYear());
            bookEntity.setPages(bookMenu.registerMenuPages());
            // Rating is set to be 0 in initialization, will be incremented after user vote
            bookEntity.setRating(0);

            bookRepository.addBook(bookEntity);

            System.out.println(bookEntity.getBookName() + " has been added to library!\n");

        } catch (SQLException e) {
            System.out.println("Can't add book!!!");
        }
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
            System.out.println("Can't Display books!!!");
        }
    }

    @Override
    public void deleteBook() { //-> Function for admins, to delete books
        String bookToDelete = bookMenu.deleteBookMenu();
        try {
            bookRepository.deleteBookByName(bookToDelete);

            System.out.println(bookToDelete + " has been removed from library!\n");
        } catch (SQLException e) {
            System.out.println("Can't Delete book!!!");
        }
    }

    public void addRating(String name) { // -> Add rating to book in DB
        if (validator.isBookExist(name)) {
            try {
               ResultSet resultSet = bookRepository.getBookByName(name);

                if (resultSet.next()) {
                    int rating = resultSet.getInt(7);
                    rating++;
                    bookRepository.updateBookRatingByName(rating, name);
                    System.out.println("Thank you for your vote !\n");
                }
            } catch (SQLException e) {
                System.out.println("Can't add rating!!!");
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
            System.out.println("Can't show most rated book!!!");
        }
    }

    public void vote(int userId) { // -> Check if user is already voted, add vote is not
        List<String> listOfTitles = new ArrayList<>();
        System.out.println("Please enter title to vote for:");
        String title = scanner.nextLine().toLowerCase();
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
            System.out.println("Can't Vote!!!");
        }
    }
}
